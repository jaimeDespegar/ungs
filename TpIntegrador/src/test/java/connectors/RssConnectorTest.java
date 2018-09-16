package connectors;

import static org.junit.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ungs.connectors.RssConnector;

public class RssConnectorTest {

    private RssConnector instance;

    @BeforeClass
    public void init() {
        this.instance = new RssConnector();
    }


    @Test
    public void isAvailable_isOk() {
        assertTrue(instance.isAvailable());
    }

    @Test
    public void isAvailable_isFalse() {
        assertFalse(instance.isAvailable());
    }

}

