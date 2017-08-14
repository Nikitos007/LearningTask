package ua.com.utils.validation;

import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.dao.EmployeeDao;
import ua.com.model.Employee;

@Component
public class ValidationUniqueEmployeeEmail implements CheckWithCheck.SimpleCheck {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public boolean isSatisfied(Object employeeObj, Object value) {
        Employee employeeRequest = (Employee) employeeObj;
        Employee employee = employeeDao.getByEmail(employeeRequest.getEmail());
        if (employee == null) {
            return true;
        }
        return employeeRequest.getId() != null && employee.getId() != null && employee.getId().equals(employeeRequest.getId());
    }
}
