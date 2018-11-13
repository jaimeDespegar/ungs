package servicesStub;

import com.google.common.collect.Lists;
import static org.mockito.Mockito.*;
import ungs.connectors.RssConnector;
import ungs.dto.rss.RssItemDto;
import ungs.filters.filterFactory.RssFilterFactory;
import ungs.model.InformationDto;
import ungs.services.Service;
import ungs.transformers.RssTransformer;
import java.util.List;

public class RssServiceStub extends Service<RssConnector, RssItemDto, RssTransformer> {

    private List<RssItemDto> itemsRss = Lists.newArrayList();

    public RssServiceStub() {
        this.connector = mock(RssConnector.class);
        this.transformer = new RssTransformer();
        this.filterFactory = new RssFilterFactory(connector);
        this.init();
    }

    public void init() {
        itemsRss.add(new RssItemDto("Sport", "Futbol 2018: River vs Boca", "www.sarasa.com"));
        itemsRss.add(new RssItemDto("Politics", "Elecciones 2019", "www.clarin.com"));
        itemsRss.add(new RssItemDto("Politics", "Futbol para todos", "www.clarin.com"));

        when(connector.find("rss.theme.sport")).thenReturn(itemsRss.subList(0, 1));
        when(connector.find("rss.theme.politics")).thenReturn(itemsRss.subList(1, 3));
        when(connector.find(anyList())).thenReturn(itemsRss);
    }

    @Override
    public List<InformationDto> getData() {
        return null;
    }

}