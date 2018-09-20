package ungs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ungs.connectors.AbstractConnector;
import ungs.model.Configuration;
import ungs.utils.ReaderValuesConfiguration;
import ungs.utils.exceptions.ConfigurationException;
import java.util.Map;

public abstract class Service<T extends AbstractConnector> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
   // protected Configuration configuration;
    protected T connector;

    public Service(T connector, String configurationFile) {
        this.connector = connector;
        //this.configuration = getConfiguration(configurationFile);
        this.connector.setConfiguration(getConfiguration(configurationFile));
        this.connector.init();
    }

    private Configuration getConfiguration(String pathConfiguration) {
        Map map = null;
        try {
           map = new ReaderValuesConfiguration(pathConfiguration).getValues();
        } catch (ConfigurationException ce) {
            this.logger.warn("El servicio no puede levantarse porque ocurrio el siguiente error: " + ce.getMessage());
        }
        return new Configuration(map);
    }

}