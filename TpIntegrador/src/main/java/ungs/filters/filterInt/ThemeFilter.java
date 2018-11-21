package ungs.filters.filterInt;

import com.google.common.collect.Lists;
import ungs.connectors.AbstractConnector;
import java.util.List;
import java.util.function.Consumer;

public abstract class ThemeFilter<I,O,P> extends AbstractFilter<I,O,P> {

    public ThemeFilter(AbstractConnector connector, List<P> list) {
        super(connector, list);
    }

    @Override
    public List<O> applyFilter() {
        List<O> listResult = Lists.newArrayList();
        listInput.forEach(getConsumerTheme(listResult));
        return listResult;
    }

    protected abstract Consumer<P> getConsumerTheme(List<O> result);

}