package ua.com.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.controllers.Controller;
import ua.com.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created on 12.07.17.
 */
@Component(value = "/controller/viewDepartment")
public class ViewDepartmentCommand implements Controller {

    @Autowired
    private EmployeeService employeeService;

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));
        request.setAttribute("employeeList", employeeService.viewEmployeeByDepartmentId(departmentId));
        request.getRequestDispatcher("/WEB-INF/jsp/aboutDepartment.jsp").forward(request, response);
    }
}
