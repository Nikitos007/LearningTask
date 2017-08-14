package ua.com.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.dao.CRUDOperationsDao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created on 07.08.17.
 */
@Repository
public abstract class CRUDOperationsDaoHibernateImpl<T, E extends Number> implements CRUDOperationsDao<T, E> {

    private static final Logger LOG = LoggerFactory.getLogger(CRUDOperationsDaoHibernateImpl.class);

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
    @Transactional(readOnly = true)
    public List<T> findAll() {
        String hql = "FROM " + genericClass.getSimpleName();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        List<T> valueList = (List<T>) query.list();
        session.close();
        return valueList;
    }

    @Override
    @Transactional(readOnly = true)
    public T getById(E id) {
        Session session = sessionFactory.openSession();
        T value = (T) session.get(genericClass, id);
        session.close();
        return value;
    }

    @Override
    @Transactional
    public void save(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
    }

}