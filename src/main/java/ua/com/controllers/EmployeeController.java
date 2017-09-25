package ua.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.exceptions.ValidateException;
import ua.com.models.Department;
import ua.com.models.Employee;
import ua.com.services.EmployeeService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ManagedBean
@ViewScoped
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private Employee employee = new Employee();

    private Department selectedDepartment = new Department();

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public Department getSelectedDepartment() {
        return selectedDepartment;
    }

    public void setSelectedDepartment(Department selectedDepartment) {
        this.selectedDepartment = selectedDepartment;
    }


    public List<Employee> findByDepartmentId() {
        return employeeService.findByDepartmentId(selectedDepartment.getDepartmentId());
    }


    private Map<String, String> validateErrors = new HashMap<>();
    private Integer sizeOfErrors;


    public Integer getSizeOfErrors() {
        return sizeOfErrors;
    }

    public String getError(String key) {
        String error = validateErrors.get(key);
        validateErrors.remove(key);
        return error;
    }

    public void validate() {
        try {
            sizeOfErrors = 0;
            employeeService.validate(employee);
        } catch (ValidateException e) {
            workValidateException(e);
        }
    }

    private void workValidateException(ValidateException e) {
        validateErrors.clear();
        validateErrors = e.getErrorsMap();
        sizeOfErrors = validateErrors.size();
    }


    public void save() {
        Department department = new Department();
        department.setDepartmentId(selectedDepartment.getDepartmentId());
        employee.setDepartment(department);
        try {
            employeeService.save(employee);
        } catch (ValidateException e) {
            workValidateException(e);
            for (Map.Entry<String, String> entry : e.getErrorsMap().entrySet()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, entry.getValue(), null));
            }
            return;
        }
        employee = new Employee();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee saved!", null));
    }

    public void delete(Employee employee) {
        employeeService.delete(employee);
        this.employee = new Employee();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee deleted!", null));
    }

    public void clear() {
        employee = new Employee();
    }

}
