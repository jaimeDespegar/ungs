package ungs.caches.client;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import ungs.model.InformationDto;
import ungs.services.Service;
import ungs.services.ServiceData;
import java.util.LinkedList;
import java.util.List;

public class ServiceCacheProxy implements ServiceData<InformationDto> {

    private CacheClient cacheClient;
    private List<Service> services;

    public ServiceCacheProxy(List<Service> services) {
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

    @Override
    public List<InformationDto> getData() {
        List<InformationDto> result = Lists.newArrayList();
        for (Service s : services) {
            List<InformationDto> list = cacheClient.findByOrigin(s.getOrigin());
            if (CollectionUtils.isNotEmpty(list)) {
                result.addAll(list);
            } else {
                result.addAll(s.getData());
            }
        }
        return result;
    }

}