package ungs.services;

import com.google.common.collect.Lists;
import ungs.connectors.RssConnector;
import ungs.dto.rss.RssItemDto;
import ungs.model.Configuration;
import ungs.model.InformationDto;
import ungs.themes.Theme;
import ungs.transformers.RssTransformer;
import java.util.List;

public class RssService extends Service<RssConnector, RssItemDto, RssTransformer> {

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

    @Override
    public List<InformationDto> getData() {
        return this.getInformation(getAllItems());
    }

}