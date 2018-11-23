package servicesStub;

import com.google.common.collect.Lists;
import ungs.connectors.impl.AbstractConnector;
import ungs.connectors.interfaz.RssSpecificConnector;
import ungs.dto.rss.RssItemDto;
import ungs.utils.ConfigUtils;
import ungs.utils.RssCheckUrlUtil;
import java.util.List;
import java.util.stream.Collectors;

public class RssConnectorStub extends AbstractConnector implements RssSpecificConnector {

    private List<RssItemDto> itemsRss = Lists.newArrayList();

    public RssConnectorStub() {}

    @Override
    public void initConnection() {
        itemsRss.add(new RssItemDto("Sport", "Futbol 2018: River vs Boca", "www.sarasa.com"));
        itemsRss.add(new RssItemDto("Politics", "En el mes de noviembre harán su", "www.clarin.com"));
        //itemsRss.add(new RssItemDto("Politics", "Futbol para todos", "www.clarin.com"));
        RssCheckUrlUtil.isUrlOk(this);
    }

    @Override
    public boolean isServiceOk(String url) {
        return "http://www.urlok.com".equalsIgnoreCase(url);
    }

    @Override
    public boolean isAvailable() {
        return "http://www.urlok.com".equalsIgnoreCase(configuration.get(ConfigUtils.RSS_URL_CONNECTION));
    }

    @Override
    public List<RssItemDto> find(String keyUrl) {
        if(keyUrl.equalsIgnoreCase("rss.theme.sport")) {
            return itemsRss.subList(0,1);
        }
        if(keyUrl.equalsIgnoreCase("rss.theme.politics")) {
            return itemsRss.subList(1,2);
        }
        return null;
    }

    @Override
    public List<RssItemDto> find(List<String> listUrl) {
        return listUrl.stream().flatMap(i->find(i).stream()).collect(Collectors.toList());
    }

}