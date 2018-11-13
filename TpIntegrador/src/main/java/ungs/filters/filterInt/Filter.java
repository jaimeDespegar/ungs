package ungs.filters.filterInt;

import java.util.List;

public interface Filter<INPUT, OUTPUT> {

    List<OUTPUT> applyFilter();
    List<OUTPUT> applyFilter(List<INPUT> list);

}