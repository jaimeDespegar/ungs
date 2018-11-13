package ungs.filters.twitter;

import ungs.connectors.AbstractConnector;
import ungs.dto.Theme;
import ungs.dto.TwitterObjectDto;
import ungs.filters.filterInt.ThemeFilter;
import ungs.filters.utils.ThemeHelper;
import java.util.List;
import java.util.function.Consumer;

public class ThemeFilterTwitter extends ThemeFilter<Theme, TwitterObjectDto> {

    public ThemeFilterTwitter(AbstractConnector connector, List<Theme> list) {
        super(connector, list);
    }

    @Override
    protected Consumer<Theme> getConsumerTheme(List<TwitterObjectDto> result) {
        return i -> result.addAll(ThemeHelper.tweetsByUser(i, connector));
    }

}