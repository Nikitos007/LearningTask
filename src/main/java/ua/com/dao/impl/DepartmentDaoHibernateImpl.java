package ua.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import ua.com.dao.DepartmentDao;
import ua.com.model.Department;
import ua.com.utils.HibernateSessionFactory;

import java.sql.SQLException;

/**
 * Created on 11.07.17.
 */
public class DepartmentDaoHibernateImpl extends CRUDOperations<Department, Long> implements DepartmentDao {

    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    @Override
    public Department getByName(Department department) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Department.class);
        criteria.add(Restrictions.eq("name", department.getName()));
        Department departmentResult = (Department) criteria.uniqueResult();
        session.close();
        return departmentResult;
    }

}
