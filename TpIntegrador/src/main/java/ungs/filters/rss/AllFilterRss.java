package ungs.filters.rss;

import ungs.connectors.RssConnector;
import ungs.dto.rss.RssItemDto;
import ungs.filters.filterInt.AbstractFilter;
import java.util.List;

public class AllFilterRss extends AbstractFilter<String, RssItemDto> {

    public AllFilterRss(RssConnector connector) {
        super(connector);
    }

    @Override
    public List<RssItemDto> applyFilter(List<String> filters) {
        return ((RssConnector)connector).find(filters);
    }

}