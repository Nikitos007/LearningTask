package ua.com.utils;

import javax.portlet.*;
import java.io.IOException;
import java.util.Map;

/**
 * Created on 29.08.17.
 */
public class RedirectUtil {

    public static void redirect(PortletRequest request, ActionResponse response, String sessionUri) throws IOException {
        PortletSession session = request.getPortletSession();
        session.setAttribute("sessionUri", sessionUri, PortletSession.APPLICATION_SCOPE);
        response.sendRedirect("/");
    }

}
