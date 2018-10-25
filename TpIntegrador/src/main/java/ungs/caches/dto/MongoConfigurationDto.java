package ungs.caches.dto;

public class MongoConfigurationDto {

    private String host;
    private Integer port;
    private String database;
    private String collection;

    public MongoConfigurationDto(String host, Integer port, String database, String collection) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.collection = collection;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

}
