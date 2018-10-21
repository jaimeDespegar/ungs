package ungs.caches.client;

import java.util.List;

public interface CacheClient<T> {

    void insert(T t);
    void update(T t);
    void delete(T t);
    void cleanCache();
    List<T> readAll();

}