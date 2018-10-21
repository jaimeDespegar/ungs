package coverage.connectors;

import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ungs.caches.client.MongoDbCacheClient;
import ungs.model.InformationDto;
import ungs.model.Origin;
import ungs.themes.Theme;

public class MongoClientImplTest {

    private MongoDbCacheClient instance;
    private InformationDto info;

    @BeforeClass
    public void init() {
        this.instance = new MongoDbCacheClient();
        this.info = new InformationDto(Origin.TWITTER, Theme.DEPORTES, "test", DateTime.now().toString());
    }

    @BeforeMethod
    public void cleanValues() {
        this.instance.cleanCache();
    }

    @Test
    public void insertTest_withValues_thenInsertOk() {
        this.instance.insert(info);
        InformationDto infoInserted = this.instance.readAll().get(0);
        Assert.assertEquals(infoInserted.getOrigin(), Origin.TWITTER);
        Assert.assertEquals(infoInserted.getTheme(), Theme.DEPORTES);
        Assert.assertEquals(infoInserted.getDescription(), "test");
    }


    @Test
    public void clean_whenIsNotEmpty_DeleteAll() {
        this.instance.insert(info);
        Assert.assertEquals(this.instance.readAll().size(), 1);
        this.instance.cleanCache();
        Assert.assertTrue(this.instance.readAll().isEmpty());
    }

    @Test
    public void update_whenContainsElement_thenUpdatedIsOk() {
        this.instance.insert(info);
        info.setDescription("descripcion editada ok!");
        this.instance.update(info);
        Assert.assertEquals(this.instance.readAll().get(0).getDescription(), "description editada ok!");
    }

}