package ungs.connectors;

import ungs.model.Configuration;

import java.util.List;

public interface AbstractConnector<T> {

    boolean isAvailable();
    List<T> find(String url);
    void setConfiguration(Configuration configuration);
}
