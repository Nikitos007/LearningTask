package ua.com.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.controllers.Controller;
import ua.com.services.EmployeeService;

import javax.portlet.*;
import java.io.IOException;

/**
 * Created on 12.07.17.
 */
@Component(value = "/controller/viewDepartment")
public class ViewDepartmentCommand implements Controller {

    private final EmployeeService employeeService;

    @Autowired
    public ViewDepartmentCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public <T extends PortletRequest, E extends PortletResponse> void execute(T request, E response, PortletContext portletContext) throws IOException, PortletException {
        Long departmentId = (Long) request.getAttribute("departmentId");
        if (departmentId == null) {
            String departmentIdStr = request.getParameter("departmentId");
            departmentId = Long.parseLong(departmentIdStr);
        }
        request.setAttribute("employeeList", employeeService.viewEmployeeByDepartmentId(departmentId));
        request.setAttribute("jspView", "/WEB-INF/jsp/aboutDepartment.jsp");
    }
}
