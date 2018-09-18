package ungs.connectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;

public class AbstractRssConnector {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public AbstractRssConnector() {}

    public InputStream connectionStatus(String url) {
        InputStream inputResponse = null;
        try {
            HttpResponse response = this.connection(url);
            if (UtilConnector.isOkResponse(response)) {
                inputResponse = response.getEntity().getContent();
            }
        } catch (Exception e) {
            logger.warn("No Response RSS Application", e); // TODO hacer algo mas !
        }
        return inputResponse;
    }

    private HttpResponse connection(String url) throws IOException {
        Response execute = Request.Get(url).execute();
        return execute.returnResponse();
    }

    protected boolean isServiceOk(String url) {
        try {
            return UtilConnector.isOkResponse(connection(url));
        } catch (Exception e) {
            return false;
        }
     }

}