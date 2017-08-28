package ua.com.controllers.impl;

import com.liferay.portal.kernel.util.ParamUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;
import ua.com.controllers.Controller;

import javax.portlet.*;
import java.io.IOException;

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
    }


    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        PortletSession session = request.getPortletSession();
        String doView = (String)session.getAttribute("doView", PortletSession.APPLICATION_SCOPE);

        if (doView == null || "true".equals(doView)) {
            include(request, response);
        }

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


}

