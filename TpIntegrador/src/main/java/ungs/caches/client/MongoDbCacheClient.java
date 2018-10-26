package ungs.caches.client;

import com.google.common.collect.Lists;
import com.mongodb.MongoServerException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import ungs.caches.connection.MongoDBConnector;
import ungs.model.InformationDto;
import ungs.utils.JsonMapper;
import java.util.List;
import java.util.stream.Collectors;

public class MongoDbCacheClient implements CacheClient<InformationDto> {

    private MongoDBConnector connection = MongoDBConnector.getConnector();
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private MongoClientHelper helper;

    public MongoDbCacheClient() {
        this.database = connection.getMongoDatabase();
        this.collection = connection.getMongoCollection();
        this.helper = new MongoClientHelper();
    }

    @Override
    public void insert(InformationDto informationDto) {
        try {
            Document document = helper.toDocument(informationDto);
            collection.insertOne(document);
        } catch (MongoServerException mse) {
            mse.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(InformationDto informationDto) {
        Document document = helper.toDocument(informationDto);
        collection.replaceOne(Filters.eq("id", document.get("id")), document);
    }

    @Override
    public void delete(InformationDto informationDto) {
        Document document = helper.toDocument(informationDto);
        collection.deleteOne(Filters.eq("id", document.get("id"))).wasAcknowledged();
    }

    @Override
    public List<InformationDto> readAll() {
        List<Document> list = collection.find().into(Lists.newArrayList());
        return helper.toInformation(list);
    }

    @Override
    public List<InformationDto> findByOrigin(String origin) {
        List<Document> listFilteredByOrigin = collection.find(Filters.eq("origin", origin))
                                                        .into(Lists.newArrayList());
        return helper.toInformation(listFilteredByOrigin);
    }

    public void cleanCache() {
        this.database.drop();
    }

}