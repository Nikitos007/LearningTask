package ua.com.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.dao.EmployeeDao;
import ua.com.exception.ValidFieldException;
import ua.com.model.Employee;
import ua.com.services.EmployeeService;
import ua.com.utils.validation.ValidationOval;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> viewEmployeeByDepartmentId(Long departmentId) {
        return employeeDao.getByDepartmentId(departmentId);
    }

    public void saveEmployee(Employee employee) throws ValidFieldException {
        ValidationOval.fieldsValidation(employee);
        employeeDao.save(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeDao.delete(employee);
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeDao.getById(employeeId);
    }

}
