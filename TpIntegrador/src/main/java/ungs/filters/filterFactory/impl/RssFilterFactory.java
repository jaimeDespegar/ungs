package ungs.filters.filterFactory.impl;

import com.google.common.collect.Lists;
import ungs.connectors.impl.AbstractConnector;
import ungs.dto.Theme;
import ungs.filters.filterFactory.interfaz.FilterFactory;
import ungs.filters.filterInt.*;
import ungs.filters.rss.*;
import java.util.List;

public class RssFilterFactory implements FilterFactory {

    private AbstractConnector rssConnector;

    public RssFilterFactory(AbstractConnector rssConnector) {
        this.rssConnector = rssConnector;
    }

    @Override
    public DescriptionFilter getDescriptionFilter(String description) {
        return new DescriptionFilterRss(rssConnector, description);
    }

    @Override
    public ThemeFilter getThemeFilter(List<Theme> themes) {
        return new ThemeFilterRss(rssConnector, themes);
    }

    @Override
    public AllFilter getAllFilter() {
        return new AllFilterRss(rssConnector);
    }

    @Override
    public List<String> getSubValues(String i) {
        return Lists.newArrayList("rss.theme." + i);
    }

}