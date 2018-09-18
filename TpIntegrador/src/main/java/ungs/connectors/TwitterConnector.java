package ungs.connectors;

import ungs.dto.TwitterObjectDto;
import ungs.model.Configuration;
import java.util.List;

public class TwitterConnector extends AbstractConnector implements Connector<TwitterObjectDto> {

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public List<TwitterObjectDto> find(String url) {
        return null;
    }

    @Override
    public void setConfiguration(Configuration configuration) {

    }

    public boolean isValidUser() {
        String urlUserConnection = "ACA TIENE QUE ESTAR ARMADO EL GET PARA VALIDAR EL USUARIO";
        // TODO MAS PERMISOS/CONFIGURACIONES QUE SE NECESITEN
        return isServiceOk(urlUserConnection);
    }


}