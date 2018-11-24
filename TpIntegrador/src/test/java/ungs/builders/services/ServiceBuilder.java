package ungs.builders.services;

import ungs.connectors.impl.AbstractConnector;
import ungs.connectors_stub.RssConnectorStub;
import ungs.connectors_stub.TwitterConnectorStub;
import ungs.filters.FilterExecutor;
import ungs.filters.filterFactory.impl.RssFilterFactory;
import ungs.filters.filterFactory.impl.TwitterFilterFactory;
import ungs.model.Configuration;
import ungs.services.RssService;
import ungs.services.Service;
import ungs.services.TwitterService;
import ungs.transformers.RssTransformer;
import ungs.transformers.TwitterTransformer;

import java.util.Map;

public class ServiceBuilder {

    private Service service;

    private ServiceBuilder(){}

    public static ServiceBuilder create() {
        return new ServiceBuilder();
    }

    public ServiceBuilder buildTwitter(String path) {
        return buildTwitter(new Configuration(path));
    }

    public ServiceBuilder buildTwitter(Map values) {
        return buildTwitter(new Configuration(values));
    }

    public ServiceBuilder buildTwitter(Configuration configuration) {
        AbstractConnector tConnector = new TwitterConnectorStub(configuration);
        this.service = new TwitterService(new TwitterTransformer(), tConnector, new TwitterFilterFactory(tConnector),
                                          new FilterExecutor(), configuration);
        return this;
    }

    public ServiceBuilder buildRss(String path) {
        return buildRss(new Configuration(path));
    }

    public ServiceBuilder buildRss(Map values) {
        return buildRss(new Configuration(values));
    }

    public ServiceBuilder buildRss(Configuration configuration) {
        AbstractConnector connector = new RssConnectorStub(configuration);
        this.service = new RssService(new RssTransformer(), connector, new RssFilterFactory(connector),new FilterExecutor(), configuration);
        return this;
    }

    public<T> T build() {
        return (T) service;
    }

}