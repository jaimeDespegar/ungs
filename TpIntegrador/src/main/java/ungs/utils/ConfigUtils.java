package ungs.utils;

import java.io.File;

public abstract class ConfigUtils {

    public static final String RSS_FILE     = "files" + File.separator + "rss.properties";
    public static final String TWITTER_FILE = "files" + File.separator + "twitter.properties";

    public static Integer DEFAULT_COUNT = 10;

    // KEYS
    // TWITTER
    public static final String TWITTER_API_KEY = "twitter.user.apiKey";
    public static final String TWITTER_API_SECRET_KEY = "twitter.user.apiSecretKey";
    public static final String TWITTER_TOKEN_KEY = "twitter.user.tokenKey";
    public static final String TWITTER_TOKEN_SECRET_KEY = "twitter.user.tokenSecretKey";
    public static final String TWITTER_USERS_SPORT = "twitter.themes.sport.users";
    public static final String TWITTER_USERS_POLITICS = "twitter.themes.politics.users";
    public static final String TWITTER_USERS_MUSIC = "twitter.themes.music.users";
    public static final String TWITTER_COUNT = "twitter.tweets.count";
    // RSS

}
