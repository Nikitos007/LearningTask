package ua.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.exceptions.ValidateException;
import ua.com.models.Employee;
import ua.com.repository.EmployeeRepository;
import ua.com.utils.validations.ValidationOval;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findByDepartmentId(Long id){
        return employeeRepository.findByDepartmentId(id);
    }

    public void save(Employee employee) throws ValidateException {
        ValidationOval.fieldsValidation(employee);
        employeeRepository.save(employee);
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }


}
