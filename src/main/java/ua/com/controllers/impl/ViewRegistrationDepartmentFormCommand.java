package ua.com.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.controllers.Controller;
import ua.com.model.Department;
import ua.com.services.DepartmentService;
import ua.com.utils.ParamUtils;

import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.io.IOException;

@Component(value = "/controller/viewRegistrationDepartmentForm")
public class ViewRegistrationDepartmentFormCommand implements Controller {

    private final DepartmentService departmentService;

    @Autowired
    public ViewRegistrationDepartmentFormCommand(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public <T extends PortletRequest, E extends PortletResponse> void execute(T request, E response, PortletContext portletContext) throws IOException, PortletException {
        String departmentIdStr = request.getParameter("departmentId");
        Long departmentId = ParamUtils.StringToLong(departmentIdStr);
        if (departmentId != null) {
            Department department = departmentService.getDepartmentById(departmentId);
            department.setName(department.getName());
            request.setAttribute("department", department);
        }
        request.setAttribute("jspView", "/WEB-INF/jsp/saveDepartment.jsp");
    }
}
