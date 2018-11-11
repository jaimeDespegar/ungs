package ungs.filters.filterInt;

import ungs.connectors.AbstractConnector;
import ungs.model.Configuration;

public abstract class AbstractFilter<I,O> implements Filter<I,O> {

    protected AbstractConnector connector;
    protected Configuration configuration;

    public AbstractFilter(AbstractConnector connector) {
        this.connector = connector;
    }

}