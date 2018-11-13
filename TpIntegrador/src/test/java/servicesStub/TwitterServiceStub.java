package servicesStub;

import static org.mockito.Mockito.*;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import ungs.connectors.TwitterConnector;
import ungs.dto.TwitterObjectDto;
import ungs.filters.filterFactory.TwitterFilterFactory;
import ungs.services.Service;
import ungs.transformers.TwitterTransformer;
import java.util.Date;
import java.util.List;

public class TwitterServiceStub extends Service<TwitterConnector, TwitterObjectDto, TwitterTransformer> {

    private List<TwitterObjectDto> itemsTwitter = Lists.newArrayList();

    public TwitterServiceStub() {
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
        itemsTwitter.add(new TwitterObjectDto(now, "Torneo de Basquetball", "TyCSports", list));

        when(connector.find("from: clarin")).thenReturn(itemsTwitter.subList(1,2));
        when(connector.find("from: PolloVignolo")).thenReturn(itemsTwitter.subList(0,1));
        when(connector.find("from: TyCSports")).thenReturn(itemsTwitter.subList(2,3));
    }

    @Override
    public List getData() {
        return null;
    }

}