package ungs.dto.rss;

public class RssItemDto {

    private String title;
    private String link;
    private String description;

    public RssItemDto(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
    }

    public RssItemDto() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "RssItemDto{" + "title='" + title + '\'' + ", description='" + description + '\'' + ", link='" + link + '\'' + '}';
    }
}