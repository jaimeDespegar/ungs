package ungs.filters.twitter;

import com.google.common.collect.Lists;
import ungs.connectors.impl.AbstractConnector;
import ungs.dto.Theme;
import ungs.dto.TwitterObjectDto;
import ungs.filters.filterInt.ThemeFilter;
import ungs.filters.utils.ThemeHelper;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ThemeFilterTwitter extends ThemeFilter<TwitterObjectDto,TwitterObjectDto,Theme> {

    public ThemeFilterTwitter(AbstractConnector connector, List<Theme> list) {
        super(connector, list);
    }

    @Override
    public List<TwitterObjectDto> applyFilter(List<TwitterObjectDto> input) {
        List<String> list = Lists.newArrayList();
        listInput.forEach(a -> list.addAll(a.getSubValues()));
        return input.stream().filter(i->list.contains(i.getUserName())).collect(Collectors.toList());
    }

    @Override
    protected Consumer<Theme> getConsumerTheme(List<TwitterObjectDto> result) {
        return i -> result.addAll(ThemeHelper.tweetsByUser(i, connector));
    }

}