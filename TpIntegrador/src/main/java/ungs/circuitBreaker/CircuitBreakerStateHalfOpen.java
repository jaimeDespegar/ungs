package ungs.circuitBreaker;

import com.google.common.collect.Lists;
import ungs.connectors.Connector;
import java.util.List;

public class CircuitBreakerStateHalfOpen<T> implements CircuitBreakerState {

    private boolean isConnectorOk = false;

    @Override
    public List<T> doAction(Connector connector) {
        return Lists.newArrayList();
    }

    @Override
    public CircuitBreakerState next() {
        if(isConnectorOk) {
            return new CircuitBreakerStateClose(2);
        }
        return new CircuitBreakerStateOpen();
    }

}