package ua.com.controllers.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;
import ua.com.controllers.Controller;
import ua.com.model.Department;
import ua.com.model.Employee;

import javax.portlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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
    }

    @Override
    public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        String validException = (String) getPortletContext().getAttribute("ValidFieldException");
        removeAttributeFromContext("ValidFieldException");
        request.setAttribute("departmentId", getPortletContext().getAttribute("departmentId"));
        removeAttributeFromContext("departmentId");
        if (validException != null) {
            handleValidException(request, response, validException);
            return;
        }
        include(request, response);
    }

    private void handleValidException(RenderRequest request, RenderResponse response, String validException) throws PortletException, IOException {
        switch (validException) {
            case "department": {
                String uri = (String) getPortletContext().getAttribute("uri");
                Map<String, String> errorMessageMap = (Map<String, String>) getPortletContext().getAttribute("errorMessageMap");
                Department department = (Department) getPortletContext().getAttribute("department");
                removeAttributeFromContext("uri");
                removeAttributeFromContext("errorMessageMap");
                removeAttributeFromContext("department");
                request.setAttribute("errorMessageMap", errorMessageMap);
                request.setAttribute("department", department);
                getPortletContext().getRequestDispatcher(uri).include(request, response);
                break;
            }
            case "employee": {
                String uri = (String) getPortletContext().getAttribute("uri");
                Map<String, String> errorMessageMap = (Map<String, String>) getPortletContext().getAttribute("errorMessageMap");
                Employee employee = (Employee) getPortletContext().getAttribute("employee");
                ArrayList<Department> departmentList = (ArrayList<Department>) getPortletContext().getAttribute("departmentList");
                removeAttributeFromContext("uri");
                removeAttributeFromContext("errorMessageMap");
                removeAttributeFromContext("employee");
                removeAttributeFromContext("departmentList");
                request.setAttribute("errorMessageMap", errorMessageMap);
                request.setAttribute("employee", employee);
                request.setAttribute("departmentList", departmentList);
                getPortletContext().getRequestDispatcher(uri).include(request, response);
                break;
            }
        }
    }

    private <T extends PortletRequest, E extends PortletResponse> void include(T request, E response) throws IOException, PortletException {

        PortletSession portletSession = request.getPortletSession();

        try {
            String action = request.getParameter("uri");
            if (action == null) {
                action = (String) getPortletContext().getAttribute("uri");
                removeAttributeFromContext("uri");
            }
            controller = (Controller) applicationContext.getBean(action);
        } catch (IllegalArgumentException | BeansException e) {
            controller = (Controller) applicationContext.getBean("/controller/viewAllDepartment");
        }
        controller.execute(request, response, getPortletContext());
    }

    private void removeAttributeFromContext(String name) {
        getPortletContext().removeAttribute(name);
    }
}
