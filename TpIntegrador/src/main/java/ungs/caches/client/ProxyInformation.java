package ungs.caches.client;

import ungs.model.InformationDto;
import ungs.services.Service;
import java.util.LinkedList;
import java.util.List;

public class ProxyInformation {

    private CacheClient cacheClient;
    private List<Service> services;

    public ProxyInformation(List<Service> services) {
        this.services = services;
        this.cacheClient = new MongoDbCacheClient();
    }

    public List<InformationDto> getInformation() {
        List<InformationDto> informationDtos = cacheClient.readAll();
        if (informationDtos.isEmpty()) {
            services.forEach(s -> informationDtos.addAll(s.getInformation(new LinkedList())));
        }
        return informationDtos;
    }

}