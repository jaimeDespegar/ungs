package ungs.connectors;

import com.google.common.collect.Lists;
import org.apache.http.HttpResponse;
import ungs.dto.rss.RssItemDto;
import ungs.dto.rss.RssRootDto;
import ungs.helpers.ConnectionHelper;
import ungs.model.Configuration;
import ungs.utils.ConfigUtils;
import ungs.utils.JsonMapper;
import ungs.utils.ResponseUtil;
import ungs.utils.exceptions.ConfigurationException;
import java.io.InputStream;
import java.util.List;

public class RssConnector extends AbstractConnector<RssItemDto> {

    private Integer COUNT_ELEMENTS;
    private JsonMapper mapper = JsonMapper.getMapper();

    public RssConnector() {}

    public RssConnector(Configuration configuration) {
        this.configuration = configuration;
        this.COUNT_ELEMENTS = configuration.getNumber(ConfigUtils.RSS_COUNT);
    }

    @Override
    public void initConnection() {
        configuration.getKeysStartWith("rss.theme.").forEach(url -> {
            if (!isServiceOk(configuration.get(url))) {
                throw new ConfigurationException(String.format("La url %s es invalida", url));
            }
        });
    }

    @Override
    public boolean isAvailable() {
        return this.isServiceOk(configuration.get(ConfigUtils.RSS_URL_CONNECTION));
    }

    @Override
    public List<RssItemDto> find(String keyUrl) {
        String url = configuration.get(keyUrl);
        logger.info(String.format("Get Service RSS, Url: %s.", url));
        RssRootDto rssRootDto = mapper.getValueFromXml(this.connectionStatus(url), RssRootDto.class);
        return ResponseUtil.getListItemsBySizeConfiguration(rssRootDto.getRss().getChannel().getListItems(), COUNT_ELEMENTS);
    }

    public List<RssItemDto> find(List<String> listUrl) {
        List<RssItemDto> items = Lists.newArrayList();
        listUrl.forEach(url -> items.addAll(find(url)));
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