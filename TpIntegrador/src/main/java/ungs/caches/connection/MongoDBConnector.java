package ungs.caches.connection;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ungs.caches.dto.MongoConfigurationDto;
import ungs.utils.ConfigUtils;

public class MongoDBConnector {

    private static MongoDBConnector connector;
    private MongoClient mongoClient;
    private MongoConfigurationDto configurationDto;

    private MongoDBConnector() {
        this.connection();
    }

    public void connection() {
        this.configurationDto = ConfigurationManager.getMongoConfiguration(ConfigUtils.MONGO_FILE);
        this.mongoClient = new MongoClient(configurationDto.getHost(), configurationDto.getPort());
    }

    public static MongoDBConnector getConnector() {
        if(connector == null) {
            connector = new MongoDBConnector();
        }
        return connector;
    }

    public MongoDatabase getMongoDatabase() {
        return this.mongoClient.getDatabase(configurationDto.getDatabase());
    }

    public MongoCollection<Document> getMongoCollection() {
        return getMongoDatabase().getCollection(configurationDto.getCollection());
    }

    public void close() {
        this.mongoClient.close();
    }

}