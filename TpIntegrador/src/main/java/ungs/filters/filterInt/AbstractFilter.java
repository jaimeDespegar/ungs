package ungs.filters.filterInt;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import ungs.connectors.impl.AbstractConnector;
import ungs.model.Configuration;
import java.util.List;

public abstract class AbstractFilter<I,O, P> implements Filter<I,O> {

    protected AbstractConnector connector;
    protected Configuration configuration;
    protected List<P> listInput;
    protected List<I> result;

    public AbstractFilter(AbstractConnector connector) {
        this.connector = connector;
        this.listInput = Lists.newArrayList();
        this.configuration = connector.getConfiguration();
    }

    public AbstractFilter(AbstractConnector connector, List<P> listInput) {
        this.connector = connector;
        this.listInput = listInput;
        this.configuration = connector.getConfiguration();
    }

    public void setListInput(List<P> listInput) {
        this.listInput = listInput;
    }

    public List<O> doApplyFilter() {
        if (CollectionUtils.isNotEmpty(result)) {
            return applyFilter(result);
        }
        return applyFilter();
    }

    public void setResult(List<I> list) {
        this.result = list;
    }

    @Override
    public List<O> applyFilter(List<I> list) {
        return Lists.newArrayList();
    }
}