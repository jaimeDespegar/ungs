package ungs.connectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.InputStream;

public class AbstractRssConnector {

    public AbstractRssConnector() {

    }

    public InputStream connectionStatus(String url) {
        InputStream inputResponse = null;
        try {
            HttpResponse response = this.connection(url);
            if (isOkResponse(response)) {
                inputResponse = response.getEntity().getContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputResponse;
    }

    private HttpResponse connection(String url) throws Exception{
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(url);
        return httpClient.execute(getRequest);
    }

    private boolean isOkResponse(HttpResponse response) {
        return response.getStatusLine().getStatusCode()>=200 &&
               response.getStatusLine().getStatusCode()<300;
    }

    protected boolean isServiceOk(String url) {
        try {
            return isOkResponse(connection(url));
        } catch (Exception e) {
            return false;
        }
     }


    public static void main(String[] args) throws Exception{
        AbstractRssConnector a = new AbstractRssConnector();
        a.connection("");
    }

}