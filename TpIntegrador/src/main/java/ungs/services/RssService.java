package ungs.services;

import com.google.common.collect.Lists;
import ungs.connectors.RssConnector;
import ungs.dto.rss.RssItemDto;
import ungs.model.Configuration;
import ungs.themes.Theme;
import ungs.transformers.RssTransformer;
import ungs.utils.ConfigUtils;
import java.util.List;

public class RssService extends Service {

    public RssService(RssTransformer transformer, RssConnector connector, Configuration configuration) {
        super(transformer, connector, configuration);
    }

    public boolean isOkServiceRss() {
        return this.connector.isAvailable();
    }

    public List<RssItemDto> getListItemsByTheme(String theme) {
        return this.connector.find(theme);
    }

    public List<RssItemDto> getAllItems() {
        List<RssItemDto> items = Lists.newArrayList();
        Theme.getThemes().forEach(theme-> items.addAll(getListItemsByTheme(theme.getValueKey())));
        return items;
    }

}