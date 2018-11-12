package ungs.connectors;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.HttpResponse;
import ungs.dto.rss.RssItemDto;
import ungs.dto.rss.RssRootDto;
import ungs.helpers.ConnectionHelper;
import ungs.model.Configuration;
import ungs.utils.JsonMapper;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class RssConnector extends AbstractConnector<RssItemDto> {

    public RssConnector() {}
    public RssConnector(Configuration configuration) { this.configuration = configuration;}

    @Override
    public void initConnection() {}

    @Override
    public boolean isAvailable() {
        return this.isServiceOk(configuration.get("rss.urlConnection"));
    }

    @Override
    public List<RssItemDto> find(String keyUrl) {
        String url = configuration.get(keyUrl);
        logger.info(String.format("Get Service RSS, Url: %s.", url));
        RssRootDto rssRootDto = JsonMapper.getMapper().getValueFromXml(this.connectionStatus(url), RssRootDto.class);
        return getListItemsBySizeConfiguration(rssRootDto.getRss().getChannel().getListItems());
    }

    public List<RssItemDto> find(List<String> listUrl) {
        List<RssItemDto> items = Lists.newArrayList();
        listUrl.forEach(url -> items.addAll(find(url)));
        return items;
    }

    private List<RssItemDto> getListItemsBySizeConfiguration(List<RssItemDto> items) {
        Integer count = Integer.valueOf(configuration.get("rss.response.count"));
        if (CollectionUtils.isNotEmpty(items) && count!=null) {
            return items.stream().limit(count).collect(Collectors.toList());
        }
        return items;
    }

    private InputStream connectionStatus(String url) {
        InputStream inputResponse = null;
        try {
            HttpResponse response = this.connectionResponse(url);
            if (ConnectionHelper.isOkResponse(response)) {
                inputResponse = response.getEntity().getContent();
            }
        } catch (Exception e) {
            logger.warn("No Response RSS Application", e); // TODO hacer algo mas !
        }
        return inputResponse;
    }

}