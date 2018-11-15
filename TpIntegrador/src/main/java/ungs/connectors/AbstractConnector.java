package ungs.connectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ungs.helpers.ConnectionHelper;
import ungs.model.Configuration;
import java.io.IOException;

public abstract class AbstractConnector<MODELO> implements Connector<MODELO> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Configuration configuration;

    public AbstractConnector() {}

    public AbstractConnector(Configuration configuration) {
        this.configuration = configuration;
    }

    protected boolean isServiceOk(String url) {
        try {
            return ConnectionHelper.isOkResponse(connectionResponse(url));
        } catch (IOException e) {
            return false;
        }
    }

    protected HttpResponse connectionResponse(String url) throws IOException {
        Response execute = Request.Get(url)
                                //  .connectTimeout(configuration.getNumber(ConfigUtils.SERVICE_TIMEOUT))
                                  .execute();
        return execute.returnResponse();
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public abstract void initConnection();

}