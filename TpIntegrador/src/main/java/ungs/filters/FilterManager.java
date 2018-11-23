package ungs.filters;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import ungs.dto.Theme;
import ungs.filters.filterFactory.FilterFactory;
import ungs.filters.filterInt.AbstractFilter;
import ungs.model.ViewFilter;
import java.util.List;
import java.util.stream.Collectors;

public class FilterManager {

    public List<AbstractFilter> getFilters(FilterFactory factory, ViewFilter viewFilter) {
        List<AbstractFilter> filters = Lists.newArrayList();

        if(viewFilter.getAll()) {
            filters.add(factory.getAllFilter());
        }
        if(viewFilter.getSelection()) {
            if (CollectionUtils.isNotEmpty(viewFilter.getThemes())) {
                filters.add(factory.getThemeFilter(viewFilter.getThemes().stream()
                                                                         .map(i->new Theme(i, factory.getSubValues(i)))
                                                                         .collect(Collectors.toList())));
            }
            if (StringUtils.isNotBlank(viewFilter.getDescription())) {
                filters.add(factory.getDescriptionFilter(viewFilter.getDescription()));
            }
        }
        return filters;
    }

}