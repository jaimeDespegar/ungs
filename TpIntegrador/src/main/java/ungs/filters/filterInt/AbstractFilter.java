package ungs.filters.filterInt;

import com.google.common.collect.Lists;
import ungs.connectors.AbstractConnector;
import ungs.model.Configuration;
import java.util.List;

public abstract class AbstractFilter<I,O> implements Filter<O> {

    protected AbstractConnector connector;
    protected Configuration configuration;
    protected List<I> listInput;

    public AbstractFilter(AbstractConnector connector) {
        this.connector = connector;
        this.listInput = Lists.newArrayList();
        this.configuration = connector.getConfiguration();
    }

    public AbstractFilter(AbstractConnector connector, List<I> listInput) {
        this.connector = connector;
        this.listInput = listInput;
        this.configuration = connector.getConfiguration();
    }

    public void setListInput(List<I> listInput) {
        this.listInput = listInput;
    }
}