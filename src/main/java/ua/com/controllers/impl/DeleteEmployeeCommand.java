package ua.com.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.controllers.Controller;
import ua.com.model.Employee;
import ua.com.services.EmployeeService;
import ua.com.utils.ParamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created on 12.07.17.
 */
@Component(value = "/controller/deleteEmployee")
public class DeleteEmployeeCommand implements Controller {

    @Autowired
    private EmployeeService employeeService;

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String employeeIdStr = request.getParameter("employeeId");
        Long employeeId = ParamUtils.StringToLong(employeeIdStr);
        Employee employee = employeeService.getEmployeeById(employeeId);
        employeeService.deleteEmployee(employee);
        response.sendRedirect("viewDepartment?departmentId=" + employee.getDepartment().getId());
    }

}
