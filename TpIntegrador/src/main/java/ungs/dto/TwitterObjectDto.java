package ungs.dto;

import java.util.Date;

public class TwitterObjectDto {

    private Date date;
    private String description;
    private String userName;

    public TwitterObjectDto(Date date, String description, String userName) {
        this.date = date;
        this.description = description;
        this.userName = userName;
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
        return "TwitterObjectDto{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}