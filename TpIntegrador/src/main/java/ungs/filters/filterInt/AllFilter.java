package ungs.filters.filterInt;

import ungs.connectors.impl.AbstractConnector;
import java.util.List;

public abstract class AllFilter<I,O,P> extends AbstractFilter<I,O,P> {

    public AllFilter(AbstractConnector connector) {
        super(connector);
    }

    @Override
    public List<O> applyFilter() {
        return null;
    }

}