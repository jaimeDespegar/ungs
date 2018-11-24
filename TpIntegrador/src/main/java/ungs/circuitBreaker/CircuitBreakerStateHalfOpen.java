package ungs.circuitBreaker;

import com.google.common.collect.Lists;
import ungs.connectors.impl.AbstractConnector;
import java.util.List;

public class CircuitBreakerStateHalfOpen<T> implements CircuitBreakerState {

    private CircuitBreakerState nextState = null;

    @Override
    public List<T> doAction(AbstractConnector connector, String value) {
        List<T> result = Lists.newArrayList();
        try {
            result = connector.find(value);
            nextState = new CircuitBreakerStateClose(2);
        } catch (Exception e) {
            nextState = new CircuitBreakerStateOpen();
        }
        return result;
    }

    @Override
    public CircuitBreakerState next() {
        return nextState;
    }

}