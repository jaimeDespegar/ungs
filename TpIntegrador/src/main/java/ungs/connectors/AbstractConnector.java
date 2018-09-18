package ungs.connectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ungs.utils.exceptions.ConnectionException;
import java.io.IOException;

public class AbstractConnector {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected boolean isServiceOk(String url) {
        try {
            return this.isOkResponse(connectionResponse(url));
        } catch (IOException e) {
            return false;
        }
    }

    public String getJsonObjectFromPath(String pathJson) {
        String result = "";
        try {
            result = this.connection(pathJson);
        } catch (IOException e) {
            this.throwConnectionException("Error", e);
        }
        logger.info("A satisfactory connection was established with the Rest Application");
        return result;
    }

    private String connection(String pathRestJson) throws IOException{
        HttpResponse response = connectionResponse(pathRestJson);
        if (isOkResponse(response)) {
            return EntityUtils.toString(response.getEntity());
        }
        return null;
    }

    protected HttpResponse connectionResponse(String url) throws IOException {
        Response execute = Request.Get(url).execute();
        return execute.returnResponse();
    }

    protected boolean isOkResponse(HttpResponse response) {
        return response.getStatusLine().getStatusCode()>=200 &&
               response.getStatusLine().getStatusCode()<300;
    }

    protected void throwConnectionException(String message, Exception e) {
        logger.error(message, e);
        throw new ConnectionException(message, e);
    }

}