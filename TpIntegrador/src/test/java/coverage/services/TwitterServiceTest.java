package coverage.services;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ungs.connectors.TwitterConnector;
import ungs.dto.TwitterObjectDto;
import ungs.model.Configuration;
import ungs.services.TwitterService;
import ungs.transformers.TwitterTransformer;
import ungs.utils.ConfigUtils;

import java.util.List;

public class TwitterServiceTest {

    private TwitterService service;

    @BeforeClass
    public void init() {
        this.service = new TwitterService(new TwitterTransformer(), new TwitterConnector(), new Configuration(ConfigUtils.TWITTER_FILE));
    }

    @Test
    public void getTweets() {
        List<TwitterObjectDto> tweets = this.service.getAllTweets(); //.getTweetsByUser("PolloVignolo");//ChavoFuchs barba");//
        System.out.println("size " + tweets.size());
        tweets.forEach(i->System.out.println(i));
    }

    @Test
    public void isAvailable_whenIsValid_thenReturntrue() {
        Assert.assertTrue(this.service.isOkServiceTwitter());
    }
}
