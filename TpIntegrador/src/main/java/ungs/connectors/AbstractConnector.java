package ungs.connectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ungs.helpers.ConnectionHelper;
import ungs.model.Configuration;
import ungs.utils.ConfigUtils;

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

    public String getJsonObjectFromPath(String pathJson) {
        String result = "";
        try {
            result = this.connection(pathJson);
        } catch (IOException e) {
            ConnectionHelper.throwConnectionException(logger, "Error", e);
        }
        logger.info("A satisfactory connection was established with the Rest Application");
        return result;
    }

    private String connection(String pathRestJson) throws IOException{
        HttpResponse response = connectionResponse(pathRestJson);
        if (ConnectionHelper.isOkResponse(response)) {
            return EntityUtils.toString(response.getEntity());
        }
        return null;
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