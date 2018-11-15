package ungs.utils;

import org.apache.commons.collections4.CollectionUtils;
import java.util.List;
import java.util.stream.Collectors;

public class ResponseUtil {

    public static <T> List<T> getListItemsBySizeConfiguration(List<T> items, Integer count) {
        if (CollectionUtils.isNotEmpty(items) && count!=null) {
            return items.stream().limit(count)
                                 .collect(Collectors.toList());
        }
        return items;
    }

}