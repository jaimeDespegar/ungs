package ungs.themes;

import com.google.common.collect.Lists;

import java.util.List;

public enum Theme {

    DEPORTES("rss.theme.sport"),
    POLITICA("rss.theme.politics");

    private String valueKey;

    private Theme(String value) {
        this.valueKey = value;
    }

    public String getValueKey() {
        return valueKey;
    }

    public static List<Theme> getThemes() {
        return Lists.newArrayList(values());
    }

}