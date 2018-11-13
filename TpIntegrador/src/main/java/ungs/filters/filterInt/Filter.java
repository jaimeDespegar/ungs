package ungs.filters.filterInt;

import java.util.List;

public interface Filter<OUTPUT> {

    List<OUTPUT> applyFilter();

}