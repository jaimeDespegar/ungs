package ungs.filters.filterFactory;

import ungs.dto.Theme;
import ungs.filters.filterInt.AllFilter;
import ungs.filters.filterInt.DescriptionFilter;
import ungs.filters.filterInt.ThemeFilter;
import java.util.List;

public interface FilterFactory {

    DescriptionFilter getDescriptionFilter(String description);
    ThemeFilter getThemeFilter(List<Theme> themes);
    AllFilter getAllFilter();

    List<String> getSubValues(String i);
}