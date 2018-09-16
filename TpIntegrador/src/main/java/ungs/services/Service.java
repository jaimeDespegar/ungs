package ungs.services;

import ungs.connectors.AbstractConnector;
import ungs.model.Configuration;
import ungs.utils.ReaderValuesConfiguration;

public abstract class Service {

    protected Configuration configuration;
    protected AbstractConnector connector;

    public Service(AbstractConnector connector, String configurationFile) {
        this.configuration = new Configuration(new ReaderValuesConfiguration(configurationFile).getValues());
        this.connector = connector;
        this.connector.setConfiguration(configuration);
    }

}