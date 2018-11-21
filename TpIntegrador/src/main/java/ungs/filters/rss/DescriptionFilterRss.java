package ungs.filters.rss;

import ungs.connectors.AbstractConnector;
import ungs.dto.rss.RssItemDto;
import ungs.filters.filterInt.DescriptionFilter;
import java.util.function.Predicate;

public class DescriptionFilterRss extends DescriptionFilter<RssItemDto, RssItemDto, AllFilterRss> {

    public DescriptionFilterRss(AbstractConnector connector, String filterDescription) {
        super(connector, filterDescription);
        this.allFilter = new AllFilterRss(connector);
    }

    @Override
    protected Predicate<RssItemDto> isContainsDescription(String filterDescription) {
        return i -> i.getDescription().toLowerCase().contains(filterDescription.toLowerCase());
    }

}