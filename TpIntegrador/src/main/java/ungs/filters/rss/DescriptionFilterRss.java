package ungs.filters.rss;

import ungs.connectors.AbstractConnector;
import ungs.dto.rss.RssItemDto;
import ungs.filters.filterInt.AbstractFilter;
import java.util.List;
import java.util.stream.Collectors;

public class DescriptionFilterRss extends AbstractFilter<RssItemDto, RssItemDto> {

    private String descrption;

    public DescriptionFilterRss(AbstractConnector connector, String filterDescription) {
        super(connector);
        this.descrption = filterDescription;
    }

    @Override
    public List<RssItemDto> applyFilter(List<RssItemDto> filters) {
        return filters.stream().filter(item -> item.getDescription().contains(descrption))
                               .collect(Collectors.toList());
    }

}