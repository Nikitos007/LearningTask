package ua.com.controllers.impl;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;
import ua.com.controllers.Controller;
import ua.com.model.Department;
import ua.com.model.Employee;
import ua.com.utils.ParamUtils;

import javax.portlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created on 17.08.17.
 */
@Component
public class MainPortlet extends GenericPortlet {

    private ApplicationContext applicationContext;
    private Controller controller;

    @Override
    public void init() throws PortletException {
        if (applicationContext == null) {
            applicationContext = PortletApplicationContextUtils.getWebApplicationContext(getPortletContext());
        }
    }

    @Override
    public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException {
        include(request, response);
//        request.setAttribute("Attribute", "true");
//        response.setRenderParameter("RenderParameter", "true");

    }

    @Override
    public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        chooseAction(request, response);
//        String Attribute = (String) request.getAttribute("Attribute");
//        String RenderParameter = request.getParameter("RenderParameter");
    }

    private void chooseAction(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        include(request, response);

//        String sessionUri = (String)session.getAttribute("sessionUri", PortletSession.APPLICATION_SCOPE);
//        Map<String, String> errorMessageMap = (Map<String, String>)session.getAttribute("errorMessageMap", PortletSession.APPLICATION_SCOPE);
//
//        if (errorMessageMap != null && errorMessageMap.size() > 0) {
//            Department department = (Department) session.getAttribute("department", PortletSession.APPLICATION_SCOPE);
//            Employee employee = (Employee) session.getAttribute("employee", PortletSession.APPLICATION_SCOPE);
//            ArrayList<Department> departmentList = (ArrayList<Department>) session.getAttribute("departmentList", PortletSession.APPLICATION_SCOPE);
//            if(department != null) {
//                handlDepartmentValidException(request, errorMessageMap, department);
//            } else {
//                handlEmployeeValidException(request, errorMessageMap, employee, departmentList);
//            }
//            getPortletContext().getRequestDispatcher(sessionUri).include(request, response);
//            removeSession();
//        } else {
//            include(request, response, sessionUri);
//        }
    }



    private <T extends PortletRequest, E extends PortletResponse> void include(T request, E response) throws IOException, PortletException {
        try {
            String action = ParamUtil.getString(request, "uri");
            controller = (Controller) applicationContext.getBean(action);
        } catch (NullPointerException | BeansException e) {
            controller = (Controller) applicationContext.getBean("/controller/viewAllDepartment");
        }
        controller.execute(request, response, getPortletContext());
    }




//    private void handlDepartmentValidException(RenderRequest request, Map<String, String> errorMessageMap, Department department) {
//        request.setAttribute("errorMessageMap", errorMessageMap);
//        request.setAttribute("department", department);
//    }
//
//    private void handlEmployeeValidException(RenderRequest request, Map<String, String> errorMessageMap, Employee employee, ArrayList<Department> departmentList) {
//        request.setAttribute("errorMessageMap", errorMessageMap);
//        request.setAttribute("employee", employee);
//        request.setAttribute("departmentList", departmentList);
//    }
//
//    private void removeSession() {
//        session.removeAttribute("sessionUri", PortletSession.APPLICATION_SCOPE);
//        session.removeAttribute("errorMessageMap", PortletSession.APPLICATION_SCOPE);
//        session.removeAttribute("department", PortletSession.APPLICATION_SCOPE);
//        session.removeAttribute("employee", PortletSession.APPLICATION_SCOPE);
//        session.removeAttribute("departmentList", PortletSession.APPLICATION_SCOPE);
//    }


}


// TODO <aui:input type="text" name="industry" value="<%= industryFilter %>" />
