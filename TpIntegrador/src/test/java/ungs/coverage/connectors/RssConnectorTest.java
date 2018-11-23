package ungs.coverage.connectors;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ungs.connectors.impl.RssConnector;

public class RssConnectorTest {

    private RssConnector instance;

    @BeforeClass
    public void init() {
        this.instance = new RssConnector();
    }

    @Test(enabled = false)
    public void isAvailable_isOk() {
        Assert.assertTrue(instance.isAvailable());
    }

    @Test(enabled = false)
    public void isAvailable_isFalse() {
        Assert.assertFalse(instance.isAvailable());
    }

}