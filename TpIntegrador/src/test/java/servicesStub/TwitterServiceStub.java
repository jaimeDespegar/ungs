package servicesStub;

import static org.mockito.Mockito.*;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import ungs.connectors.TwitterConnector;
import ungs.dto.TwitterObjectDto;
import ungs.filters.filterFactory.TwitterFilterFactory;
import ungs.model.Configuration;
import ungs.services.Service;
import ungs.transformers.TwitterTransformer;
import ungs.utils.ConfigUtils;
import ungs.utils.ResponseUtil;
import java.util.Date;
import java.util.List;

public class TwitterServiceStub extends Service<TwitterConnector, TwitterObjectDto, TwitterTransformer> {

    private List<TwitterObjectDto> itemsTwitter = Lists.newArrayList();

    public TwitterServiceStub(Configuration configuration) {
        this.configuration = configuration;
        this.connector = mock(TwitterConnector.class);
        this.transformer = new TwitterTransformer();
        this.filterFactory = new TwitterFilterFactory(connector);
        this.init();
    }

    public void init() {
        Date now = DateTime.now().toDate();
        List<String> list = Lists.newArrayList();
        itemsTwitter.add(new TwitterObjectDto(now, "Torneo Libertadores: River vs Boca", "PolloVignolo", list));
        itemsTwitter.add(new TwitterObjectDto(now, "Mejores oportunidades", "clarin", list));
    //    itemsTwitter.add(new TwitterObjectDto(now, "Torneo de Basquetball", "TyCSports", list));

        when(connector.find("from: clarin")).thenReturn(itemsTwitter.subList(1,2));
        when(connector.find("from: PolloVignolo")).thenReturn(itemsTwitter.subList(0,1));
     //   when(connector.find("from: TyCSports")).thenReturn(itemsTwitter.subList(2,3));

        when(connector.isAvailable()).thenAnswer(a -> {
            return configuration.get("apiKey").equalsIgnoreCase("OK") && configuration.get("tokenKey").equalsIgnoreCase("OK")
            && configuration.get("apiSecrectKey").equalsIgnoreCase("OK") && configuration.get("tokenSecretKey").equalsIgnoreCase("OK") ;
        });
    }

    @Override
    public List<TwitterObjectDto> getData() {
        return ResponseUtil.getListItemsBySizeConfiguration(itemsTwitter, getCountValues());
    }

}