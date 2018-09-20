package ungs.services;

import com.google.common.collect.Lists;
import ungs.connectors.TwitterConnector;
import ungs.dto.TwitterObjectDto;
import ungs.themes.Theme;
import ungs.transformers.TwitterTransformer;
import ungs.utils.ConfigUtils;
import java.util.List;

public class TwitterService extends Service<TwitterConnector, TwitterObjectDto, TwitterTransformer> {

    public TwitterService(TwitterTransformer transformer, TwitterConnector connector) {
        super(transformer, connector, ConfigUtils.TWITTER_FILE);
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
