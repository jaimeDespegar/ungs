package ungs.filters.rss;

import ungs.connectors.impl.AbstractConnector;
import ungs.dto.rss.RssItemDto;
import ungs.filters.filterInt.DescriptionFilter;
import java.util.function.Predicate;

public class DescriptionFilterRss extends DescriptionFilter<RssItemDto, RssItemDto, AllFilterRss, RssItemDto> {

    public DescriptionFilterRss(AbstractConnector connector, String filterDescription) {
        super(connector, filterDescription);
        this.allFilter = new AllFilterRss(connector);
    }

    @Override
    protected Predicate<RssItemDto> isContainsDescription(String filterDescription) {
        return i -> i!=null?i.getDescription().toLowerCase().contains(filterDescription.toLowerCase()):false;
    }

}