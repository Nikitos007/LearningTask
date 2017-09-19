package ua.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ua.com.models.Department;
import ua.com.services.DepartmentService;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ManagedBean(name = "departmentController")
@ViewScoped
public class DepartmentController {

    private Department department = new Department();

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    //    @ManagedProperty("#{departmentService}")
    @Autowired
    private DepartmentService departmentService;

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public List<Department> findAll() {
        return departmentService.findAll();
    }

    public void viewDepartmentSaveForm() {

    }

    public void save() {
       departmentService.save(department);
       department = new Department();
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Department saved!", null));
    }

    public void delete(Department department) {
        departmentService.delete(department);
        department = new Department();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Department deleted!", null));
    }

    public void viewEmployees() {

    }


//
//    public void save(ActionEvent actionEvent) {
//        addMessage("Data saved");
//    }
//
//    public void update(ActionEvent actionEvent) {
//        addMessage("Data updated");
//    }
//
//    public void delete(ActionEvent actionEvent) {
//        addMessage("Data deleted");
//    }
//
//    public void addMessage(String summary) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
//        FacesContext.getCurrentInstance().addMessage(null, message);
//    }
}
