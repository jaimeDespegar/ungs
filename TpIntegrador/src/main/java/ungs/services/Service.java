package ungs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ungs.connectors.AbstractConnector;
import ungs.model.Configuration;
import ungs.model.InformationDto;
import ungs.transformers.TransformerInformation;
import ungs.utils.ConfigUtils;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Service<C extends AbstractConnector, OBJECT, T extends TransformerInformation<OBJECT>> implements ServiceData<InformationDto> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected C connector;
    protected T transformer;
    protected Configuration configuration;

    public Service(T transformer, C connector, Configuration configuration) {
        this.transformer = transformer;
        this.connector = connector;
        this.configuration = configuration;
        this.connector.setConfiguration(configuration);
        this.connector.initConnection();
    }

    public List<InformationDto> getInformation(List<OBJECT> objects) {
        return objects.stream().map(obj -> transformer.transform(obj)).collect(Collectors.toList());
    }

    public String getOrigin() {
        return configuration.get(ConfigUtils.SERVICE_NAME);
    }

}