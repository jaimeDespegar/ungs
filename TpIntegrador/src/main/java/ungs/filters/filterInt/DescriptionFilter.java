package ungs.filters.filterInt;

import ungs.connectors.AbstractConnector;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class DescriptionFilter<I, O> extends AbstractFilter<I, O> {

    protected String filterDescription;

    public DescriptionFilter(AbstractConnector connector, String filterDescription) {
        super(connector);
        this.filterDescription = filterDescription;
    }

    @Override
    public List<O> applyFilter() {
        return (List<O>) listInput.stream().filter(isContainsDescription(filterDescription))
                                           .collect(Collectors.toList());
    }

    protected abstract Predicate<I> isContainsDescription(String filterDescription);

}