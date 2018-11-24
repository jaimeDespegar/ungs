package ungs.circuitBreaker;

import ungs.connectors.impl.AbstractConnector;
import java.util.List;

public interface CircuitBreakerState<T> {

    List<T> doAction(AbstractConnector connector, String value);
    CircuitBreakerState next();

}