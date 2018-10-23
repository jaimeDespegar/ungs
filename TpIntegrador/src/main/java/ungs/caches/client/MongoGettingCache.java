package ungs.caches.client;

import ungs.services.Service;
import java.util.List;

public class MongoGettingCache {

    private CacheClient client;

    public MongoGettingCache () {
        this.client = new MongoDbCacheClient();
    }

    public void searchInformation(List<Service> services) {
     //   services.forEach(s -> s.getInformation(Lists.newArrayList()).forEach(i->));
    }

}
