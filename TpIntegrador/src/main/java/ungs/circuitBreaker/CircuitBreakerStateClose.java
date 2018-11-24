package ungs.circuitBreaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ungs.connectors.impl.AbstractConnector;
import ungs.utils.exceptions.CircuitBreakerCloseException;
import java.util.List;

public class CircuitBreakerStateClose<T> implements CircuitBreakerState {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
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
                logger.warn("Rompee", e);
            }
        }
        if (failsCount > retriesCount) {
            this.logger.warn("Threshold exceeded, the circuit was opened");
            throw new CircuitBreakerCloseException("Threshold exceeded, the circuit was opened");
        }
        return result;
    }

    @Override
    public CircuitBreakerState next() {
        return new CircuitBreakerStateOpen();
    }

}