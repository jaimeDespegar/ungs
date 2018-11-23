package ungs.model;


public class InformationDto {

    private String id;
    private Origin origin;
    private String  theme;
    private String description;
    private String date;

    public InformationDto(String id, Origin origin, String theme, String description, String date) {
        this.id = id;
        this.origin = origin;
        this.theme = theme;
        this.description = description;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
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