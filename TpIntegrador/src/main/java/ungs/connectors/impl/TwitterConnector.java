package ungs.connectors.impl;

import org.apache.commons.lang3.StringUtils;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import ungs.connectors.interfaz.TwitterSpecificConnector;
import ungs.dto.TwitterObjectDto;
import ungs.helpers.TwitterHelper;
import ungs.model.Configuration;
import ungs.utils.*;
import ungs.utils.exceptions.ConnectionException;
import java.util.List;

public class TwitterConnector extends AbstractConnector<TwitterObjectDto> implements TwitterSpecificConnector {

    private Twitter twitter;
    private TwitterHelper helper;

    public TwitterConnector() {
        this.helper = new TwitterHelper();
    }

    public TwitterConnector(Configuration configuration) {
        this.configuration = configuration;
        this.helper = new TwitterHelper();
    }

    @Override
    public void initConnection() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(configuration.get(ConfigUtils.TWITTER_API_KEY))
                                .setOAuthConsumerSecret(configuration.get(ConfigUtils.TWITTER_API_SECRET_KEY))
                                .setOAuthAccessToken(configuration.get(ConfigUtils.TWITTER_TOKEN_KEY))
                                .setOAuthAccessTokenSecret(configuration.get(ConfigUtils.TWITTER_TOKEN_SECRET_KEY))
                                .setHttpReadTimeout(configuration.getNumber(ConfigUtils.SERVICE_TIMEOUT));
        TwitterFactory tf = new TwitterFactory(cb.build());
        this.twitter = tf.getInstance();
    }

    @Override
    public boolean isAvailable() {
        try {
            logger.info("twitter.getScreenName() : " + twitter.getScreenName());
            return StringUtils.isBlank(twitter.getScreenName());
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
            throw new ConnectionException("The service Twitter is not available", te);
        }
    }

    @Override
    public List<TwitterObjectDto> findByUser(String user) {
        return this.doFind("from:" + user);
    }

    public List<Status> searchtweets(String value) throws TwitterException {
        Query query = new Query(value);
        QueryResult result = twitter.search(query);
        return result.getTweets();
    }

}