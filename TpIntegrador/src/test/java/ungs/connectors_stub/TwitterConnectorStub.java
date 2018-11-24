package ungs.connectors_stub;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import ungs.connectors.impl.AbstractConnector;
import ungs.connectors.interfaz.TwitterSpecificConnector;
import ungs.dto.TwitterObjectDto;
import ungs.model.Configuration;

import java.util.Date;
import java.util.List;

public class TwitterConnectorStub extends AbstractConnector<TwitterObjectDto> implements TwitterSpecificConnector {

    private List<TwitterObjectDto> itemsTwitter = Lists.newArrayList();

    public TwitterConnectorStub(Configuration configuration) {
        super(configuration);
    }

    @Override
    public void initConnection() {
        Date now = DateTime.now().toDate();
        List<String> list = Lists.newArrayList();
        itemsTwitter.add(new TwitterObjectDto(now, "Torneo de la Libertadores: River vs Boca", "PolloVignolo", list));
        itemsTwitter.add(new TwitterObjectDto(now, "Ley de medios", "clarin", list));
        //    itemsTwitter.add(new TwitterObjectDto(now, "Torneo de Basquetball", "TyCSports", list));
    }

    @Override
    public boolean isAvailable() {
        return configuration.get("apiKey").equalsIgnoreCase("OK") &&
               configuration.get("tokenKey").equalsIgnoreCase("OK") &&
               configuration.get("apiSecrectKey").equalsIgnoreCase("OK") &&
               configuration.get("tokenSecretKey").equalsIgnoreCase("OK");
    }

    @Override
    public List<TwitterObjectDto> findByUser(String user) {
        return find(user);
    }

    @Override
    public List<TwitterObjectDto> find(String url) {
        if ("from: clarin".equalsIgnoreCase(url)||"clarin".equalsIgnoreCase(url)) {
            return itemsTwitter.subList(1, 2);
        }
        if ("from: PolloVignolo".equalsIgnoreCase(url)||"PolloVignolo".equalsIgnoreCase(url)) {
            return itemsTwitter.subList(0,1);
        }
        return null;
    }

}