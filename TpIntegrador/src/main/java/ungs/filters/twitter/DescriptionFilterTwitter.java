package ungs.filters.twitter;

import ungs.connectors.AbstractConnector;
import ungs.dto.TwitterObjectDto;
import ungs.filters.filterInt.AbstractFilter;
import java.util.List;
import java.util.stream.Collectors;

public class DescriptionFilterTwitter extends AbstractFilter<TwitterObjectDto, TwitterObjectDto> {

    private String descriptionFilter = "";

    public DescriptionFilterTwitter(AbstractConnector connector, String descriptionFilter) {
        super(connector);
        this.descriptionFilter = descriptionFilter;
    }

    @Override
    public List<TwitterObjectDto> applyFilter(List<TwitterObjectDto> input) {
        return input.stream().filter(i -> descriptionFilter.equalsIgnoreCase(i.getDescription()))
                             .collect(Collectors.toList());
    }

}