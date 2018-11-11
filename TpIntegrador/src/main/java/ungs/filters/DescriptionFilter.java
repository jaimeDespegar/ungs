package ungs.filters;

import ungs.dto.rss.RssItemDto;
import ungs.filters.filterInt.Filter;

import java.util.List;
import java.util.stream.Collectors;

public class DescriptionFilter implements Filter<RssItemDto, RssItemDto> {

    private String value;

    public DescriptionFilter(String valueToFilter) {
        this.value = valueToFilter;
    }

    @Override
    public List<RssItemDto> applyFilter(List<RssItemDto> input) {
        return input.stream().filter(i->i.getDescription().contains(value))
                             .collect(Collectors.toList());
    }

}
