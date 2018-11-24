package ungs.coverage.services;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ungs.builders.services.ServiceBuilder;
import ungs.dto.TwitterObjectDto;
import ungs.model.Configuration;
import ungs.services.TwitterService;
import ungs.utils.ConfigUtils;
import java.util.List;

public class TwitterServiceTest {

    private TwitterService service;

    @BeforeClass
    public void init() {
        this.service = ServiceBuilder.create().buildTwitter(new Configuration(ConfigUtils.TWITTER_FILE)).build();
    }

    //@Test
    public void getTweets() {
        List<TwitterObjectDto> tweets = this.service.getAllTweets(); //.getTweetsByUser("PolloVignolo");//ChavoFuchs barba");//
        System.out.println("size " + tweets.size());
        tweets.forEach(i->System.out.println(i));
    }

    @Test
    public void isAvailable_whenIsValid_thenReturntrue() {
       // Assert.assertTrue(this.service.isOkServiceTwitter());
    }
}
