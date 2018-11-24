package ungs.criteriosDeAceptacion.two.iteration;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ungs.builders.services.ServiceBuilder;
import ungs.services.RssService;

public class UserStoryFive {

    private RssService providerB;

    @BeforeClass
    public void init() {
        this.providerB = ServiceBuilder.create().buildRss("src/test/resources/test-files/services/rss/rss-exists.properties").build();
    }

    @Test
    public void connection_whenCountRetriesExced_thenReturnCloseException() {
        this.providerB.setValueConfiguration("service.retries.count", "1");
        this.providerB.setValueConfiguration("service.monitorTask.minutes", "1");
        this.providerB.setValueConfiguration("rss.theme.noExists", "http://www.urlok.com");
        Assert.assertTrue(this.providerB.getData().isEmpty());
    }



}