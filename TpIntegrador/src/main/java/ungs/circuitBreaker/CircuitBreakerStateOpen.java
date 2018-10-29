package ungs.circuitBreaker;

import ungs.connectors.Connector;
import java.util.List;

public class CircuitBreakerStateOpen<T> implements CircuitBreakerState {

    @Override
    public List<T> doAction(Connector connector) {
        throw new RuntimeException("The service is not available");
    }

    @Override
    public CircuitBreakerState next() {
        return new CircuitBreakerStateHalfOpen();
    }

}