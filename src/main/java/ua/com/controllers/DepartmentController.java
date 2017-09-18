package ua.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.models.Department;
import ua.com.services.DepartmentService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.List;

//@ManagedBean
@Component
public class DepartmentController {

//    @ManagedProperty("#{departmentService}")
    @Autowired
    private DepartmentService departmentService;

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public String showHello() {
        return departmentService.sayHello();
    }

    public void findAll() {
        List<Department> departmentList = departmentService.findAll();
//        FacesContext.getCurrentInstance().addMessage
//                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Check removed!", null));
    }

}
