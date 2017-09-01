package ua.com.controllers.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;
import ua.com.controllers.Controller;

import javax.portlet.*;
import java.io.IOException;

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
        Long departmentId = (Long) getPortletContext().getAttribute("departmentId");
        getPortletContext().removeAttribute("departmentId");
        request.setAttribute("departmentId", departmentId);

        String jspView = (String) request.getAttribute("jspView");
        if (jspView == null) {
            include(request, response);
        }
        jspView = (String) request.getAttribute("jspView");
        getPortletContext().getRequestDispatcher(jspView).include(request, response);
    }

    private <T extends PortletRequest> String getAction(T request) {
        String action = request.getParameter("uri");
        if (action == null) {
            action = (String) request.getAttribute("uri");
        }
        if (action == null) {
            action = (String) getPortletContext().getAttribute("uri");
            getPortletContext().removeAttribute("uri");
        }
        return action;
    }

    private <T extends PortletRequest, E extends PortletResponse> void include(T request, E response) throws IOException, PortletException {
        try {
            String action = getAction(request);
            controller = (Controller) applicationContext.getBean(action);
        } catch (IllegalArgumentException | BeansException e) {
            controller = (Controller) applicationContext.getBean("/controller/viewAllDepartment");
        }
        controller.execute(request, response, getPortletContext());
    }

}
