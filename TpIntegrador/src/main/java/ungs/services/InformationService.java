package ungs.services;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ungs.caches.client.ServiceCacheProxy;
import ungs.dto.Theme;
import ungs.filters.FilterManager;
import ungs.internacionalizacion.*;
import ungs.model.*;
import java.util.List;

public class InformationService {

    private List<Service> services;
    private List<Theme> themes;
    private Configuration configuration;
    private ServiceCacheProxy serviceCacheProxy;
    private LenguajeValue lenguajeValue;
    private LenguajeConfiguration lenguajeConfiguration;
    private FilterManager filterManager;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public InformationService() {
        this.services = Lists.newArrayList();
        this.themes = Lists.newArrayList();
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public List<InformationDto> getInformation() {
        return serviceCacheProxy.getData(services);
    }

    public List<Service> getServices() {
        return services;
    }

    public boolean addService(Service service) {
        return this.services.add(service);
    }

    public boolean addTheme(Theme theme) {
        return this.themes.add(theme);
    }

    public void addConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setServiceProxy(ServiceCacheProxy proxy) {
        this.serviceCacheProxy = proxy;
    }

    public void setLenguajeValue(LenguajeValue lenguajeValue) {
        this.lenguajeValue = lenguajeValue;
    }

    public LenguajeValue getLenguajeValue() {
        return lenguajeValue;
    }

    public void setLenguajeConfiguration(LenguajeConfiguration lenguajeConfiguration) {
        this.lenguajeConfiguration = lenguajeConfiguration;
    }

    public List<InformationDto> searchByFilters(ViewFilter viewFilter) {
        return Lists.newArrayList();
    }

    public boolean setValueConfiguration(String key, String value) {
        return configuration.setValueConfiguration(key, value);
    }

}