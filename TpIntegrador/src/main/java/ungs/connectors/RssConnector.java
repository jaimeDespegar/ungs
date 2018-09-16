package ungs.connectors;

import com.google.common.collect.Lists;
import ungs.dto.rss.RssItemDto;
import ungs.dto.rss.RssRootDto;
import ungs.model.Configuration;
import ungs.utils.JsonMapper;
import java.util.List;

public class RssConnector extends AbstractRssConnector implements AbstractConnector<RssItemDto>{

    private Configuration configuration;

    public RssConnector() {}

    public RssConnector(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean isAvailable() {
        return this.isServiceOk(configuration.getValueByKey("rss.urlConnection"));
    }

    @Override
    public List<RssItemDto> find(String keyUrl) {
        String url = configuration.getValueByKey(keyUrl);
        logger.info("Get Service RSS , Url: " + url);
        RssRootDto rssRootDto = JsonMapper.getMapper().getValueFromXml(this.connectionStatus(url), RssRootDto.class);
        return rssRootDto.getRss().getChannel().getListItems(); // FIXME puede haber NullPointer
    }

    public List<RssItemDto> find(List<String> listUrl) {
        List<RssItemDto> items = Lists.newArrayList();
        listUrl.forEach(url -> items.addAll(find(url)));
        return items;
    }

    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}