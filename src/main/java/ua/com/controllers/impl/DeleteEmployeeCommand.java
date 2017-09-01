package ua.com.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.controllers.Controller;
import ua.com.model.Employee;
import ua.com.services.EmployeeService;
import ua.com.utils.ParamUtils;

import javax.portlet.*;
import java.io.IOException;


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
        String departmentIdStr = request.getParameter("departmentId");
        Long departmentId = ParamUtils.StringToLong(departmentIdStr);
        Employee employee = employeeService.getEmployeeById(employeeId);
        employeeService.deleteEmployee(employee);
        if (response instanceof ActionResponse) {
            ActionResponse actionResponse = (ActionResponse) response;
            portletContext.setAttribute("uri", "/controller/viewDepartment");
            portletContext.setAttribute("departmentId", departmentId);
            actionResponse.sendRedirect("/");
        }
    }

}
