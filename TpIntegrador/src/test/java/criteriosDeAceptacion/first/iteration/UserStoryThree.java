package criteriosDeAceptacion.first.iteration;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.testng.Assert;
import org.testng.annotations.*;
import ungs.connectors.RssConnector;
import ungs.connectors.TwitterConnector;
import ungs.dto.Theme;
import ungs.dto.TwitterObjectDto;
import ungs.dto.rss.RssItemDto;
import ungs.filters.DescriptionTwitterFilter;
import ungs.filters.filterInt.Filter;
import ungs.filters.rss.DescriptionFilterRss;
import ungs.filters.rss.ThemeFilterRss;
import ungs.filters.twitter.ThemeFilterTwitter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UserStoryThree {

    private Filter filterDescription, filterDescriptionTwitter, filterThemeSport, filterThemePolitics, filterThemeSportTwitter;
    private List<RssItemDto> itemsRss = Lists.newArrayList();
    private List<TwitterObjectDto> itemsTwitter = Lists.newArrayList();
    @Mock
    private RssConnector rssConnector;
    @Mock
    private TwitterConnector twitterConnector;

    @BeforeClass
    public void init() {
        //RssConnector rssConnector = new RssConnector(new Configuration(ConfigUtils.RSS_FILE));
        rssConnector = mock(RssConnector.class);
        twitterConnector = mock(TwitterConnector.class);
        filterDescription = new DescriptionFilterRss(rssConnector, "Futbol");
        filterDescriptionTwitter = new DescriptionTwitterFilter("torneo");
        filterThemeSport = new ThemeFilterRss(rssConnector);
        filterThemePolitics = new ThemeFilterRss(rssConnector);
        filterThemeSportTwitter = new ThemeFilterTwitter(twitterConnector);
        itemsRss.add(new RssItemDto("Sport", "Futbol 2018: River vs Boca", "www.sarasa.com"));
        itemsRss.add(new RssItemDto("Politics", "Elecciones 2019", "www.clarin.com"));
        itemsRss.add(new RssItemDto("Politics", "Futbol para todos", "www.clarin.com"));

        Date now = DateTime.now().toDate();
        List<String> list = Lists.newArrayList();
        itemsTwitter.add(new TwitterObjectDto(now, "Torneo Libertadores: River vs Boca", "PolloVignolo", list));
        itemsTwitter.add(new TwitterObjectDto(now, "Mejores oportunidades", "clarin", list));
        itemsTwitter.add(new TwitterObjectDto(now, "Torneo de Basquetball", "TyCSports", list));

        when(rssConnector.find("rss.theme.sport")).thenReturn(itemsRss.subList(0, 1));
        when(rssConnector.find("rss.theme.politics")).thenReturn(itemsRss.subList(1, 3));
        when(rssConnector.find(anyList())).thenReturn(itemsRss);
        when(twitterConnector.find("from: clarin")).thenReturn(itemsTwitter.subList(1,2));
        when(twitterConnector.find("from: PolloVignolo")).thenReturn(itemsTwitter.subList(0,1));
        when(twitterConnector.find("from: TyCSports")).thenReturn(itemsTwitter.subList(2,3));
    }

    @Test
    public void filterByThemeSport() {
        Theme themeSport = new Theme("sport", Arrays.asList("PolloVignolo", "TyCSports"));
        List<RssItemDto> result = filterThemeSport.applyFilter(Arrays.asList(themeSport));
        List<TwitterObjectDto> twitterObjectDtos = filterThemeSportTwitter.applyFilter(Arrays.asList(themeSport));
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(twitterObjectDtos.size(), 2);
    }

    @Test
    public void filterByThemePolitics() {
        List<RssItemDto> result = filterThemePolitics.applyFilter(Arrays.asList(new Theme("politics")));
        Assert.assertEquals(result.size(), 2);
    }

    @Test
    public void filterByDescription() {
        List<RssItemDto> result = filterDescription.applyFilter(itemsRss);
        List<TwitterObjectDto> resultTwitter = filterDescriptionTwitter.applyFilter(itemsTwitter);
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(resultTwitter.size(), 2);
    }

    @Test
    public void filterByDescriptionAndThemeSport() {

    }

    @Test
    public void filterByDescriptionAndThemePolitics() {

    }

    @Test
    public void filterByDescriptionAndAllThemes() {

    }

    @Test
    public void filterByAll() {

    }

}