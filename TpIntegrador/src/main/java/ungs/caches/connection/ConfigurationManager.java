package ungs.caches.connection;

import ungs.caches.dto.MongoConfigurationDto;
import ungs.model.Configuration;
import ungs.utils.ConfigUtils;

public class ConfigurationManager {

    public static MongoConfigurationDto getMongoConfiguration(String pathFile) {
        Configuration configuration = new Configuration(pathFile);
        String host = configuration.get(ConfigUtils.MONGO_HOST);
        Integer port = configuration.getNumber(ConfigUtils.MONGO_PORT);
        String db = configuration.get(ConfigUtils.MONGO_BD);
        String collection = configuration.get(ConfigUtils.MONGO_COLECCTION);
        return new MongoConfigurationDto(host, port, db, collection);
    }

}
