package ungs.caches.dao;

import java.util.List;

public interface CrudDao<T> {

    boolean insert(T t);
    boolean update(T t);
    boolean delete(T t);
    List<T> readAll();

}