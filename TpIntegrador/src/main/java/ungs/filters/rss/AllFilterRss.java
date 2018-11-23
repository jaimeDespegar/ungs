package ungs.filters.rss;

import ungs.connectors.impl.AbstractConnector;
import ungs.connectors.interfaz.RssSpecificConnector;
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
        return ((RssSpecificConnector)connector).find(themes);
    }

}