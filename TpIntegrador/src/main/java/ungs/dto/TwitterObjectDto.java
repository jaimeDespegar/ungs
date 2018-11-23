package ungs.dto;

import java.util.Date;
import java.util.List;

public class TwitterObjectDto {

    private Date date;
    private String description;
    private String userName;
    private List<String> hashtags;

    public TwitterObjectDto(Date date, String description, String userName, List<String> hashtags) {
        this.date = date;
        this.description = description;
        this.userName = userName;
        this.hashtags = hashtags;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        hashtags.forEach(i->System.out.println(i));
        return "TwitterObjectDto{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}