package ungs.executors.tasks.impl;

import ungs.caches.client.CacheClient;
import ungs.executors.tasks.interfaces.MyTask;

public abstract class CacheTask implements MyTask {

    protected CacheClient client;

    public CacheTask(CacheClient client) {
        this.client = client;
    }

}