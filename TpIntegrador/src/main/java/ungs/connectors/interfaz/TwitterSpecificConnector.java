package ungs.connectors.interfaz;

import ungs.dto.TwitterObjectDto;
import java.util.List;

public interface TwitterSpecificConnector {

    List<TwitterObjectDto> findByUser(String user);

}