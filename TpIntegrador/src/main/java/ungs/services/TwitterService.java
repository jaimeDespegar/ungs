package ungs.services;

import ungs.connectors.TwitterConnector;
import ungs.dto.TwitterObjectDto;
import ungs.utils.ConfigUtils;

import java.util.List;

public class TwitterService extends Service {

    public TwitterService(TwitterConnector connector) {
        super(connector, ConfigUtils.TWITTER_FILE);
    }

    public boolean isOkServiceTwitter() {
        return this.connector.isAvailable();
    }

    public List<TwitterObjectDto> getListTweetsByUser() {
        return this.connector.find("");
    }

}
