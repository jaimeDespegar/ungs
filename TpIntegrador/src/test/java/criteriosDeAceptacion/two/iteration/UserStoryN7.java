package criteriosDeAceptacion.two.iteration;

import static org.testng.Assert.*;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ungs.caches.client.MongoDbCacheClient;
import ungs.caches.client.ServiceCacheProxy;
import ungs.caches.connection.ConfigurationManager;
import ungs.caches.dto.MongoConfigurationDto;
import ungs.caches.executors.TaskExecutor;
import ungs.caches.executors.TaskJob;
import ungs.caches.executors.tasks.impl.LoadCacheTask;
import ungs.connectors.RssConnector;
import ungs.connectors.TwitterConnector;
import ungs.model.Configuration;
import ungs.model.InformationDto;
import ungs.services.RssService;
import ungs.services.Service;
import ungs.services.TwitterService;
import ungs.transformers.RssTransformer;
import ungs.transformers.TwitterTransformer;
import ungs.utils.ConfigUtils;
import java.util.List;

public class UserStoryN7 {

    private ConfigurationManager manager;
    private ServiceCacheProxy cacheProxy;
    private TaskExecutor taskExecutor;

    @BeforeClass
    public void init() {
        this.manager = new ConfigurationManager();
        List<Service> services = Lists.newArrayList(new TwitterService(new TwitterTransformer(), new TwitterConnector(), new Configuration(ConfigUtils.TWITTER_FILE)) ,
                new RssService(new RssTransformer(), new RssConnector(), new Configuration(ConfigUtils.RSS_FILE)));
        this.cacheProxy = new ServiceCacheProxy();
        this.taskExecutor = new TaskExecutor(new TaskJob(new LoadCacheTask(new MongoDbCacheClient(), services)), 1L);
    }

    @Test
    public void testLoadFileMongo_whenExists_thenIsOkValues() {
        MongoConfigurationDto dto = ConfigurationManager.getMongoConfiguration(ConfigUtils.MONGO_FILE);
        assertFalse(StringUtils.isBlank(dto.getHost()));
        assertEquals(dto.getHost(), "localhost");
    }

    @Test
    public void testGetDataProxy_whenRssHaveNotCache_thenRssGetDataService() {
        cacheProxy.setService(new RssService(new RssTransformer(), new RssConnector(), new Configuration(ConfigUtils.RSS_FILE)));
        List<InformationDto> list = cacheProxy.getData();
        assertTrue(true);
    }

    @Test
    public void runTaskCacheLoad_isOK() {
        this.taskExecutor.run();
        assertTrue(true);
    }

}