package ungs.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import ungs.utils.ConfigUtils;
import ungs.utils.ReaderValuesConfiguration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Configuration {

    private Map<String, String> values = Maps.newHashMap();

    public Configuration() {}

    public Configuration(String pathFile) {
        this.values = ReaderValuesConfiguration.getValuesProperties(pathFile);
    }

    public Configuration(Map configurations) {
        this.values = configurations;
    }

    public String get(String key) {
        return values.get(key);
    }

    public Integer getNumber(String key) {
        try {
            return Integer.valueOf(get(key));
        } catch (NumberFormatException nfe) {
            return ConfigUtils.DEFAULT_COUNT;
        }
    }

    public List<String> getKeysStartWith(String s) {
        return values.keySet()
                     .stream()
                     .filter(i->i.startsWith(s))
                     .collect(Collectors.toList());
    }

    public List<String> getValuesByListKeys(List<String> keys) {
        List<String> list = Lists.newArrayList();
        keys.forEach(key -> list.add(values.get(key)));
        return list;
    }

}