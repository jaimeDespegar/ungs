package ungs.helpers;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import ungs.utils.exceptions.ConnectionException;

public class ConnectionHelper {

    public static boolean isOkResponse(HttpResponse response) {
        return response.getStatusLine().getStatusCode()>=200 &&
               response.getStatusLine().getStatusCode()<300;
    }

    public static void throwConnectionException(Logger logger, String message, Exception e) {
        logger.error(message, e);
        throw new ConnectionException(message, e);
    }

}