package ungs.connectors;

import java.util.List;

public interface Connector<T> {

    boolean isAvailable();
    List<T> find(String url);

}