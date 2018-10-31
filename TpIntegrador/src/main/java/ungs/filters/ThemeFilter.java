package ungs.filters;

import ungs.dto.Theme;

public abstract class ThemeFilter implements Filter{

    private Theme theme;

    public ThemeFilter(Theme theme) {
        this.theme = theme;
    }

}