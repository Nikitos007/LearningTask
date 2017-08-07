package ua.com.controllers.impl;

import ua.com.controllers.Controller;
import ua.com.model.Department;
import ua.com.services.DepartmentService;
import ua.com.services.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created on 12.07.17.
 */
public class ViewAllDepartmentCommand implements Controller {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        List<Department> departmentList = departmentService.viewAllDepartment();
        request.setAttribute("departmentList", departmentList);
        request.getRequestDispatcher("/WEB-INF/jsp/viewAllDepartment.jsp").forward(request, response);
    }
}
