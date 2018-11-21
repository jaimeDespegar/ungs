package ungs.filters.twitter;

import com.google.common.collect.Lists;
import ungs.connectors.AbstractConnector;
import ungs.dto.Theme;
import ungs.dto.TwitterObjectDto;
import ungs.filters.filterInt.AllFilter;
import ungs.filters.utils.ThemeHelper;
import java.util.List;

public class AllFilterTwitter extends AllFilter<TwitterObjectDto, TwitterObjectDto, String> {

    public AllFilterTwitter(AbstractConnector connector) {
        super(connector);
    }

    @Override
    public List<TwitterObjectDto> applyFilter() {
        List<TwitterObjectDto> all = Lists.newArrayList();
        Theme theme = new Theme("ALL", allUsersThemes());
        all.addAll(ThemeHelper.tweetsByUser(theme, connector));
        return all;
    }

    private List<String> allUsersThemes() {
        List<String> themes = configuration.getKeysStartWith("twitter.theme.");
        List<String> users = Lists.newArrayList();
        themes.forEach(i -> users.addAll(Lists.newArrayList(configuration.get(i).split(","))));
        return users;
    }

}