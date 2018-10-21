package ungs.caches.connection;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnector {

    private static MongoDBConnector connector;
    private MongoClient mongoClient;


    private MongoDBConnector() {
        this.connection();
    }

    public void connection() {
        String  host = "localhost";
        Integer port = 27017;
        this.mongoClient = new MongoClient(host, port);
    }

    public static MongoDBConnector getConnector() {
        if(connector == null) {
            connector = new MongoDBConnector();
        }
        return connector;
    }


    public MongoDatabase getMongoDatabase() {
        return this.mongoClient.getDatabase("mydb");
    }

    public void close() {
        this.mongoClient.close();
    }
}