package ungs.circuitBreaker;

import ungs.connectors.interfaz.Connector;

import java.util.List;

public interface CircuitBreakerState<T> {

    List<T> doAction(Connector connector);
    CircuitBreakerState next();

}