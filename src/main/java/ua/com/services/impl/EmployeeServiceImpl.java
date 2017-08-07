package ua.com.services.impl;

import ua.com.dao.EmployeeDao;
import ua.com.dao.impl.EmployeeDaoDatababaseImpl;
import ua.com.exception.ValidFieldException;
import ua.com.model.Employee;
import ua.com.services.EmployeeService;
import ua.com.utils.validation.ValidationOval;

import java.sql.SQLException;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao = new EmployeeDaoDatababaseImpl();

    public List<Employee> viewEmployeeByDepartmentId(Long departmentId) throws SQLException {
        return employeeDao.getByDepartmentId(departmentId);
    }

    public void saveEmployee(Employee employee) throws SQLException, ValidFieldException {
        ValidationOval.fieldsValidation(employee);
        employeeDao.save(employee);
    }

    public void deleteEmployee(Employee employee) throws SQLException {
        employeeDao.delete(employee);
    }

    public Employee getEmployeeById(Long employeeId) throws SQLException {
        return employeeDao.getById(employeeId);
    }

}
