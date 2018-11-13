package ungs.services;

import com.google.common.collect.Lists;
import ungs.connectors.TwitterConnector;
import ungs.dto.TwitterObjectDto;
import ungs.filters.ConditionFilter;
import ungs.filters.filterFactory.TwitterFilterFactory;
import ungs.model.Configuration;
import ungs.model.InformationDto;
import ungs.transformers.TwitterTransformer;
import ungs.utils.ConfigUtils;
import java.util.Arrays;
import java.util.List;

public class TwitterService extends Service<TwitterConnector, TwitterObjectDto, TwitterTransformer> {

    public TwitterService(TwitterTransformer transformer, TwitterConnector connector, TwitterFilterFactory factory, Configuration configuration) {
        super(transformer, connector, factory, configuration);
    }

    public TwitterService(){}

    public boolean isOkServiceTwitter() {
        return this.connector.isAvailable();
    }

    public List<TwitterObjectDto> getTweetsByUser(String user) {
        return this.connector.findByUser(user);
    }

    public List<TwitterObjectDto> getTweetsByConditionFilter(ConditionFilter filter) {
        return this.connector.findByFilter(filter);
    }

    public List<TwitterObjectDto> getAllTweets() {
        List<String> allUsers = this.getAllUsersNames();
        List<TwitterObjectDto> tweets = Lists.newArrayList();
        allUsers.forEach(user -> tweets.addAll(this.connector.findByUser(user)));
        return tweets;
    }

    @Override
    public List<InformationDto> getData() {
        return this.getInformation(getAllTweets());
    }

    private List<String> getAllUsersNames() {
        List<String> allUsers = Lists.newArrayList();
        allUsers.addAll(Arrays.asList(configuration.get(ConfigUtils.TWITTER_USERS_POLITICS).split(",")));
        allUsers.addAll(Arrays.asList(configuration.get(ConfigUtils.TWITTER_USERS_SPORT).split(",")));
        return allUsers;
    }

}