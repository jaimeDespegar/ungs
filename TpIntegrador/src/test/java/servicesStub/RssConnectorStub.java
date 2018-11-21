package servicesStub;

import com.google.common.collect.Lists;
import ungs.connectors.AbstractConnector;
import ungs.dto.rss.RssItemDto;
import ungs.utils.ConfigUtils;
import ungs.utils.RssCheckUrlUtil;
import ungs.utils.exceptions.ConfigurationException;

import java.util.List;

public class RssConnectorStub extends AbstractConnector {

    private List<RssItemDto> itemsRss = Lists.newArrayList();

    public RssConnectorStub() {}

    @Override
    public void initConnection() {
        itemsRss.add(new RssItemDto("Sport", "Futbol 2018: River vs Boca", "www.sarasa.com"));
        itemsRss.add(new RssItemDto("Politics", "Elecciones 2019", "www.clarin.com"));
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
    public List find(String keyUrl) {
        if(keyUrl.equalsIgnoreCase("rss.theme.sport")) {
            return itemsRss.subList(0,1);
        }
        if(keyUrl.equalsIgnoreCase("rss.theme.politics")) {
            return itemsRss.subList(1,2);
        }
        return null;
    }

}