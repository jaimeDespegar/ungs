package ungs.services;

import com.google.common.collect.Lists;
import ungs.connectors.RssConnector;
import ungs.dto.rss.RssItemDto;
import ungs.themes.Theme;
import ungs.transformers.RssTransformer;
import ungs.utils.ConfigUtils;
import java.util.List;

public class RssService extends Service{

    public RssService(RssTransformer transformer, RssConnector connector) {
        super(transformer, connector, ConfigUtils.RSS_FILE);
    }

    public boolean isOkServiceRss() {
        return this.connector.isAvailable();
    }

    public List<RssItemDto> getListItemsByTheme(Theme theme) {
        return this.connector.find(theme.getValueKey());
    }

    public List<RssItemDto> getAllItems() {
        List<RssItemDto> items = Lists.newArrayList();
        Theme.getThemes().forEach(theme-> items.addAll(getListItemsByTheme(theme)));
        return items;
    }

}