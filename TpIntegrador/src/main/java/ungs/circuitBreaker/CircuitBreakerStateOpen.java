package ungs.circuitBreaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ungs.connectors.impl.AbstractConnector;
import ungs.executors.tasks.impl.ServiceMonitorTask;
import ungs.utils.ConfigUtils;
import ungs.utils.JobUtils;
import ungs.utils.exceptions.CircuitBreakerOpenException;
import java.util.List;

public class CircuitBreakerStateOpen<T> implements CircuitBreakerState {

    private ServiceMonitorTask monitorTask;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<T> doAction(AbstractConnector connector, String value) {

        if (this.monitorTask == null) {
            this.monitorTask = new ServiceMonitorTask(connector);
            long minutes = connector.getConfiguration().getNumber(ConfigUtils.MINUTES_MONITOR_TASK).longValue();
            logger.info(String.format("Run Job Monitor Task, every %s minutes.", minutes));
            JobUtils.runJob(monitorTask, minutes);
        }

        throw new CircuitBreakerOpenException("The service is not available");
    }

    @Override
    public CircuitBreakerState next() {
        return this;
    }

}