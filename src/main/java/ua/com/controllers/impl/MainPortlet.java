package ua.com.controllers.impl;

import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ParamUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;
import ua.com.controllers.Controller;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
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
//        controller = (Controller) applicationContext.getBean("/controller/viewAllDepartment");
    }

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        String action = ParamUtil.getString(request, "uri");
        try {
            controller = (Controller) applicationContext.getBean(action);
        } catch (BeansException e) {
            controller = (Controller) applicationContext.getBean("/controller/viewAllDepartment");
        }
        controller.execute(request, response, getPortletContext());
    }

}

