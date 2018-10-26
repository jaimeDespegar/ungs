package ungs.caches.client;

import org.bson.Document;
import ungs.model.InformationDto;
import ungs.utils.JsonMapper;
import java.util.List;
import java.util.stream.Collectors;

public class MongoClientHelper {

    private JsonMapper mapper = JsonMapper.getMapper();

    public List<InformationDto> toInformation(List<Document> documents) {
        return documents.stream()
                        .map(i -> (InformationDto) mapper.getValue(i.toJson(), InformationDto.class))
                        .collect(Collectors.toList());
    }

    public Document toDocument(InformationDto informationDto) {
        return Document.parse(mapper.toJson(informationDto));
    }
}
