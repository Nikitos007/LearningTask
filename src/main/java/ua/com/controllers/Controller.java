package ua.com.controllers;

import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.io.IOException;
import java.io.Serializable;


public interface Controller extends Serializable {

    <T extends PortletRequest, E extends PortletResponse> void execute(T request, E response, PortletContext portletContext) throws IOException, PortletException;

}

