package ungs.filters;

import com.google.common.collect.Lists;
import ungs.filters.filterInt.AbstractFilter;
import java.util.List;

public class FilterExecutor<I> {

    public List<I> getData(List<AbstractFilter> filters) {
        List<I> result = Lists.newArrayList();
        for (AbstractFilter f : filters) {
            f.setListInput(result);
            result = f.doApplyFilter();
        }
        return result;
    }

}