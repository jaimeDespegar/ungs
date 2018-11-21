package ungs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ungs.circuitBreaker.ConnectorProxy;
import ungs.connectors.AbstractConnector;
import ungs.filters.FilterExecutor;
import ungs.filters.filterFactory.FilterFactory;
import ungs.model.Configuration;
import ungs.model.InformationDto;
import ungs.transformers.TransformerInformation;
import ungs.utils.ConfigUtils;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Service<C extends AbstractConnector, OBJECT, T extends TransformerInformation<OBJECT>> implements ServiceData<OBJECT> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected C connector;
    protected T transformer;
    protected ConnectorProxy proxy;
    protected Configuration configuration;
    protected FilterFactory filterFactory;
    protected FilterExecutor<OBJECT> filterExecutor;

    public Service(T transformer, C connector, FilterFactory filterFactory, FilterExecutor filterExecutor, Configuration configuration) {
        this.transformer = transformer;
        this.connector = connector;
        this.filterFactory = filterFactory;
        this.filterExecutor = filterExecutor;
        this.configuration = configuration;
        this.connector.setConfiguration(configuration);
        this.connector.initConnection();
    }

    public Service(Configuration configuration) {
        this.configuration = configuration;
    }

    public Service() {}

    protected void init() {
        this.connector.setConfiguration(configuration);
        this.connector.initConnection();
    }

    public List<InformationDto> getInformation(List<OBJECT> objects) {
        return objects.stream().map(obj -> transformer.transform(obj))
                               .collect(Collectors.toList());
    }

    public boolean isServiceOk() {
        return connector.isAvailable();
    }

    public String getOrigin() {
        return configuration.get(ConfigUtils.SERVICE_NAME);
    }

    public ConnectorProxy getProxy() {
        return proxy;
    }

    public FilterFactory getFilterFactory() {
        return filterFactory;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setValueConfiguration(String key, String value) {
        this.configuration.setValueConfiguration(key, value);
        this.connector.setConfiguration(configuration);
    }

    protected Integer getCountValues() {
        Integer value = configuration.getNumber(String.format("%s.response.count", getOrigin()));
        return value==0 ? ConfigUtils.DEFAULT_COUNT : value;
    }
}