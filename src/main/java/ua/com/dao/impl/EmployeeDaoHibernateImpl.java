package ua.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.dao.EmployeeDao;
import ua.com.model.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 07.08.17.
 */

@Repository
public class EmployeeDaoHibernateImpl extends CRUDOperations<Employee, Long> implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional(readOnly = true)
    public List<Employee> getByDepartmentId(Long departmentId) {
        List<Employee> employeeList = new ArrayList<>();
        String hql = "FROM Employee WHERE id_department = :departmentId";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("departmentId", departmentId);
        employeeList = query.list();
        session.close();
        return employeeList;
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getByEmail(String email) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.eq("email", email));
        Employee employeeResult = (Employee) criteria.uniqueResult();
        session.close();
        return employeeResult;
    }
}
