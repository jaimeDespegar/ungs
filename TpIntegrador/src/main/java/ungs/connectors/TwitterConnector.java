package ungs.connectors;

import com.google.common.collect.Lists;
import org.apache.http.client.utils.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.JodaTimePermission;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import ungs.dto.TwitterObjectDto;
import ungs.utils.ConfigUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TwitterConnector extends AbstractConnector<TwitterObjectDto> {

    private Twitter twitter;

    public TwitterConnector() {

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
        return false;
    }

    @Override
    public List<TwitterObjectDto> find(String valueToFind) {
        try {
            return transformToTwetterModel(searchtweets(valueToFind));
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

    private List<TwitterObjectDto> transformToTwetterModel(List<Status> items) {
        List<TwitterObjectDto> tweets = Lists.newArrayList();
        items.forEach(i-> tweets.add(transform(i)));
        return tweets;
    }

    private TwitterObjectDto transform(Status item) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");

        String dateInString = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(item.getCreatedAt());
        System.out.println(dateInString);
        DateTime dateTime = DateTime.parse(dateInString, formatter).withZone(DateTimeZone.forID("Asia/Kolkata"));
        return new TwitterObjectDto(dateTime.toDate(), item.getText(), item.getUser().getScreenName());
    }
}