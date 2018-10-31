package ungs.caches.executors.tasks.impl;

import ungs.caches.executors.tasks.interfaces.MyTask;
import ungs.circuitBreaker.CircuitBreakerStateHalfOpen;
import ungs.circuitBreaker.ConnectorProxy;
import ungs.services.Service;
import java.util.List;

public class ServiceMonitorTask implements MyTask {

    private List<Service> services;

    public ServiceMonitorTask(List<Service> services) {
        this.services = services;
    }

    @Override
    public void doExecute() {
        services.forEach(service -> enabledIfServiceIsOk(service));
    }

    public void enabledIfServiceIsOk(Service service) {
        if(!service.isEnabled()) {
            ConnectorProxy proxy = service.getProxy();
            proxy.setState(new CircuitBreakerStateHalfOpen());
            proxy.reconnectedCircuite();
        }
    }

}