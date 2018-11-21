package ungs.filters.filterInt;

import ungs.connectors.AbstractConnector;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class DescriptionFilter<I, O, F extends AllFilter, P> extends AbstractFilter<I, O, P> {

    protected String filterDescription;
    protected F allFilter;

    public DescriptionFilter(AbstractConnector connector, String filterDescription) {
        super(connector);
        this.filterDescription = filterDescription;
    }

    @Override
    public List<O> applyFilter() {
        return filter(allFilter.applyFilter());
    }

    public List<O> applyFilter(List<I> input) {
        return filter(input);
    }

    protected List<O> filter(List<I> input) {
        return (List<O>) input.stream()
                              .filter(isContainsDescription(filterDescription))
                              .collect(Collectors.toList());
    }

    protected abstract Predicate<I> isContainsDescription(String filterDescription);

}