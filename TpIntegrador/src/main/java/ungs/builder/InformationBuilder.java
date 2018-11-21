package ungs.builder;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ungs.caches.client.ServiceCacheProxy;
import ungs.dto.Theme;
import ungs.internacionalizacion.LenguajeValue;
import ungs.model.Configuration;
import ungs.services.InformationService;
import ungs.services.Service;
import ungs.utils.ConfigUtils;
import ungs.utils.exceptions.ConfigurationException;

public class InformationBuilder {

    private InformationService instance;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public InformationBuilder() {
        this.instance = new InformationService();
    }

    public <T extends Service> InformationBuilder withService(String service, String pathPackage) {
        try {
            Class classService = Class.forName(pathPackage);
            T t = (T) classService.getConstructor(Configuration.class)
                                  .newInstance(new Configuration(ConfigUtils.FOLDER_PATH + service + ".properties"));
            this.instance.addService(t);
        } catch (Exception e) {
            logger.error("Error", e);
            throw new ConfigurationException("No existe el servicio para: " + service + " , package " + pathPackage);
        }
        return this;
    }

    public InformationBuilder withTheme(String theme) {
        this.instance.addTheme(new Theme(theme, Lists.newArrayList()));
        return this;
    }

    public InformationBuilder withConfiguration(Configuration configuration) {
        this.instance.addConfiguration(configuration);
        return this;
    }

    public InformationBuilder setLenguajeValue(String lenguaje) {
        this.instance.setLenguajeValue(new LenguajeValue(lenguaje));
        return this;
    }

    public InformationService build() {
        this.instance.setServiceProxy(new ServiceCacheProxy());
        return this.instance;
    }

}