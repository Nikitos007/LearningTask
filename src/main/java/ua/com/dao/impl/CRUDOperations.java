package ua.com.dao.impl;

import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.dao.CRUDOperationsDao;
import ua.com.utils.HibernateSessionFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 07.08.17.
 */
public abstract class CRUDOperations<T, E extends Number> implements CRUDOperationsDao<T, E>{
    private static final Logger LOG = LoggerFactory.getLogger(CRUDOperations.class);
    private Class<T> genericClass;
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

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
        String hql = "FROM " + genericClass.getSimpleName();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        List<T> valueList = (List<T>) query.list();
        session.close();
        return valueList;
    }

    @Override
    public T getById(E id) {
        Session session = sessionFactory.openSession();
        T value = (T) session.get(genericClass, id);
        session.close();
        return value;
    }

    @Override
    public void save(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

}