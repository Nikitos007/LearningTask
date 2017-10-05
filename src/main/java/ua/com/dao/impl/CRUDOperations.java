package ua.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.dao.CRUDOperationsDao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@Repository
public abstract class CRUDOperations<T, E extends Number> implements CRUDOperationsDao<T, E> {

    private static final Logger LOG = LoggerFactory.getLogger(CRUDOperations.class);

    private Class<T> genericClass;

    @Autowired
    private SessionFactory sessionFactory;

    {
        Type mySuperclass = getClass().getGenericSuperclass();
        Type tType = ((ParameterizedType) mySuperclass).getActualTypeArguments()[0];
        String className = tType.toString().split(" ")[1];
        try {
            genericClass = (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(genericClass);
        for (final Object o : criteria.list()) {
            list.add((T) o);
        }
        return list;
    }

    @Override
    public T getById(E id) {
        Session session = sessionFactory.getCurrentSession();
        T value = (T) session.get(genericClass, id);
        return value;
    }

    @Override
    public void save(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.clear();
        session.saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
    }

}