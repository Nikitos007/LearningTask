package ua.com.dao;

import java.util.List;

/**
 * Created on 08.08.17.
 */
public interface CRUDOperationsDao<T, E extends Number> {

    List<T> findAll();

    T getById(E id);

    void save(T entity);

    void delete(T entity);

}
