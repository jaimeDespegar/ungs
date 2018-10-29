package ungs.circuitBreaker;

import ungs.connectors.Connector;

import java.util.List;

public class ConnectorProxy<T> {

    private CircuitBreakerState state;
    private Connector connector;

    public ConnectorProxy(Connector connector) {
        this.connector = connector;
        this.state = new CircuitBreakerStateClose();
    }

    public List<T> getData() {
        return null;
    }

}