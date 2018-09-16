package ungs.connectors;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;


public class test {

    public static void main(String[] args) throws Exception{


        HttpClient httpClient = new DefaultHttpClient();

        HttpGet getRequest = new HttpGet("http://tn.com.ar/feed/deportes");

        HttpResponse response = httpClient.execute(getRequest);

        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

        String output = "";
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        httpClient.getConnectionManager().shutdown();

    }


}
