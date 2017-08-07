package ua.com.controllers.impl;

import org.apache.log4j.Logger;
import ua.com.controllers.Dispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Created on 12.07.17.
 */
@WebServlet("/")
public class Controller extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Controller.class);
    Dispatcher dispatcher = new Dispatcher();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        try {
            dispatcher.get(uri).execute(request, response);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            response.sendRedirect("/controller/viewAllDepartment");
        }
    }
}
