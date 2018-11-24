package ungs.criteriosDeAceptacion.first.iteration;

import ungs.builders.ViewFilterBuilder;
import org.testng.Assert;
import org.testng.annotations.*;
import ungs.builders.services.ServiceBuilder;
import ungs.filters.filterFactory.impl.RssFilterFactory;
import ungs.filters.filterFactory.impl.TwitterFilterFactory;
import ungs.connectors_stub.*;
import ungs.builder.InformationBuilder;
import ungs.connectors.impl.AbstractConnector;
import ungs.filters.FilterExecutor;
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
    private ViewFilter filterSport,filterPolitics,filterDescription,filterAll,filterDescriptionAndSport,filterDescriptionAndPolitics,filterDescriptionAndAllThemes;
    private String pathFileRss = "src/test/resources/test-files/services/rss/rss-exists.properties";
    private String pathFileTwitter = "src/test/resources/test-files/services/twitter/twitter-exists.properties";

    @BeforeClass
    public void init() {
        this.rssService = ServiceBuilder.create().buildRss(pathFileRss).build();
        this.twitterService = ServiceBuilder.create().buildTwitter(pathFileTwitter).build();

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