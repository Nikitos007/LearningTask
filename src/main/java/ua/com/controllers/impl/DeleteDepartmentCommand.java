package ua.com.controllers.impl;

import com.liferay.portal.util.PortalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.controllers.Controller;
import ua.com.model.Department;
import ua.com.services.DepartmentService;
import ua.com.utils.ParamUtils;

import javax.portlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(value = "/controller/deleteDepartment")
public class DeleteDepartmentCommand implements Controller {

    private final DepartmentService departmentService;

    @Autowired
    public DeleteDepartmentCommand(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void execute(PortletRequest request, PortletResponse response, PortletContext portletContext) throws IOException, PortletException {
        Long departmentId = ParamUtils.StringToLong(request.getParameter("departmentId"));
        Department department = new Department();
        department.setId(departmentId);
        departmentService.delete(department);


        request.setAttribute("uri", "/controller/viewAllDepartment");
        PortletRequestDispatcher requestDispatcher = portletContext.getRequestDispatcher("/Portlet");
        requestDispatcher.include(request, response);

        //TODO in this place should be action - sendRedirect()...
    }
}
