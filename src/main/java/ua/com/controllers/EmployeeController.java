package ua.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.exceptions.ValidateException;
import ua.com.models.Department;
import ua.com.models.Employee;
import ua.com.repository.EmployeeRepository;
import ua.com.services.EmployeeService;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.Arrays;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class EmployeeController {

    private Employee employee = new Employee();
    private Department selectedDepartment = new Department();

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

@Autowired
private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    public EmployeeRepository getEmployeeRepository() {
//        return employeeRepository;
//    }
//
//    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }


    public List<Employee> findByDepartmentId(){
        return employeeService.findByDepartmentId(selectedDepartment.getDepartmentId());
    }

    public void save() {
        employee.setDepartment(selectedDepartment);
        try {
            employeeService.save(employee);
        } catch (ValidateException e) {
            e.printStackTrace();
        }
        employee = new Employee();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee saved!", null));
    }

    public void delete(Employee employee) {
        employeeService.delete(employee);
        employee = new Employee();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee deleted!", null));
    }

    public void clear() {
        employee = new Employee();
    }

}
