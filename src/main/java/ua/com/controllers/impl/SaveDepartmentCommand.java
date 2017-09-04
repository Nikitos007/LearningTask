package ua.com.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.controllers.Controller;
import ua.com.exception.ValidFieldException;
import ua.com.model.Department;
import ua.com.services.DepartmentService;
import ua.com.utils.ParamUtils;

import javax.portlet.*;
import java.io.IOException;

@Component(value = "/controller/saveDepartment")
public class SaveDepartmentCommand implements Controller {

    private static final Logger LOG = LoggerFactory.getLogger(SaveDepartmentCommand.class);

    private final DepartmentService departmentService;

    @Autowired
    public SaveDepartmentCommand(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public <T extends PortletRequest, E extends PortletResponse> void execute(T request, E response, PortletContext portletContext) throws IOException, PortletException {
        Department department = getDepartmentFromRequest(request);
        try {
            departmentService.saveDepartment(department);
            if (response instanceof ActionResponse) {
                ActionResponse actionResponse = (ActionResponse) response;
                actionResponse.sendRedirect("/");
            }
        } catch (ValidFieldException e) {
            LOG.debug("Not valid fields for save department: {}", department);
            request.setAttribute("errorMessageMap", e.getErrorsMap());
            request.setAttribute("department", department);
            request.setAttribute("jspView", "/WEB-INF/jsp/saveDepartmentForm.jsp");
        }
    }

    private <T extends PortletRequest> Department getDepartmentFromRequest(T request) {
        String departmentIdStr = request.getParameter("departmentId");
        String departmentName = request.getParameter("departmentName");
        Department department = new Department();
        department.setId(ParamUtils.StringToLong(departmentIdStr));
        department.setName(ParamUtils.verifyString(departmentName));
        return department;
    }

}
