package ua.com.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.controllers.Controller;
import ua.com.model.Department;
import ua.com.services.DepartmentService;

import javax.portlet.*;
import java.io.IOException;
import java.util.List;

/**
 * Created on 12.07.17.
 */
@Component(value = "/controller/viewAllDepartment")
public class ViewAllDepartmentCommand implements Controller {

    private final DepartmentService departmentService;

    @Autowired
    public ViewAllDepartmentCommand(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public <T extends PortletRequest, E extends PortletResponse> void execute(T request, E response, PortletContext portletContext) throws IOException, PortletException {
        List<Department> departmentList = departmentService.viewAllDepartment();
        request.setAttribute("departmentList", departmentList);
        request.setAttribute("jspView", "/WEB-INF/jsp/viewAllDepartment.jsp");
    }
}
