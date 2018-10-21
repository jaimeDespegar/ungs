package ungs.services;

import com.google.common.collect.Lists;
import org.apache.commons.codec.binary.StringUtils;
import ungs.connectors.TwitterConnector;
import ungs.dto.TwitterObjectDto;
import ungs.filters.ConditionFilter;
import ungs.model.Configuration;
import ungs.themes.Theme;
import ungs.transformers.TwitterTransformer;
import ungs.utils.ConfigUtils;
import java.util.List;

public class TwitterService extends Service<TwitterConnector, TwitterObjectDto, TwitterTransformer> {

    public TwitterService(TwitterTransformer transformer, TwitterConnector connector, Configuration configuration) {
        super(transformer, connector, configuration);
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

    public List<TwitterObjectDto> getTweetsByConditionFilter(ConditionFilter filter) {
        return this.connector.findByFilter(filter);
    }

    public List<TwitterObjectDto> getAllTweets() {
        return this.connector.find("");
    }

}