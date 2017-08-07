package ua.com.controllers.impl;

import ua.com.controllers.Controller;
import ua.com.model.Employee;
import ua.com.services.EmployeeService;
import ua.com.services.impl.EmployeeServiceImpl;
import ua.com.utils.ParamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created on 12.07.17.
 */
public class DeleteEmployeeCommand implements Controller {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));
        Employee employee = getEmployeeFromRequest(request);
        employeeService.deleteEmployee(employee);
        response.sendRedirect("viewDepartment?departmentId=" + departmentId);
    }

    private Employee getEmployeeFromRequest(HttpServletRequest request) {
        String employeeIdStr = request.getParameter("employeeId");
        Long employeeId = ParamUtils.StringToLong(employeeIdStr);
        Employee employee = new Employee();
        employee.setId(employeeId);
        return employee;
    }
}
