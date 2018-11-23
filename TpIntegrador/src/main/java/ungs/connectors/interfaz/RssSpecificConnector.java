package ungs.connectors.interfaz;

import ungs.dto.rss.RssItemDto;
import java.util.List;

public interface RssSpecificConnector {

    List<RssItemDto> find(List<String> listUrl);

}