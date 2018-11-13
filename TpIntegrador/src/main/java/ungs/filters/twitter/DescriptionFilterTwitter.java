package ungs.filters.twitter;

import ungs.connectors.AbstractConnector;
import ungs.dto.TwitterObjectDto;
import ungs.filters.filterInt.DescriptionFilter;
import java.util.List;
import java.util.function.Predicate;

public class DescriptionFilterTwitter extends DescriptionFilter<TwitterObjectDto, TwitterObjectDto> {

    public DescriptionFilterTwitter(AbstractConnector connector, String descriptionFilter) {
        super(connector, descriptionFilter);
    }

    @Override
    protected Predicate<TwitterObjectDto> isContainsDescription(String filterDescription) {
        return i -> i.getDescription().toLowerCase().contains(filterDescription.toLowerCase());
    }

}