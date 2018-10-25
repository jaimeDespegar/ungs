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
    private JsonMapper mapper = JsonMapper.getMapper();

    public MongoDbCacheClient() {
        this.database = connection.getMongoDatabase();
        this.collection = connection.getMongoCollection();
    }

    @Override
    public void insert(InformationDto informationDto) {
        try {
            Document obj = Document.parse(mapper.toJson(informationDto));
            collection.insertOne(obj);
        } catch (MongoServerException mse) {
            mse.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(InformationDto informationDto) {
        Document document = Document.parse(mapper.toJson(informationDto));
        collection.replaceOne(Filters.eq("id", document.get("id")), document);
    }

    @Override
    public void delete(InformationDto informationDto) {
        Document document = Document.parse(mapper.toJson(informationDto));
        collection.deleteOne(Filters.eq("id", document.get("id")));
    }

    @Override
    public List<InformationDto> readAll() {
        List<Document> list = collection.find().into(Lists.newArrayList());
        return list.stream()
                   .map(i -> (InformationDto) mapper.getValue(i.toJson(), InformationDto.class))
                   .collect(Collectors.toList());
    }

    public void cleanCache() {
        this.database.drop();
    }

}