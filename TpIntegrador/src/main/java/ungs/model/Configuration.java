package ungs.model;

import com.google.common.collect.Maps;
import ungs.utils.ConfigUtils;
import ungs.utils.ReaderValuesConfiguration;
import java.util.Map;

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

}