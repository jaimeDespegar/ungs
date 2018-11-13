package ungs.filters;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import ungs.dto.Theme;
import ungs.filters.filterFactory.FilterFactory;
import ungs.filters.filterInt.Filter;
import ungs.model.ViewFilter;
import ungs.services.Service;
import java.util.List;
import java.util.stream.Collectors;

public class FilterManager {

    public List<Filter> getFilters(Service service, ViewFilter viewFilter) {
        List<Filter> filters = Lists.newArrayList();
        FilterFactory factory = service.getFilterFactory();

        if(viewFilter.getAll()) {
            filters.add(factory.getAllFilter());
        }
        if(viewFilter.getSelection()) {
            if (StringUtils.isNotBlank(viewFilter.getDescription())) {
                filters.add(factory.getDescriptionFilter(viewFilter.getDescription()));
            }
            if (CollectionUtils.isNotEmpty(viewFilter.getThemes())) {
                filters.add(factory.getThemeFilter(viewFilter.getThemes().stream()
                                                                         .map(i->new Theme(i))
                                                                         .collect(Collectors.toList())));
            }
        }
        return filters;
    }

}