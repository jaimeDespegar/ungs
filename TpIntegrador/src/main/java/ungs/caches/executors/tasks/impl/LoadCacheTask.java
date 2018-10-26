package ungs.caches.executors.tasks.impl;

import ungs.caches.client.CacheClient;
import ungs.services.Service;
import java.util.List;

public class LoadCacheTask extends CacheTask {

    private List<Service> services;

    public LoadCacheTask(CacheClient client, List<Service> services) {
        super(client);
        this.services = services;
    }

    @Override
    public void doExecute() {
        this.services.forEach(service -> loadDataByService(service));
    }

    private void loadDataByService(Service service) {
        service.getData().forEach(i->this.client.insert(i));
    }

}