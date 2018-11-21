package ungs.filters.rss;

import ungs.connectors.AbstractConnector;
import ungs.dto.Theme;
import ungs.dto.rss.RssItemDto;
import ungs.filters.filterInt.ThemeFilter;
import java.util.List;
import java.util.function.Consumer;

public class ThemeFilterRss extends ThemeFilter<RssItemDto, RssItemDto, Theme> {

    public ThemeFilterRss(AbstractConnector connector, List<Theme> list) {
        super(connector, list);
    }


    @Override
    public List<RssItemDto> applyFilter(List<RssItemDto> list) {
        return list;
    }

    @Override
    protected Consumer<Theme> getConsumerTheme(List<RssItemDto> result) {
        return theme -> result.addAll(connector.find(getUrlByTheme(theme)));
    }

    private String getUrlByTheme(Theme theme) {
        return String.format("rss.theme.%s", theme.getValue());
    }

}