package ua.com.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * Created on 12.07.17.
 */


@Component
public class Controller implements HttpRequestHandler {

    private final Map<String, ua.com.controllers.Controller> commands;

    @Autowired
    public Controller(Map<String, ua.com.controllers.Controller> commands) {
        this.commands = commands;
    }

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        getCommand(uri).execute(request, response);
    }

    private ua.com.controllers.Controller getCommand(String commandName) {
        if (commands.containsKey(commandName)) {
            return commands.get(commandName);
        }
        return commands.get("/controller/viewAllDepartment");
    }


}
