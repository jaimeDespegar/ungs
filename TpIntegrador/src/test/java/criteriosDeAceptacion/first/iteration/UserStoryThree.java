package criteriosDeAceptacion.first.iteration;

import builders.ViewFilterBuilder;
import org.testng.Assert;
import org.testng.annotations.*;
import servicesStub.*;
import ungs.builder.InformationBuilder;
import ungs.connectors.impl.AbstractConnector;
import ungs.filters.FilterExecutor;
import ungs.filters.filterFactory.*;
import ungs.model.Configuration;
import ungs.model.InformationDto;
import ungs.model.ViewFilter;
import ungs.services.*;
import ungs.transformers.*;
import java.util.List;

public class UserStoryThree {

    private RssService rssService;
    private TwitterService twitterService;
    private InformationService informationService;
    private AbstractConnector connector = new RssConnectorStub();
    private AbstractConnector tConnector = new TwitterConnectorStub();
    private ViewFilter filterSport,filterPolitics,filterDescription,filterAll,filterDescriptionAndSport,filterDescriptionAndPolitics,filterDescriptionAndAllThemes;
    private String pathFileRss = "src/test/resources/test-files/services/rss/rss-exists.properties";
    private String pathFileTwitter = "src/test/resources/test-files/services/twitter/twitter-exists.properties";

    @BeforeClass
    public void init() {
        this.rssService = new RssService(new RssTransformer(), connector, new RssFilterFactory(connector),new FilterExecutor(), new Configuration(pathFileRss));
        this.twitterService = new TwitterService(new TwitterTransformer(), tConnector, new TwitterFilterFactory(tConnector), new FilterExecutor(), new Configuration(pathFileTwitter));

        this.informationService = new InformationBuilder().build();
        this.informationService.addService(rssService);
        this.informationService.addService(twitterService);

        this.filterSport = ViewFilterBuilder.create().dataDefault().withThemes("sport").build();
        this.filterPolitics = ViewFilterBuilder.create().dataDefault().withThemes("politics").build();
        this.filterDescription = ViewFilterBuilder.create().dataDefault().withDescription("river").build();
        this.filterDescriptionAndSport = ViewFilterBuilder.create().dataDefault().withThemes("sport").withDescription("river").build();
        this.filterDescriptionAndPolitics = ViewFilterBuilder.create().dataDefault().withThemes("politics").withDescription("medios").build();
        this.filterDescriptionAndAllThemes = ViewFilterBuilder.create().dataDefault().withDescription("de").build();
        this.filterAll = ViewFilterBuilder.create().dataDefaultEmpty().setAll(true).build();
    }

    @Test
    public void filterByThemeSport() {
        List<InformationDto> result = informationService.getData(filterSport);
        Assert.assertEquals(result.size(), 2);
    }

    @Test
    public void filterByThemePolitics() {
        List<InformationDto> result = informationService.getData(filterPolitics);
        Assert.assertEquals(result.size(), 2);
    }

    @Test
    public void filterByDescription() {
        List<InformationDto> result = informationService.getData(filterDescription);
        Assert.assertEquals(result.size(), 2);
    }

    @Test
    public void filterByDescriptionAndThemeSport() {
        List<InformationDto> result = informationService.getData(filterDescriptionAndSport);
        Assert.assertEquals(result.size(), 2);
    }

    @Test
    public void filterByDescriptionAndThemePolitics() {
        List<InformationDto> result = informationService.getData(filterDescriptionAndPolitics);
        Assert.assertEquals(result.size(), 1);
    }

    @Test
    public void filterByDescriptionAndAllThemes() {
        List<InformationDto> result = informationService.getData(filterDescriptionAndAllThemes);
        Assert.assertEquals(result.size(), 3);
    }

    @Test
    public void filterByAll() {
        List<InformationDto> result = informationService.getData(filterAll);
        Assert.assertEquals(result.size(), 4);
    }

}