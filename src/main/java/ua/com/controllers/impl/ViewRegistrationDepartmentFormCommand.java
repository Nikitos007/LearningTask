package ua.com.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.controllers.Controller;
import ua.com.model.Department;
import ua.com.services.DepartmentService;
import ua.com.utils.ParamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 13.07.17.
 */
@Component(value = "/controller/viewRegistrationDepartmentForm")
public class ViewRegistrationDepartmentFormCommand implements Controller {

    private final DepartmentService departmentService;

    @Autowired
    public ViewRegistrationDepartmentFormCommand(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long departmentId = ParamUtils.StringToLong(request.getParameter("departmentId"));
        if (departmentId != null) {
            Department department = departmentService.getDepartmentById(departmentId);
            department.setName(department.getName());
            request.setAttribute("department", department);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/saveDepartment.jsp").forward(request, response);
    }
}
