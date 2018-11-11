package ungs.services;

import com.google.common.collect.Lists;
import ungs.caches.client.ServiceCacheProxy;
import ungs.dto.Theme;
import ungs.internacionalizacion.LenguajeConfiguration;
import ungs.internacionalizacion.LenguajeValue;
import ungs.model.Configuration;
import ungs.model.InformationDto;
import java.util.List;

public class InformationService {

    private List<Service> services;
    private List<Theme> themes;
    private Configuration configuration;
    private ServiceCacheProxy serviceCacheProxy;
    private LenguajeValue lenguajeValue;
    private LenguajeConfiguration lenguajeConfiguration;

    public InformationService() {
        this.services = Lists.newArrayList();
        this.themes = Lists.newArrayList();
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public List<InformationDto> getInformation() {
        return serviceCacheProxy.getData();
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

}