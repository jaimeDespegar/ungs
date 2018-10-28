package ungs.caches.client;

import java.util.List;

public interface CacheClient<T> {

    boolean insert(T t);
    boolean update(T t);
    boolean delete(T t);
    void cleanCache();
    List<T> readAll();
    List<T> findByOrigin(String origin);

}