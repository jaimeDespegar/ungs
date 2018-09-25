package ungs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ungs.connectors.AbstractConnector;
import ungs.model.Configuration;
import ungs.model.InformationDto;
import ungs.transformers.TransformerInformation;
import ungs.utils.ReaderValuesConfiguration;
import ungs.utils.exceptions.ConfigurationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Service<T extends AbstractConnector, OBJECT, TRANSFORMER extends TransformerInformation<OBJECT>> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected T connector;
    protected TRANSFORMER transformer;

    public Service(TRANSFORMER transformer, T connector, String configurationFile) {
        this.transformer = transformer;
        this.connector = connector;
        this.connector.setConfiguration(getConfiguration(configurationFile));
        this.connector.initConnection();
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

    public List<InformationDto> getInformation(List<OBJECT> objects) {
        return objects.stream().map(obj -> transformer.transform(obj)).collect(Collectors.toList());
    }

}