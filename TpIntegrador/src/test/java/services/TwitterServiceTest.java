package services;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ungs.connectors.TwitterConnector;
import ungs.dto.TwitterObjectDto;
import ungs.services.TwitterService;

import java.util.List;

public class TwitterServiceTest {


    private TwitterService service;

    @BeforeClass
    public void init() {
        this.service = new TwitterService(new TwitterConnector());
    }

    @Test
    public void getTweets() {
        List<TwitterObjectDto> tweets = this.service.getTweetsByUser("RiverLPM");//
        System.out.println("size " + tweets.size());
        tweets.forEach(i->System.out.println(i));
    }

}
