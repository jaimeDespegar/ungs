package ungs.services;

import ungs.connectors.RssConnector;
import ungs.dto.rss.RssItemDto;
import ungs.themes.Theme;
import ungs.utils.ConfigUtils;
import java.util.List;

public class RssService extends Service{

    public RssService(RssConnector connector) {
        super(connector, ConfigUtils.RSS_FILE);
    }

    public boolean isOkServiceRss() {
        return this.connector.isAvailable();
    }

    public List<RssItemDto> getListItemsByTheme(Theme theme) {
        return this.connector.find(theme.getValueKey());
    }

}