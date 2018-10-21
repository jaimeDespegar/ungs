package ungs.filters;

import ungs.dto.ThemeDto;

public abstract class ThemeFilter implements Filter{

    private ThemeDto themeDto;

    public ThemeFilter(ThemeDto themeDto) {
        this.themeDto = themeDto;
    }

}