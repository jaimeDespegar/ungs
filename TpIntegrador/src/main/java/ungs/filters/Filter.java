package ungs.filters;

import java.util.List;

public interface Filter<INPUT, OUTPUT > {

    List<OUTPUT> applyFilter(List<INPUT> input);


}