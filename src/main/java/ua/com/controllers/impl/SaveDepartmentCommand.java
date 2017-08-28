package ua.com.controllers.impl;

import com.liferay.portal.kernel.util.ParamUtil;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            ((ActionResponse)response).sendRedirect("/");
        } catch (ValidFieldException e) {
            LOG.debug("Not valid fields for save department: {}", department);
            request.setAttribute("errorMessageMap", e.getErrorsMap());
            request.setAttribute("uri", "/controller/viewRegistrationDepartmentForm");
//            portletContext.getRequestDispatcher("/WEB-INF/jsp/saveDepartment.jsp").include(request, response);
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

// TODO portletResponse instanceof ActionResponse
// if (portletResponse instanceof ActionResponse)
//         {
//         ActionResponse actionResponse = (ActionResponse)portletResponse;
//         String redirectURL = actionResponse.encodeURL(url.toString());
//         actionResponse.sendRedirect( redirectURL );
//         }