package ungs.model;

import ungs.themes.Theme;

public class InformationDto {

    private Origin origin;
    private Theme  theme;
    private String description;
    private String date;

    public InformationDto(Origin origin, Theme theme, String description, String date) {
        this.origin = origin;
        this.theme = theme;
        this.description = description;
        this.date = date;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}