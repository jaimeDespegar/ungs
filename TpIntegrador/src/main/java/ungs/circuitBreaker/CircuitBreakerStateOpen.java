package ungs.circuitBreaker;

import ungs.connectors.interfaz.Connector;
import ungs.utils.exceptions.CircuitBreakerOpenException;
import java.util.List;

public class CircuitBreakerStateOpen<T> implements CircuitBreakerState {

    @Override
    public List<T> doAction(Connector connector) {
        throw new CircuitBreakerOpenException("The service is not available");
    }

    @Override
    public CircuitBreakerState next() {
        return this;
    }

}