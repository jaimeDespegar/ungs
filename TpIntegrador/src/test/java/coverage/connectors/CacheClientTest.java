package coverage.connectors;

import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ungs.caches.client.CacheClient;
import ungs.model.InformationDto;
import ungs.model.Origin;
import ungs.themes.Theme;

public class CacheClientTest {

    private CacheClient<InformationDto> instance;
    private InformationDto info;

    @BeforeClass
    public void init() {
        this.instance = new StubCacheClient();
        this.info = new InformationDto("1", Origin.TWITTER, Theme.DEPORTES, "test", DateTime.now().toString());
    }

    @BeforeMethod
    public void cleanValues() {
        this.instance.cleanCache();
    }

    @Test
    public void insertTest_withValues_thenInsertOk() {
        Assert.assertTrue(this.instance.insert(info));
        InformationDto infoInserted = this.instance.readAll().get(0);
        Assert.assertEquals(infoInserted.getOrigin(), Origin.TWITTER);
        Assert.assertEquals(infoInserted.getTheme(), Theme.DEPORTES);
        Assert.assertEquals(infoInserted.getDescription(), "test");
    }

    @Test
    public void clean_whenIsNotEmpty_DeleteAll() {
        Assert.assertTrue(this.instance.insert(info));
        Assert.assertEquals(this.instance.readAll().size(), 1);
        this.instance.cleanCache();
        Assert.assertTrue(this.instance.readAll().isEmpty());
    }

    @Test
    public void update_whenContainsElement_thenUpdatedIsOk() {
        Assert.assertTrue(this.instance.insert(info));
        info.setDescription("descripcion editada ok!");
        Assert.assertTrue(this.instance.update(info));
    }

    @Test
    public void dalete_whenContainsOneElement_thenDeleteIsOk() {
        Assert.assertTrue(this.instance.insert(info));
        Assert.assertTrue(this.instance.delete(info));
        Assert.assertTrue(this.instance.readAll().isEmpty());
    }

}