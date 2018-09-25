package ungs.services;

import com.google.common.collect.Lists;
import ungs.model.InformationDto;
import ungs.utils.ConfigUtils;

import java.util.List;

public class InformationService {

    private List<Service> services;


    public InformationService() {
        this.services = Lists.newArrayList();
    }

    private void init() {
        List<String> filesByServices = Lists.newArrayList(ConfigUtils.RSS_FILE, ConfigUtils.TWITTER_FILE);
    }

    public List<InformationDto> getAllInformationBySuppliers() {
        List<InformationDto> items = Lists.newArrayList();
        // TODO tengo que pedirle a todos los proveedores la info e ir agrupandolo en esta lista!
        return items;
    }

}