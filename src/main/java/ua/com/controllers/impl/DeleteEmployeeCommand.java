package ua.com.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.controllers.Controller;
import ua.com.model.Employee;
import ua.com.services.EmployeeService;
import ua.com.utils.ParamUtils;
import ua.com.utils.RedirectUtil;

import javax.portlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 12.07.17.
 */
@Component(value = "/controller/deleteEmployee")
public class DeleteEmployeeCommand implements Controller {

    private final EmployeeService employeeService;

    @Autowired
    public DeleteEmployeeCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public <T extends PortletRequest, E extends PortletResponse> void execute(T request, E response, PortletContext portletContext) throws IOException, PortletException {
        String employeeIdStr = request.getParameter("employeeId");
        Long employeeId = ParamUtils.StringToLong(employeeIdStr);
        Employee employee = employeeService.getEmployeeById(employeeId);
        employeeService.deleteEmployee(employee);
        if (response instanceof ActionResponse) {
            RedirectUtil.redirect(request, (ActionResponse) response, "/controller/viewAllDepartment");
        }

//        ((ActionResponse)response).sendRedirect("/");
//        response.sendRedirect("viewDepartment?departmentId=" + employee.getDepartment().getId());
    }
}
