package ungs.services;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class testTwitter {


        public static void main(String args[]) {
            Twitter twitter = new TwitterFactory().getInstance();
            // Twitter Consumer key & Consumer Secret
            twitter.setOAuthConsumer("l5HHK9Zif6cbQLVTm2lzTQ", "3Jp8X9B78MijlxgFXrp76S5mT2QkqK1iOytZ8z1sc");
            // Twitter Access token & Access token Secret
            twitter.setOAuthAccessToken(new AccessToken("371118809-g8MSGnFNZVe1ZDHynOQF4x83vIsCkJAinI4EIhRW",
                    "MEeLoM0rC90gucCdVpdeuacfYvWcLIhDpaS8XDlr4Ts"));
            try {
                // Getting Twitter Timeline using Twitter4j API
                ResponseList statusReponseList = twitter.getUserTimeline(new Paging(1, 5));
                for (Object status : statusReponseList) {
                    Status s = (Status) status;
                    System.out.println(s.getText());
                }
                // Post a Tweet using Twitter4j API
                Status status = twitter.updateStatus("Hello");
                System.out.println("Successfully updated the status to [" + status.getText() + "].");
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }


}
