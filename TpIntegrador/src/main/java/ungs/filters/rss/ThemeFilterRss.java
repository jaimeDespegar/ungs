package ungs.filters.rss;

import com.google.common.collect.Lists;
import ungs.connectors.AbstractConnector;
import ungs.dto.Theme;
import ungs.dto.rss.RssItemDto;
import ungs.filters.filterInt.AbstractFilter;
import java.util.List;

public class ThemeFilterRss extends AbstractFilter<Theme, RssItemDto> {

    public ThemeFilterRss(AbstractConnector connector) {
        super(connector);
    }

    @Override
    public List<RssItemDto> applyFilter(List<Theme> filters) {
        List<RssItemDto> listResult = Lists.newArrayList();
        filters.forEach(theme -> listResult.addAll(connector.find(getUrlByTheme(theme))));
        return listResult;
    }

    private String getUrlByTheme(Theme theme) {
        String property = String.format("rss.theme.%s", theme.getValue());
        return configuration.get(property);
    }

}