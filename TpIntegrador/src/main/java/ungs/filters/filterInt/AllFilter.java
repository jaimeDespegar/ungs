package ungs.filters.filterInt;

import ungs.connectors.AbstractConnector;
import java.util.List;

public abstract class AllFilter<I,O> extends AbstractFilter<I,O> {

    public AllFilter(AbstractConnector connector) {
        super(connector);
    }

    @Override
    public List<O> applyFilter() {
        return null;
    }

}