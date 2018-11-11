package ungs.caches.client;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import ungs.model.InformationDto;
import ungs.services.Service;
import ungs.services.ServiceData;
import java.util.List;

public class ServiceCacheProxy implements ServiceData<InformationDto> {

    private CacheClient cacheClient;
    private Service service;

    public ServiceCacheProxy() {
        this.cacheClient = new MongoDbCacheClient();
    }

    @Override
    public List<InformationDto> getData() {
        List<InformationDto> values = cacheClient.findByOrigin(service.getOrigin());
        if (CollectionUtils.isEmpty(values)) {
            values = service.getData();
            values.forEach(i -> cacheClient.insert(i));
        }
        return values;
    }

    public void setService(Service s) {
        this.service = s;
    }

}