package ungs.filters.filterInt;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import ungs.connectors.AbstractConnector;
import ungs.model.Configuration;
import java.util.List;

public abstract class AbstractFilter<I,O, P> implements Filter<I,O> {

    protected AbstractConnector connector;
    protected Configuration configuration;
    protected List<P> listInput;

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

    public List<O> doApplyFilter(List<I> input) {
        if (CollectionUtils.isNotEmpty(input)) {
            return applyFilter(input);
        }
        return applyFilter();
    }

    @Override
    public List<O> applyFilter(List<I> list) {
        return Lists.newArrayList();
    }
}