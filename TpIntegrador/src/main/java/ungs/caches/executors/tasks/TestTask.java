package ungs.caches.executors.tasks;

import com.google.common.collect.Lists;
import ungs.caches.client.MongoDbCacheClient;
import ungs.caches.client.ServiceCacheProxy;
import ungs.caches.executors.TaskExecutor;
import ungs.caches.executors.TaskJob;
import ungs.caches.executors.tasks.impl.LoadCacheTask;
import ungs.connectors.RssConnector;
import ungs.connectors.TwitterConnector;
import ungs.model.Configuration;
import ungs.services.RssService;
import ungs.services.Service;
import ungs.services.TwitterService;
import ungs.transformers.RssTransformer;
import ungs.transformers.TwitterTransformer;
import ungs.utils.ConfigUtils;

import java.util.List;
import java.util.TimerTask;

public class TestTask {

    public static void main(String[] args) {

        List<Service> services = Lists.newArrayList(new TwitterService(new TwitterTransformer(), new TwitterConnector(), new Configuration(ConfigUtils.TWITTER_FILE)));
        System.out.println("Threads 1 :" + Thread.activeCount());
        TaskExecutor taskExecutor = new TaskExecutor(new TaskJob(new LoadCacheTask(new MongoDbCacheClient(), services)), 1L);
        System.out.println("Threads 2 :" + Thread.activeCount());
        taskExecutor.run();
        System.out.println("Threads 3 :" + Thread.activeCount());
    }


}
