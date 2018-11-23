package ungs.services;

import com.google.common.collect.Lists;
import ungs.connectors.impl.AbstractConnector;
import ungs.connectors.impl.RssConnector;
import ungs.dto.rss.RssItemDto;
import ungs.filters.FilterExecutor;
import ungs.filters.FilterManager;
import ungs.filters.filterFactory.interfaz.FilterFactory;
import ungs.filters.filterFactory.impl.RssFilterFactory;
import ungs.model.Configuration;
import ungs.themes.Theme;
import ungs.transformers.RssTransformer;
import ungs.utils.ResponseUtil;
import java.util.List;

public class RssService extends Service<AbstractConnector, RssItemDto, RssTransformer> {

    public RssService(RssTransformer transformer, AbstractConnector connector, FilterFactory filterFactory, FilterExecutor filterExecutor, Configuration configuration) {
        super(transformer, connector, filterFactory, filterExecutor, configuration);
    }

    public RssService(Configuration configuration) {
        super(configuration);
        this.transformer = new RssTransformer();
        this.connector = new RssConnector(configuration);
        this.filterFactory = new RssFilterFactory(connector);
        this.filterExecutor = new FilterExecutor();
        this.filterManager = new FilterManager();
        this.init();
    }

    public List<RssItemDto> getListItemsByTheme(String theme) {
        return this.connector.find(theme);
    }

    public List<RssItemDto> getAllItems() {
        List<RssItemDto> items = Lists.newArrayList();
        Theme.getThemes().forEach(theme-> items.addAll(getListItemsByTheme(theme.getValueKey())));
        return ResponseUtil.getListItemsBySizeConfiguration(items, this.getCountValues());
    }

    @Override
    public List<RssItemDto> getData() {
        return getAllItems();
    }

}