package ungs.utils;

import ungs.connectors.AbstractConnector;
import ungs.utils.exceptions.ConfigurationException;

public class RssCheckUrlUtil {

    public static void isUrlOk(AbstractConnector connector) {
        connector.getConfiguration().getKeysStartWith("rss.theme.").forEach(url -> {
            if (!connector.isServiceOk(connector.getConfiguration().get(url))) {
                throw new ConfigurationException(String.format("La url %s es invalida", url));
            }
        });
    }

}