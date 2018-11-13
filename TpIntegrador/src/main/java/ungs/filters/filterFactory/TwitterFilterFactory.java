package ungs.filters.filterFactory;

import ungs.connectors.TwitterConnector;
import ungs.dto.Theme;
import ungs.filters.filterInt.*;
import ungs.filters.twitter.*;
import java.util.List;

public class TwitterFilterFactory implements FilterFactory {

    private TwitterConnector twitterConnector;

    public TwitterFilterFactory(TwitterConnector twitterConnector) {
        this.twitterConnector = twitterConnector;
    }

    @Override
    public DescriptionFilter getDescriptionFilter(String description) {
        return new DescriptionFilterTwitter(twitterConnector, description);
    }

    @Override
    public ThemeFilter getThemeFilter(List<Theme> list) {
        return new ThemeFilterTwitter(twitterConnector, list);
    }

    @Override
    public AllFilter getAllFilter() {
        return new AllFilterTwitter(twitterConnector);
    }

}