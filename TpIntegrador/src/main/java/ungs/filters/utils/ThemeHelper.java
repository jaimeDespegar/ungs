package ungs.filters.utils;

import com.google.common.collect.Lists;
import ungs.connectors.impl.AbstractConnector;
import ungs.dto.Theme;
import ungs.dto.TwitterObjectDto;
import ungs.model.Configuration;
import java.util.List;

public class ThemeHelper {

    private static final String PROPERTY_THEME = "twitter.theme.%s";
    private static final String FIND_BY_USER = "from: %s";

    public static void setValuesByTheme(List<Theme> themes, Configuration configuration) {
        themes.forEach(theme -> {
            String property = String.format(PROPERTY_THEME, theme.getValue());
            theme.setSubValues(Lists.newArrayList(configuration.get(property).split(",")));
        });
    }

    public static List<TwitterObjectDto> tweetsByUser(Theme theme, AbstractConnector connector) {
        List<TwitterObjectDto> list = Lists.newArrayList();
        theme.getSubValues().forEach(user -> list.addAll(connector.find(String.format(FIND_BY_USER, user))));
        return list;
    }

}