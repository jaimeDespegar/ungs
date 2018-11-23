package coverage.connectors;

import static org.junit.Assert.*;
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
        assertTrue(instance.isAvailable());
    }

    @Test(enabled = false)
    public void isAvailable_isFalse() {
        assertFalse(instance.isAvailable());
    }

}