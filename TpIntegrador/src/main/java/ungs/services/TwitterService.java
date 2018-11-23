package ungs.services;

import com.google.common.collect.Lists;
import ungs.connectors.impl.AbstractConnector;
import ungs.connectors.impl.TwitterConnector;
import ungs.connectors.interfaz.TwitterSpecificConnector;
import ungs.dto.TwitterObjectDto;
import ungs.filters.FilterExecutor;
import ungs.filters.FilterManager;
import ungs.filters.filterFactory.TwitterFilterFactory;
import ungs.model.Configuration;
import ungs.transformers.TwitterTransformer;
import ungs.utils.ConfigUtils;
import ungs.utils.ResponseUtil;
import java.util.Arrays;
import java.util.List;

public class TwitterService extends Service<AbstractConnector, TwitterObjectDto, TwitterTransformer> {

    public TwitterService(TwitterTransformer transformer, AbstractConnector connector, TwitterFilterFactory factory, FilterExecutor filterExecutor, Configuration configuration) {
        super(transformer, connector, factory, filterExecutor, configuration);
    }

    public TwitterService(Configuration configuration) {
        super(configuration);
        this.transformer = new TwitterTransformer();
        this.connector = new TwitterConnector(configuration);
        this.filterFactory = new TwitterFilterFactory(connector);
        this.filterExecutor = new FilterExecutor();
        this.filterManager = new FilterManager();
        this.init();
    }

    public boolean isOkServiceTwitter() {
        return this.connector.isAvailable();
    }

    public List<TwitterObjectDto> getAllTweets() {
        List<String> allUsers = this.getAllUsersNames();
        List<TwitterObjectDto> tweets = Lists.newArrayList();
        allUsers.forEach(user -> tweets.addAll(findByUser(user)));
        return ResponseUtil.getListItemsBySizeConfiguration(tweets, this.getCountValues());
    }

    private List<TwitterObjectDto> findByUser(String user) {
        return ((TwitterSpecificConnector) connector).findByUser(user);
    }

    @Override
    public List<TwitterObjectDto> getData(){
        return getAllTweets();
    }

    private List<String> getAllUsersNames() {
        List<String> allUsers = Lists.newArrayList();
        allUsers.addAll(Arrays.asList(configuration.get(ConfigUtils.TWITTER_USERS_POLITICS).split(",")));
        allUsers.addAll(Arrays.asList(configuration.get(ConfigUtils.TWITTER_USERS_SPORT).split(",")));
        return allUsers;
    }

}