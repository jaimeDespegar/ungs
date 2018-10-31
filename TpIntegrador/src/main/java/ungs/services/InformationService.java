package ungs.services;

import com.google.common.collect.Lists;
import ungs.builder.InformationBuilder;
import ungs.caches.client.ServiceCacheProxy;
import ungs.dto.Theme;
import ungs.internacionalizacion.LenguajeValue;
import ungs.model.Configuration;
import ungs.model.InformationDto;
import ungs.utils.ConfigUtils;
import java.util.List;
import java.util.function.Function;

public class InformationService {

    private List<Service> services;
    private List<Theme> themes;
    private Configuration configuration;
    private ServiceCacheProxy serviceCacheProxy;
    private LenguajeValue lenguajeValue;


    public InformationService() {
        this.services = Lists.newArrayList();
        this.themes = Lists.newArrayList();
    }

    public List<String> getThemes() {
        return Lists.newArrayList();
    }

    public List<InformationDto> getInformation() {
        return serviceCacheProxy.getData();
    }

/*    public List<InformationDto> getInformation() {
        List<InformationDto> all = Lists.newArrayList();
        for (Service s : services) {
            List<InformationDto> l = s.getData();
            all.addAll(l);
        }
        return all;
    }*/

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
}