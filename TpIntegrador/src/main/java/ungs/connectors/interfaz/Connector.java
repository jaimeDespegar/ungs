package ungs.connectors.interfaz;

import java.util.List;

public interface Connector<T> {

    boolean isAvailable();
    List<T> find(String url);

}