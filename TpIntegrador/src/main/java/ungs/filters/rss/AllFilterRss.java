package ungs.filters.rss;

import ungs.connectors.AbstractConnector;
import ungs.connectors.RssConnector;
import ungs.dto.rss.RssItemDto;
import ungs.filters.filterInt.AllFilter;
import java.util.List;

public class AllFilterRss extends AllFilter<String, RssItemDto, RssItemDto> {

    public AllFilterRss(AbstractConnector connector) {
        super(connector);
    }

    @Override
    public List<RssItemDto> applyFilter() {
        List<String> themes = configuration.getKeysStartWith("rss.theme.");
        return ((RssConnector)connector).find(themes);
    }

}