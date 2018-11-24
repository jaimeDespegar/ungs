package ungs.executors.tasks.impl;

import ungs.circuitBreaker.CircuitBreakerStateHalfOpen;
import ungs.connectors.impl.AbstractConnector;
import ungs.executors.tasks.interfaces.MyTask;

public class ServiceMonitorTask implements MyTask {

    private AbstractConnector connector;

    public ServiceMonitorTask(AbstractConnector connector) {
        this.connector = connector;
    }

    @Override
    public void doExecute() {
        if (!connector.isAvailable()) {
            this.connector.setState(new CircuitBreakerStateHalfOpen());
            this.connector.reconnectedCircuite();
        }
    }

}