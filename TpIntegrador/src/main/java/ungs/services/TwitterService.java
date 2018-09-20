package ungs.services;

import com.google.common.collect.Lists;
import ungs.connectors.TwitterConnector;
import ungs.dto.TwitterObjectDto;
import ungs.themes.Theme;
import ungs.utils.ConfigUtils;
import java.util.List;

public class TwitterService extends Service<TwitterConnector> {

    public TwitterService(TwitterConnector connector) {
        super(connector, ConfigUtils.TWITTER_FILE);
    }

    public boolean isOkServiceTwitter() {
        return this.connector.isAvailable();
    }

    public List<TwitterObjectDto> getTweetsByTheme(Theme theme) {
        List<TwitterObjectDto> tweets = Lists.newArrayList();

        return tweets;
    }

    public List<TwitterObjectDto> getTweetsByUser(String user) {
        return this.connector.findByUser(user);
    }

    public List<TwitterObjectDto> getTweetsByDescription(String description) {
        return this.connector.findByDescription(description);
    }

    public List<TwitterObjectDto> getTweetsByUserAndDescription(String user, String description) {
        return this.connector.findByUserAndDescription(user, description);
    }

}
