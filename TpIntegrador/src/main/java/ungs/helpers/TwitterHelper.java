package ungs.helpers;

import com.google.common.collect.Lists;
import twitter4j.Status;
import ungs.dto.TwitterObjectDto;
import java.util.List;
import java.util.stream.Collectors;

public class TwitterHelper {

    public List<TwitterObjectDto> transformToTwitterModel(List<Status> items) {
        List<TwitterObjectDto> tweets = Lists.newArrayList();
        items.forEach(i-> tweets.add(transform(i)));
        return tweets;
    }

    private TwitterObjectDto transform(Status item) {
        return new TwitterObjectDto(item.getCreatedAt(), item.getText(), item.getUser().getScreenName(), getListHashtags(item));
    }

    private List<String> getListHashtags(Status item) {
        return Lists.newArrayList(item.getHashtagEntities()).stream()
                                                            .map(i->"#"+i.getText())
                                                            .collect(Collectors.toList());
    }
}