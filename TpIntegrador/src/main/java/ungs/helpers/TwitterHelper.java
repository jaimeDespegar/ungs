package ungs.helpers;

import com.google.common.collect.Lists;
import twitter4j.Status;
import ungs.dto.TwitterObjectDto;

import java.util.List;

public class TwitterHelper {

    public List<TwitterObjectDto> transformToTwetterModel(List<Status> items) {
        List<TwitterObjectDto> tweets = Lists.newArrayList();
        items.forEach(i-> tweets.add(transform(i)));
        return tweets;
    }

    private TwitterObjectDto transform(Status item) {
        /*
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        String dateInString = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(item.getCreatedAt());
        DateTime dateTime = DateTime.parse(dateInString, formatter).withZone(DateTimeZone.forID("Asia/Kolkata"));
        */
        return new TwitterObjectDto(item.getCreatedAt(), item.getText(), item.getUser().getScreenName());
    }

}
