package ua.com.controllers.impl;

import ua.com.controllers.Controller;
import ua.com.services.EmployeeService;
import ua.com.services.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created on 12.07.17.
 */
public class ViewDepartmentCommand implements Controller {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));
        request.setAttribute("employeeList", employeeService.viewEmployeeByDepartmentId(departmentId));
        request.getRequestDispatcher("/WEB-INF/jsp/aboutDepartment.jsp").forward(request, response);
    }
}
