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
    public <T extends PortletRequest, E extends PortletResponse> void execute(T request, E response, PortletContext portletContext) throws IOException, PortletException {
        Long departmentId = ParamUtils.StringToLong(request.getParameter("departmentId"));
        Department department = new Department();
        department.setId(departmentId);
        departmentService.delete(department);
        if (response instanceof ActionResponse) {
            ActionResponse actionResponse = (ActionResponse)response;
            actionResponse.sendRedirect("/WEB-INF/jsp/saveDepartment.jsp");
        }
        PortletSession session = request.getPortletSession();
        session.setAttribute("doView","false", PortletSession.APPLICATION_SCOPE);
    }
}
