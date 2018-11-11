package ungs.caches.client;

import ungs.caches.dao.CrudDao;
import java.util.List;

public interface CacheClient<T> extends CrudDao<T> {

    void cleanCache();
    List<T> findByOrigin(String origin);

}