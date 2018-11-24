package ungs.connectors_stub;

import static org.mockito.Mockito.*;
import com.google.common.collect.Lists;
import ungs.connectors.impl.TwitterConnector;
import ungs.dto.TwitterObjectDto;
import ungs.filters.FilterExecutor;
import ungs.filters.FilterManager;
import ungs.filters.filterFactory.impl.TwitterFilterFactory;
import ungs.model.Configuration;
import ungs.services.Service;
import ungs.transformers.TwitterTransformer;
import ungs.utils.ResponseUtil;

import java.util.List;

public class TwitterServiceStub extends Service<TwitterConnector, TwitterObjectDto, TwitterTransformer> {

    private List<TwitterObjectDto> itemsTwitter = Lists.newArrayList();

    public TwitterServiceStub(Configuration configuration) {
        this.configuration = configuration;
        this.connector = mock(TwitterConnector.class);
        this.transformer = new TwitterTransformer();
        this.filterFactory = new TwitterFilterFactory(connector);
        this.filterExecutor = new FilterExecutor();
        this.filterManager = new FilterManager();
        this.init();
    }

    public void init() { }

    @Override
    public List<TwitterObjectDto> getData() {
        return ResponseUtil.getListItemsBySizeConfiguration(itemsTwitter, getCountValues());
    }

}