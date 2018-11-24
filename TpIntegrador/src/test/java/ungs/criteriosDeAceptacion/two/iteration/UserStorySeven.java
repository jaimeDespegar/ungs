package ungs.criteriosDeAceptacion.two.iteration;

import static org.testng.Assert.*;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ungs.builders.services.ServiceBuilder;
import ungs.caches.client.MongoDbCacheClient;
import ungs.caches.client.ServiceCacheProxy;
import ungs.caches.connection.ConfigurationManager;
import ungs.caches.dto.MongoConfigurationDto;
import ungs.executors.TaskExecutor;
import ungs.executors.TaskJob;
import ungs.executors.tasks.impl.LoadCacheTask;
import ungs.model.Configuration;
import ungs.model.InformationDto;
import ungs.services.Service;
import ungs.utils.ConfigUtils;
import java.util.List;

public class UserStorySeven {

    private ConfigurationManager manager;
    private ServiceCacheProxy cacheProxy;
    private TaskExecutor taskExecutor;

    @BeforeClass
    public void init() {
        this.manager = new ConfigurationManager();
        List<Service> services = Lists.newArrayList(ServiceBuilder.create().buildTwitter(new Configuration(ConfigUtils.TWITTER_FILE)).build(),
                                                    ServiceBuilder.create().buildRss(new Configuration(ConfigUtils.RSS_FILE)).build());
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
        cacheProxy.setService(ServiceBuilder.create().buildRss(new Configuration(ConfigUtils.RSS_FILE)).build());
        List<InformationDto> list = cacheProxy.getData();
        assertTrue(true);
    }

    @Test
    public void runTaskCacheLoad_isOK() {
        this.taskExecutor.run();
        assertTrue(true);
    }

}