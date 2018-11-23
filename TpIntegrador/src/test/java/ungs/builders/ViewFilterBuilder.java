package ungs.builders;

import ungs.model.ViewFilter;
import java.util.Arrays;

public class ViewFilterBuilder {

    private ViewFilter instance;

    private ViewFilterBuilder() {
        this.instance = new ViewFilter();
    }

    public static ViewFilterBuilder create() {
        return new ViewFilterBuilder();
    }

    public ViewFilterBuilder dataDefaultEmpty() {
        this.instance.setAll(false);
        this.instance.setSelection(false);
        this.instance.setProviders(Arrays.asList());
        this.instance.setThemes(Arrays.asList());
        this.instance.setDescription(null);
        return this;
    }

    public ViewFilterBuilder dataDefault() {
        this.instance.setAll(false);
        this.instance.setSelection(true);
        this.instance.setProviders(Arrays.asList("rss", "twitter"));
        this.instance.setThemes(Arrays.asList("sport","politics"));
        this.instance.setDescription(null);
        return this;
    }

    public ViewFilterBuilder setAll(Boolean all) {
        this.instance.setAll(all);
        return this;
    }

    public ViewFilterBuilder withThemes(String ... themes) {
        this.instance.setThemes(Arrays.asList(themes));
        return this;
    }

    public ViewFilterBuilder withDescription(String description) {
        this.instance.setDescription(description);
        return this;
    }

    public ViewFilter build() {
        return instance;
    }

}