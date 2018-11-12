package ungs.filters;

import ungs.dto.TwitterObjectDto;
import ungs.filters.filterInt.Filter;
import ungs.filters.twitter.DescriptionFilterTwitter;

import java.util.List;
import java.util.stream.Collectors;

public class DescriptionTwitterFilter implements Filter<TwitterObjectDto, TwitterObjectDto> {

    private String filterDescription;
    public DescriptionTwitterFilter(String filter) {
        this.filterDescription = filter;
    }

    @Override
    public List<TwitterObjectDto> applyFilter(List<TwitterObjectDto> input) {
        return input.stream().filter(i->i.getDescription().toLowerCase().contains(filterDescription.toLowerCase()))
                             .collect(Collectors.toList());
    }

}