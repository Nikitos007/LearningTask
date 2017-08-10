package ua.com.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;


/**
 * Created on 12.07.17.
 */

@Component
public class Controller implements HttpRequestHandler {

    private static final Logger LOG = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private Map<String, ua.com.controllers.Controller> commands;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        try {
            getCommand(uri).execute(request, response);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            response.sendRedirect("/controller/viewAllDepartment");
        }
    }

    public ua.com.controllers.Controller getCommand(String commandName) {
        if (commands.containsKey(commandName)) {
            return commands.get(commandName);
        }
        return commands.get("/controller/viewAllDepartment");
    }
}
