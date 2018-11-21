package ungs.utils;

import java.io.File;

public abstract class ConfigUtils {

    // FILES
    public static final String FOLDER_PATH = "files" + File.separator;
    public static final String CONFIG_FILE = FOLDER_PATH + "configuration.properties";
    public static final String RSS_FILE = FOLDER_PATH + "rss.properties";
    public static final String TWITTER_FILE = FOLDER_PATH + "twitter.properties";
    public static final String MONGO_FILE = FOLDER_PATH + "mongo.properties";
    public static final String ERRORS_FILE = FOLDER_PATH + "errors.json";
    public static final String LABELS_FILE = FOLDER_PATH + "labels.json";

    // KEYS
    public static Integer DEFAULT_COUNT = 10;
    public static final String SERVICE_NAME = "provider.service.name";
    public static final String SERVICE_TIMEOUT = "service.timeout";
    public static final String SERVICE_RETRIES_COUNT = "service.retries.count";
    public static final String THEMES = "themes";
    public static final String SERVICE = "services";
    public static final String PACKAGE = "service.package.";
    public static final String LENGUAJE = "lenguaje";

    // TWITTER
    public static final String TWITTER_API_KEY = "twitter.user.apiKey";
    public static final String TWITTER_API_SECRET_KEY = "twitter.user.apiSecretKey";
    public static final String TWITTER_TOKEN_KEY = "twitter.user.tokenKey";
    public static final String TWITTER_TOKEN_SECRET_KEY = "twitter.user.tokenSecretKey";
    public static final String TWITTER_USERS_SPORT = "twitter.theme.sport";
    public static final String TWITTER_USERS_POLITICS = "twitter.theme.politics";
    public static final String TWITTER_COUNT = "twitter.response.count";
    // RSS
    public static final String RSS_COUNT = "rss.response.count";
    public static final String RSS_URL_CONNECTION = "rss.urlConnection";

    // MONGO
    public static final String MONGO_HOST = "mongo.host";
    public static final String MONGO_PORT = "mongo.port";
    public static final String MONGO_BD = "mongo.database";
    public static final String MONGO_COLECCTION = "mongo.collection";

}