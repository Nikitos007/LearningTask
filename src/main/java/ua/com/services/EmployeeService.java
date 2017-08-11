package ua.com.services;

import ua.com.exception.ValidFieldException;
import ua.com.model.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * Created on 13.07.17.
 */
public interface EmployeeService {
    Employee getEmployeeById(Long employeeId);

    void deleteEmployee(Employee employee);

    void saveEmployee(Employee employee) throws ValidFieldException;

    List<Employee> viewEmployeeByDepartmentId(Long departmentId);
}
