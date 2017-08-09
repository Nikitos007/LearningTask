package ua.com.utils.validation;

import net.sf.oval.constraint.CheckWithCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.dao.EmployeeDao;
import ua.com.dao.impl.EmployeeDaoHibernateImpl;
import ua.com.model.Employee;


public class ValidationUniqueEmployeeEmail implements CheckWithCheck.SimpleCheck {

    private static final Logger LOG = LoggerFactory.getLogger(ValidationUniqueEmployeeEmail.class);
    private static EmployeeDao employeeDao = new EmployeeDaoHibernateImpl();

    @Override
    public boolean isSatisfied(Object employeeObj, Object value) {
        Employee employeeRequest = (Employee) employeeObj;
        Employee employee = employeeDao.getByEmail(employeeRequest);
        if (employee == null) {
            return true;
        }
        return employeeRequest.getId() != null && employee.getId() != null && employee.getId().equals(employeeRequest.getId());
    }
}
