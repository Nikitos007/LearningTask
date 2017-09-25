package ua.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ua.com.exceptions.ValidateException;
import ua.com.models.Department;
import ua.com.services.DepartmentService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ManagedBean
@SessionScope
public class DepartmentController {

    private Department department = new Department();

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Autowired
    private DepartmentService departmentService;

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    public List<Department> findAll() {
        return departmentService.findAll();
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
            departmentService.validate(department);
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
        try {
            departmentService.save(department);
        } catch (ValidateException e) {
            workValidateException(e);
            for (Map.Entry<String, String> entry : e.getErrorsMap().entrySet()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, entry.getValue(), null));
            }
            return;
        }
        department = new Department();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Department saved!", null));
    }

    public void delete(Department department) {
        departmentService.delete(department);
        this.department = new Department();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Department deleted!", null));
    }

    public void clear() {
        department = new Department();
    }

}
