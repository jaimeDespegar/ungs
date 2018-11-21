package ungs.connectors;

import com.google.common.collect.Lists;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import ungs.dto.TwitterObjectDto;
import ungs.filters.ConditionFilter;
import ungs.helpers.TwitterHelper;
import ungs.model.Configuration;
import ungs.utils.ConfigUtils;
import ungs.utils.ResponseUtil;
import java.util.List;

public class TwitterConnector extends AbstractConnector<TwitterObjectDto> {

    private Twitter twitter;
    private TwitterHelper helper;
    private Integer COUNT_ELEMENTS;

    public TwitterConnector(TwitterHelper helper) {
        this.helper = helper;
    }

    public TwitterConnector() {
        this.helper = new TwitterHelper();
    }

    public TwitterConnector(Configuration configuration) {
        this.configuration = configuration;
        this.COUNT_ELEMENTS = configuration.getNumber(ConfigUtils.TWITTER_COUNT);
        this.helper = new TwitterHelper();
    }

    @Override
    public void initConnection() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(configuration.get(ConfigUtils.TWITTER_API_KEY))
                                .setOAuthConsumerSecret(configuration.get(ConfigUtils.TWITTER_API_SECRET_KEY))
                                .setOAuthAccessToken(configuration.get(ConfigUtils.TWITTER_TOKEN_KEY))
                                .setOAuthAccessTokenSecret(configuration.get(ConfigUtils.TWITTER_TOKEN_SECRET_KEY));
        TwitterFactory tf = new TwitterFactory(cb.build());
        this.twitter = tf.getInstance();
    }

    @Override
    public boolean isAvailable() {
        try {
            logger.info("twitter.getScreenName() : " + twitter.getScreenName());
            return twitter.getScreenName().length()>0;
        } catch (Exception e) {
            logger.warn("El usuario no esta habilitado, error");
            return false;
        }
    }


    public List<TwitterObjectDto> find(String valueToFind, Integer value) {
        return ResponseUtil.getListItemsBySizeConfiguration(find(valueToFind), value);
    }

    @Override
    public List<TwitterObjectDto> find(String valueToFind) {
        try {
            List<Status> result = searchtweets(valueToFind);
            return helper.transformToTwitterModel(result);
        } catch (TwitterException te) {
            logger.error("Mensaje error Connector Twitter.", te);
        }
        return Lists.newArrayList();
    }

    public List<TwitterObjectDto> findByUser(String user) {
        return this.find("from:" + user);
    }

    public List<TwitterObjectDto> findByUserAndDescription(String user, String description) {
        return this.find("from:" + user + " " + description);
    }

    public List<TwitterObjectDto> findByFilter(ConditionFilter filter) {
        return this.find(filter.getValue());
    }

    public List<TwitterObjectDto> findByHashtag(String hashtag) {
        return this.find("#" + hashtag);
    }

    public List<Status> searchtweets(String value) throws TwitterException {
        Query query = new Query(value);
        QueryResult result = twitter.search(query);
        return result.getTweets();
    }

}