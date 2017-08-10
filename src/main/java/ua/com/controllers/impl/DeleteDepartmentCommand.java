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
import java.sql.SQLException;

@Component(value = "/controller/deleteDepartment")
public class DeleteDepartmentCommand implements Controller {

    @Autowired
    private DepartmentService departmentService;

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        Long departmentId = ParamUtils.StringToLong(request.getParameter("departmentId"));
        Department department = new Department();
        department.setId(departmentId);
        departmentService.delete(department);
        response.sendRedirect("viewAllDepartment");
    }
}
