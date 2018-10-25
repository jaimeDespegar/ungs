package criteriosDeAceptacion.two.iteration;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ungs.caches.connection.ConfigurationManager;
import ungs.caches.dto.MongoConfigurationDto;
import ungs.utils.ConfigUtils;

public class UserStoryN7 {

    private ConfigurationManager manager;

    @BeforeClass
    public void init() {
        this.manager = new ConfigurationManager();
    }

    @Test
    public void testLoadFileMongo_whenExists_thenIsOkValues() {
        MongoConfigurationDto dto = ConfigurationManager.getMongoConfiguration(ConfigUtils.MONGO_FILE);
        Assert.assertFalse(dto.getHost().isEmpty());
        Assert.assertEquals(dto.getHost(), "localhost");
    }

}
