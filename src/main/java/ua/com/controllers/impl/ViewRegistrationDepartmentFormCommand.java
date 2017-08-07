package ua.com.controllers.impl;

import ua.com.controllers.Controller;
import ua.com.model.Department;
import ua.com.services.DepartmentService;
import ua.com.services.impl.DepartmentServiceImpl;
import ua.com.utils.ParamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created on 13.07.17.
 */
public class ViewRegistrationDepartmentFormCommand implements Controller {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        Long departmentId = ParamUtils.StringToLong(request.getParameter("departmentId"));
        if (departmentId != null) {
            Department department = departmentService.getDepartmentById(departmentId);
            department.setName(department.getName());
            request.setAttribute("department", department);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/saveDepartment.jsp").forward(request, response);
    }
}
