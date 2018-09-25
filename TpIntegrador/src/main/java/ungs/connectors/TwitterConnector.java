package ungs.connectors;

import com.google.common.collect.Lists;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import ungs.dto.TwitterObjectDto;
import ungs.helpers.TwitterHelper;
import ungs.utils.ConfigUtils;
import java.util.List;

public class TwitterConnector extends AbstractConnector<TwitterObjectDto> {

    private Twitter twitter;
    private TwitterHelper helper;

    public TwitterConnector(TwitterHelper helper) {
        this.helper = helper;
    }

    public TwitterConnector() {
        this.helper = new TwitterHelper();
    }

    @Override
    public void init() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(configuration.get(ConfigUtils.TWITTER_API_KEY))
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

    @Override
    public List<TwitterObjectDto> find(String valueToFind) {
        try {
            return helper.transformToTwetterModel(searchtweets(valueToFind));
        } catch (TwitterException te) {
            te.printStackTrace(); // TODO hacer algo mas !
        }
        return Lists.newArrayList();
    }


    public List<TwitterObjectDto> findByUser(String user) {
        return this.find("from:" + user);
    }

    public List<TwitterObjectDto> findByUserAndDescription(String user, String description) {
        return this.find("from:" + user + " " + description);
    }

    public List<TwitterObjectDto> findByDescription(String description) {
        return this.find( description);
    }

    public boolean isValidUser() {
        String urlUserConnection = "ACA TIENE QUE ESTAR ARMADO EL GET PARA VALIDAR EL USUARIO";
        // TODO MAS PERMISOS/CONFIGURACIONES QUE SE NECESITEN
        return isServiceOk(urlUserConnection);
    }

    public List<Status> searchtweets(String value) throws TwitterException {
        Query query = new Query(value);
        query.setCount(configuration.getNumber(ConfigUtils.TWITTER_COUNT));
        QueryResult result = twitter.search(query);
        System.out.println("SE HIZO ESTA BUSQUEDA: " + result.getQuery());
        return result.getTweets();
    }

}