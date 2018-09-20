package ungs.transformers;

import org.joda.time.DateTime;
import ungs.dto.rss.RssItemDto;
import ungs.model.InformationDto;
import ungs.model.Origin;

public class RssTransformer implements TransformerInformation<RssItemDto>{

    @Override
    public InformationDto transform(RssItemDto rssItemDto) {
        return new InformationDto(Origin.RSS, null, rssItemDto.getDescription(), DateTime.now().toString());
    }

}