package ungs.circuitBreaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ungs.connectors.interfaz.Connector;
import ungs.utils.exceptions.CircuitBreakerCloseException;
import ungs.utils.exceptions.CircuitBreakerOpenException;
import java.util.List;

public class ConnectorProxy<T> {

    private CircuitBreakerState state;
    private Connector connector;
    private Logger logger = LoggerFactory.getLogger(ConnectorProxy.class);


    public ConnectorProxy(Connector connector, Integer retriesCount) {
        this.connector = connector;
        this.state = new CircuitBreakerStateClose(retriesCount);
    }

    public List<T> getData() {
        List<T> list = null;
          try {
            list = this.state.doAction(connector);
        } catch (CircuitBreakerOpenException cboe) {
            logger.warn(cboe.getMessage(), cboe);
        } catch (CircuitBreakerCloseException cbce) {
            this.setNextState();
        }
        return list;
    }

    public void setNextState() {
        this.state = state.next();
    }

    public void setState(CircuitBreakerState state) {
        this.state = state;
    }

    public void reconnectedCircuite() {
        this.state.doAction(connector);
        this.setNextState();
    }

}