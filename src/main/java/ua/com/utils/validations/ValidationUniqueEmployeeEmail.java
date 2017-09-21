package ua.com.utils.validations;

import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.models.Employee;
import ua.com.repository.EmployeeRepository;

@Component
public class ValidationUniqueEmployeeEmail implements CheckWithCheck.SimpleCheck {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public boolean isSatisfied(Object employeeObj, Object value) {
        Employee employeeRequest = (Employee) employeeObj;
        Employee employee = employeeRepository.findByEmail(employeeRequest.getEmail());
        if (employee == null) {
            return true;
        }
        return employeeRequest.getEmployeeId() != null && employee.getEmployeeId() != null && employee.getEmployeeId().equals(employeeRequest.getEmployeeId());
    }
}