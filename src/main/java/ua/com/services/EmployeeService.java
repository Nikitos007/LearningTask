package ua.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.exceptions.ValidateException;
import ua.com.models.Employee;
import ua.com.repository.DepartmentRepository;
import ua.com.repository.EmployeeRepository;
import ua.com.utils.validations.ValidationOval;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Employee> findByDepartmentId(Long id){
        return employeeRepository.findByDepartmentId(id);
    }

    public void validate(Employee employee) throws ValidateException {
        ValidationOval.fieldsValidation(employee);
    }

    public void save(Employee employee) throws ValidateException {
        validate(employee);
        employeeRepository.save(employee);
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }


}
