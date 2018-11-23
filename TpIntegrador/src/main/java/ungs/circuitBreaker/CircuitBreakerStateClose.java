package ungs.circuitBreaker;

import ungs.connectors.interfaz.Connector;
import ungs.utils.exceptions.CircuitBreakerCloseException;
import java.util.List;

public class CircuitBreakerStateClose<T> implements CircuitBreakerState {

    private Integer retriesCount;
    private Integer failsCount;

    public CircuitBreakerStateClose(Integer retriesCount) {
        this.retriesCount = retriesCount;
    }

    @Override
    public List<T> doAction(Connector connector) {
        List<T> result = null;
        while (failsCount <= retriesCount && result == null) {
            try {
                result = connector.find("");
                failsCount = 0;
            } catch (Exception e) {
                this.failsCount += 1;
            }
        }
        if (failsCount>retriesCount) {
            throw new CircuitBreakerCloseException("Se abrio el cirtuito");
        }
        return result;
    }

    @Override
    public CircuitBreakerState next() {
        return new CircuitBreakerStateOpen();
    }

}