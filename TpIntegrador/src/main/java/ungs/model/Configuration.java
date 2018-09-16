package ungs.model;

import com.google.common.collect.Maps;
import java.util.Map;

public class Configuration {

    private Map<String, String> values = Maps.newHashMap();

    public Configuration() {}

    public Configuration(Map configurations) {
        this.values = configurations;
    }

    public String getValueByKey(String key) {
        return values.get(key);
    }

    public void setValues(Map values) {
        this.values = values;
    }

}