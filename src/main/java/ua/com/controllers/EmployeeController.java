package ua.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.models.Department;
import ua.com.models.Employee;
import ua.com.repository.EmployeeRepository;


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

    @PostConstruct
    private void init() {
        selectedDepartment.setDepartmentId(1L);
        employee.setDepartment(selectedDepartment);
    }

    public Department getSelectedDepartment() {
        return selectedDepartment;
    }

    public void setSelectedDepartment(Department selectedDepartment) {
        this.selectedDepartment = selectedDepartment;
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> findByDepartmentId(){
    return employeeRepository.findByDepartmentId(selectedDepartment.getDepartmentId());
    }

    public void save() {
        employeeRepository.save(employee);
        employee = new Employee();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee saved!", null));
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
        employee = new Employee();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee deleted!", null));
    }

    public void findByEmail(String email) {
    }


}
