package ungs.themes;

public enum Theme {

    DEPORTES("rss.theme.sport"),
    MUSICA("rss.theme.music"),
    POLITICA("rss.theme.politics");

    private String valueKey;

    private Theme(String value) {
        this.valueKey = value;
    }

    public String getValueKey() {
        return valueKey;
    }
}
