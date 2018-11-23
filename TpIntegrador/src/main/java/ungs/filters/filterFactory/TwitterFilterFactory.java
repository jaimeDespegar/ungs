package ungs.filters.filterFactory;

import com.google.common.collect.Lists;
import ungs.connectors.impl.AbstractConnector;
import ungs.dto.Theme;
import ungs.filters.filterInt.*;
import ungs.filters.twitter.*;
import java.util.List;

public class TwitterFilterFactory implements FilterFactory {

    private AbstractConnector twitterConnector;

    public TwitterFilterFactory(AbstractConnector twitterConnector) {
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

    @Override
    public List<String> getSubValues(String i) {
        return Lists.newArrayList(twitterConnector.getConfiguration().get("twitter.theme."+ i).split(","));
    }
}