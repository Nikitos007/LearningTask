package ua.com.controllers.impl;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.controllers.Controller;
import ua.com.exception.ValidFieldException;
import ua.com.model.Department;
import ua.com.services.DepartmentService;
import ua.com.utils.ParamUtils;
import ua.com.utils.RedirectUtil;

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
                RedirectUtil.redirect(request, (ActionResponse) response, "/controller/viewAllDepartment");
            }
        } catch (ValidFieldException e) {
            LOG.debug("Not valid fields for save department: {}", department);
//            RedirectUtil.forwardValidException(request, "/WEB-INF/jsp/saveDepartment.jsp", e.getErrorsMap());

//            PortletSession session = request.getPortletSession();
//            session.setAttribute("sessionUri", "/WEB-INF/jsp/saveDepartment.jsp", PortletSession.APPLICATION_SCOPE);
//            session.setAttribute("errorMessageMap", e.getErrorsMap(), PortletSession.APPLICATION_SCOPE);
//            session.setAttribute("department", department, PortletSession.APPLICATION_SCOPE);
        }
    }

    private <T extends PortletRequest> Department getDepartmentFromRequest(T request) {
        String departmentIdStr = ParamUtil.getString(request, "departmentId");
        String departmentName = ParamUtil.getString(request, "departmentName");
        Department department = new Department();
        department.setId(ParamUtils.StringToLong(departmentIdStr));
        department.setName(ParamUtils.verifyString(departmentName));
        return department;
    }

}
