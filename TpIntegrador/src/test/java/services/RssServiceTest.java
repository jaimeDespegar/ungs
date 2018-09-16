package services;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.collections.Lists;
import ungs.connectors.RssConnector;
import ungs.dto.rss.RssItemDto;
import ungs.services.RssService;
import ungs.themes.Theme;
import java.util.List;

public class RssServiceTest {

    private RssService service;
    @Mock
    private RssConnector connector;

    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.service = new RssService(connector);

        when(connector.find(Theme.DEPORTES.getValueKey()))
                      .thenReturn(Lists.newArrayList(new RssItemDto(), new RssItemDto("Futbol", "MUNDIAL 2018" ,"GOOGLE/DEPORTES")));
        when(connector.find(Theme.POLITICA.getValueKey()))
                      .thenReturn(Lists.newArrayList(new RssItemDto("Economia","Dolar description","argentina/elecciones")));
        when(connector.find(Theme.MUSICA.getValueKey()))
                      .thenReturn(Lists.newArrayList(new RssItemDto("Rock", "ACDC Description", "music/2018")));
    }

    @Test
    public void isServiceRss_whenIsOk_thenReturnTrue() {
        when(connector.isAvailable()).thenReturn(true);
        assertTrue(this.service.isOkServiceRss());
    }

    @Test
    public void isServiceRss_whenIsUnaivalible_thenReturnFalse() {
        when(connector.isAvailable()).thenReturn(false);
        assertFalse(this.service.isOkServiceRss());
    }

    @Test
    public void getListItemByThemeTest_whenThemeIsSport_thenReturnListItemSports() {
        List<RssItemDto> items = this.service.getListItemsByTheme(Theme.DEPORTES);
        assertEquals(items.size(), 2);
    }

    @Test
    public void getListItemByThemeTest_whenThemeIsMusic_thenReturnListItemMusic() {
        List<RssItemDto> items = this.service.getListItemsByTheme(Theme.MUSICA);
        assertEquals(items.size(), 1);
    }

    @Test
    public void getListItemByThemeTest_whenThemeIsPolitics_thenReturnListItemPolitics() {
        List<RssItemDto> items = this.service.getListItemsByTheme(Theme.POLITICA);
        assertEquals(items.size(), 1);
    }

}