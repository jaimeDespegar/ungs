package ungs.criteriosDeAceptacion.two.iteration;

import com.google.common.collect.Maps;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ungs.builders.services.ServiceBuilder;
import ungs.circuitBreaker.*;
import ungs.connectors.impl.AbstractConnector;
import ungs.connectors_stub.RssConnectorStub;
import ungs.dto.rss.RssItemDto;
import ungs.model.Configuration;
import ungs.services.RssService;
import ungs.utils.exceptions.*;
import java.util.*;

public class UserStoryFive {

    private RssService providerB;
    private AbstractConnector rssConnectorStub;
    private CircuitBreakerState circuitBreakerStateClose;
    private CircuitBreakerState circuitBreakerStateHalfOpen;
    private CircuitBreakerState circuitBreakerStateOpen;
    private Map values;

    @BeforeMethod
    public void init() {
        this.providerB = ServiceBuilder.create().buildRss("src/test/resources/test-files/services/rss/rss-exists.properties").build();

        this.values = Maps.newHashMap();
        this.values.put("rss.theme.noExists", "http://www.urlok.com");
        this.values.put("service.retries.count", "1");
        this.values.put("service.monitorTask.minutes", "1");
        this.rssConnectorStub = new RssConnectorStub(new Configuration(values));
        this.rssConnectorStub.initConnection();

        this.circuitBreakerStateOpen = new CircuitBreakerStateOpen();
        this.circuitBreakerStateHalfOpen = new CircuitBreakerStateHalfOpen();
        this.circuitBreakerStateClose = new CircuitBreakerStateClose(2);
    }

    @Test
    public void connection_whenCountRetriesExced_thenReturnCloseException() {
        this.providerB.setValueConfiguration("service.retries.count", "1");
        this.providerB.setValueConfiguration("service.monitorTask.minutes", "1");
        this.providerB.setValueConfiguration("rss.theme.noExists", "http://www.urlok.com");
        Assert.assertTrue(this.providerB.getData().isEmpty());
        Assert.assertEquals(this.providerB.getStateConnector().getClass(), CircuitBreakerStateOpen.class);
    }

    @Test(expectedExceptions = CircuitBreakerCloseException.class)
    public void doAction_whenCountRetriesExced_thenReturnCloseException() {
        this.circuitBreakerStateClose.doAction(rssConnectorStub,"url-no-valida");
    }

    @Test
    public void doAction_whenCountRetriesNotExced_thenReturnListOneSize() {
        List<RssItemDto> list = this.circuitBreakerStateClose.doAction(rssConnectorStub,"rss.theme.sport");
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void doAction_whenCountRetriesNotExced_thenNextStateIsOpen() {
        Assert.assertEquals(this.circuitBreakerStateClose.next().getClass(), CircuitBreakerStateOpen.class);
    }

    @Test(expectedExceptions = CircuitBreakerOpenException.class)
    public void doAction_whenStateIsOpen_throwCircuiteOpenException() {
        this.circuitBreakerStateOpen.doAction(rssConnectorStub, "not-search");
    }

    @Test
    public void doAction_whenStateIsOpen_thenNextStateIsOpen() {
        Assert.assertEquals(this.circuitBreakerStateOpen.next().getClass(), CircuitBreakerStateOpen.class);
    }

    @Test
    public void doAction_whenStateIsHalfOpenAndTestIsOK_thenNextStateIsCloseAndListIsSizeOne() {
        List<RssItemDto> list = this.circuitBreakerStateHalfOpen.doAction(rssConnectorStub,"rss.theme.sport");
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(this.circuitBreakerStateHalfOpen.next().getClass(), CircuitBreakerStateClose.class);
    }

    @Test
    public void doAction_whenStateIsHalfOpenAndTestFail_thenNextStateIsOpenAndListIsEmpty() {
        List<RssItemDto> list = this.circuitBreakerStateHalfOpen.doAction(rssConnectorStub,"URL-no-exists");
        Assert.assertTrue(list.isEmpty());
        Assert.assertEquals(this.circuitBreakerStateHalfOpen.next().getClass(), CircuitBreakerStateOpen.class);
    }

}