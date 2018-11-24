package ungs.circuitBreaker;

import ungs.connectors.impl.AbstractConnector;
import ungs.utils.exceptions.CircuitBreakerCloseException;
import java.util.List;

public class CircuitBreakerStateClose<T> implements CircuitBreakerState {

    private Integer retriesCount;
    private Integer failsCount;

    public CircuitBreakerStateClose(Integer retriesCount) {
        this.retriesCount = retriesCount;
        this.failsCount = 0;
    }

    @Override
    public List<T> doAction(AbstractConnector connector, String value) {
        List<T> result = null;
        this.retriesCount = connector.getConfiguration().getNumber("service.retries.count");
        while (failsCount <= retriesCount && result == null) {
            try {
                result = connector.find(value);
                failsCount = 0;
            } catch (Exception e) {
                this.failsCount += 1;
            }
        }
        if (failsCount>retriesCount) {
            throw new CircuitBreakerCloseException("Threshold exceeded, the circuit was opened");
        }
        return result;
    }

    @Override
    public CircuitBreakerState next() {
        return new CircuitBreakerStateOpen();
    }

}