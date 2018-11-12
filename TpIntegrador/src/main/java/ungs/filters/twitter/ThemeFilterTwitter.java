package ungs.filters.twitter;

import com.google.common.collect.Lists;
import ungs.connectors.AbstractConnector;
import ungs.dto.Theme;
import ungs.dto.TwitterObjectDto;
import ungs.filters.filterInt.AbstractFilter;
import ungs.filters.utils.ThemeHelper;
import java.util.List;

public class ThemeFilterTwitter extends AbstractFilter<Theme, TwitterObjectDto> {

    public ThemeFilterTwitter(AbstractConnector connector) {
        super(connector);
    }

    @Override
    public List<TwitterObjectDto> applyFilter(List<Theme> filters) {
        List<TwitterObjectDto> result = Lists.newArrayList();
       // TODO ESTO LO TIENE QUE HACER EL SERVICE ANTES ->  ThemeHelper.setValuesByTheme(filters, configuration);
        filters.forEach(i -> result.addAll(ThemeHelper.tweetsByUser(i, connector)));
        return result;
    }

}