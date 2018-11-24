package ungs.connectors.impl;

import com.google.common.collect.Lists;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ungs.circuitBreaker.CircuitBreakerState;
import ungs.circuitBreaker.CircuitBreakerStateClose;
import ungs.connectors.interfaz.Connector;
import ungs.helpers.ConnectionHelper;
import ungs.model.Configuration;
import ungs.utils.ConfigUtils;
import ungs.utils.exceptions.CircuitBreakerCloseException;
import ungs.utils.exceptions.CircuitBreakerOpenException;
import java.io.IOException;
import java.util.List;

public abstract class AbstractConnector<MODELO> implements Connector<MODELO> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected Configuration configuration;
    private CircuitBreakerState state;

    public AbstractConnector() {}

    public AbstractConnector(Configuration configuration) {
        this.configuration = configuration;
        this.state = new CircuitBreakerStateClose(configuration.getNumber(ConfigUtils.SERVICE_RETRIES_COUNT));
    }

    public boolean isServiceOk(String url) {
        try {
            return ConnectionHelper.isOkResponse(connectionResponse(url));
        } catch (IOException e) {
            return false;
        }
    }

    protected HttpResponse connectionResponse(String url) throws IOException {
        Response execute = Request.Get(url)
                                  .connectTimeout(configuration.getNumber(ConfigUtils.SERVICE_TIMEOUT))
                                  .execute();
        return execute.returnResponse();
    }

    public List<MODELO> doFind(String value) {
        List<MODELO> list = Lists.newArrayList();
        try {
            list = this.state.doAction(this, value);
        } catch (CircuitBreakerOpenException cboe) {
            logger.warn(cboe.getMessage(), cboe);
        } catch (CircuitBreakerCloseException cbce) {
            this.setNextState();
        }
        return list;
    }

    public void setNextState() {
        this.state = state.next();
    }

    public void setState(CircuitBreakerState state) {
        this.state = state;
    }

    public void reconnectedCircuite() {
        this.state.doAction(this, "");
        this.setNextState();
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public abstract void initConnection();

}