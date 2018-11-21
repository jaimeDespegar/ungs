package ungs.filters.twitter;

import com.google.common.collect.Lists;
import ungs.connectors.AbstractConnector;
import ungs.dto.Theme;
import ungs.dto.TwitterObjectDto;
import ungs.filters.filterInt.ThemeFilter;
import ungs.filters.utils.ThemeHelper;
import java.util.List;
import java.util.function.Consumer;

public class ThemeFilterTwitter extends ThemeFilter<TwitterObjectDto,TwitterObjectDto,Theme> {

    public ThemeFilterTwitter(AbstractConnector connector, List<Theme> list) {
        super(connector, list);
    }

    @Override
    public List<TwitterObjectDto> applyFilter(List<TwitterObjectDto> input) {
        List<TwitterObjectDto> result = Lists.newArrayList();
        listInput.forEach(theme -> {
            theme.getSubValues().forEach(user -> {
                input.stream().filter(tweet -> tweet.getUserName().equalsIgnoreCase(user))
                              .map(a -> result.add(a));
            });
        });
        return result;
    }

    @Override
    protected Consumer<Theme> getConsumerTheme(List<TwitterObjectDto> result) {
        return i -> result.addAll(ThemeHelper.tweetsByUser(i, connector));
    }

}