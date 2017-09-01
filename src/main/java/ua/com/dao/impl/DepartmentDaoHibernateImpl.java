package ua.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.dao.DepartmentDao;
import ua.com.model.Department;
import ua.com.utils.HibernateSessionFactory;

/**
 * Created on 11.07.17.
 */
@Repository
public class DepartmentDaoHibernateImpl extends CRUDOperationsDaoHibernateImpl<Department, Long> implements DepartmentDao {

    private SessionFactory sessionFactory;

    @Autowired
    public DepartmentDaoHibernateImpl(HibernateSessionFactory hibernateSessionFactory) {
        super(hibernateSessionFactory);
        sessionFactory = hibernateSessionFactory.getSessionFactory();
    }

    @Override
    public Department getByName(String name) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Department.class);
        criteria.add(Restrictions.eq("name", name));
        Department departmentResult = (Department) criteria.uniqueResult();
        session.close();
        return departmentResult;
    }

}
