package criteriosDeAceptacion.first.iteration;

import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.testng.Assert;
import org.testng.annotations.*;
import ungs.connectors.RssConnector;
import ungs.connectors.TwitterConnector;
import ungs.dto.Theme;
import ungs.dto.TwitterObjectDto;
import ungs.dto.rss.RssItemDto;
import ungs.filters.filterInt.Filter;
import ungs.filters.rss.DescriptionFilterRss;
import ungs.filters.rss.ThemeFilterRss;
import ungs.filters.twitter.DescriptionFilterTwitter;
import ungs.filters.twitter.ThemeFilterTwitter;
import java.util.Arrays;
import java.util.List;

public class UserStoryThree {

    private Filter filterDescription, filterDescriptionTwitter, filterThemeSport, filterThemePolitics, filterThemeSportTwitter;
    @Mock
    private RssConnector rssConnector;
    @Mock
    private TwitterConnector twitterConnector;

    @BeforeClass
    public void init() {
        rssConnector = mock(RssConnector.class);
        twitterConnector = mock(TwitterConnector.class);
        filterDescription = new DescriptionFilterRss(rssConnector, "Futbol");
        filterDescriptionTwitter = new DescriptionFilterTwitter(twitterConnector, "torneo");
        filterThemeSport = new ThemeFilterRss(rssConnector, Arrays.asList(new Theme("sport")));
        filterThemePolitics = new ThemeFilterRss(rssConnector, Arrays.asList(new Theme("politics")));
        filterThemeSportTwitter = new ThemeFilterTwitter(twitterConnector, Arrays.asList(new Theme("sport", Arrays.asList("PolloVignolo", "TyCSports"))));
    }

    @Test
    public void filterByThemeSport() {
        List<RssItemDto> result = filterThemeSport.applyFilter();
        List<TwitterObjectDto> twitterObjectDtos = filterThemeSportTwitter.applyFilter();
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(twitterObjectDtos.size(), 2);
    }

    @Test
    public void filterByThemePolitics() {
        List<RssItemDto> result = filterThemePolitics.applyFilter();
        Assert.assertEquals(result.size(), 2);
    }

    @Test
    public void filterByDescription() {
        List<RssItemDto> result = filterDescription.applyFilter();
        List<TwitterObjectDto> resultTwitter = filterDescriptionTwitter.applyFilter();
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