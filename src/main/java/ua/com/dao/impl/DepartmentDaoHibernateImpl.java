package ua.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.dao.DepartmentDao;
import ua.com.model.Department;

/**
 * Created on 11.07.17.
 */
@Repository
public class DepartmentDaoHibernateImpl extends CRUDOperations<Department, Long> implements DepartmentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Department getByName(String name) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Department.class);
        criteria.add(Restrictions.eq("departmentName", name));
        Department departmentResult = (Department) criteria.uniqueResult();
        session.close();
        return departmentResult;
    }


}
