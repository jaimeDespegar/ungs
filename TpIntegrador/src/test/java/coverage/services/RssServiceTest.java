package coverage.services;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ungs.connectors.RssConnector;
import ungs.dto.rss.RssItemDto;
import ungs.model.Configuration;
import ungs.services.RssService;
import ungs.themes.Theme;
import ungs.transformers.RssTransformer;

import java.util.List;

public class RssServiceTest {

    private RssService service;

    @BeforeClass
    public void init() {
        //this.service = new RssService(new RssTransformer(), new RssConnector(), new Configuration());
    }

/*    @Test
    public void isServiceRss_whenIsOk_thenReturnTrue() {
        assertTrue(this.service.isOkServiceRss());
    }

    @Test
    public void isServiceRss_whenIsUnaivalible_thenReturnFalse() {
       assertFalse(false);
    }

    @Test
    public void getListItemByThemeTest_whenThemeIsSport_thenReturnListItemSports() {
        List<RssItemDto> items = this.service.getListItemsByTheme(Theme.DEPORTES.getValueKey());
        assertEquals(items.size(), 10);
    }

    @Test
    public void getListItemByThemeTest_whenThemeIsPolitics_thenReturnListItemPolitics() {
        List<RssItemDto> items = this.service.getListItemsByTheme(Theme.POLITICA.getValueKey());
        assertEquals(items.size(), 10);
    }

    @Test
    public void getListItemByThemeTest_whenHaveAllThemes_thenReturnListWithThirtyItems() {
        List<RssItemDto> items = this.service.getAllItems();
        //items.forEach(i->System.out.println("item " + i));
        assertEquals(items.size(), 20);
    }*/

}