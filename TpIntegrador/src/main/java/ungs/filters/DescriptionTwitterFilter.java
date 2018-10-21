package ungs.filters;

import ungs.dto.TwitterObjectDto;

import java.util.List;
import java.util.stream.Collectors;

public class DescriptionTwitterFilter implements Filter<TwitterObjectDto, TwitterObjectDto> {

    @Override
    public List<TwitterObjectDto> applyFilter(List<TwitterObjectDto> input) {
        return input.stream().filter(i->!i.getDescription().isEmpty()).collect(Collectors.toList());
    }

}