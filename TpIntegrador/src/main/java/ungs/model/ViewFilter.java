package ungs.model;

import java.util.List;

public class ViewFilter {

    private Boolean isAll;
    private Boolean isSelection;
    private List<String> providers;
    private List<String> themes;
    private String description;

    public ViewFilter() {}

    public ViewFilter(Boolean isAll, Boolean isSelection, List<String> providers, List<String> themes, String description) {
        this.isAll = isAll;
        this.isSelection = isSelection;
        this.providers = providers;
        this.themes = themes;
        this.description = description;
    }

    public Boolean getAll() {
        return isAll;
    }

    public void setAll(Boolean all) {
        isAll = all;
    }

    public Boolean getSelection() {
        return isSelection;
    }

    public void setSelection(Boolean selection) {
        isSelection = selection;
    }

    public List<String> getProviders() {
        return providers;
    }

    public void setProviders(List<String> providers) {
        this.providers = providers;
    }

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}