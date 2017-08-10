package ua.com.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.controllers.Controller;
import ua.com.exception.ValidFieldException;
import ua.com.model.Department;
import ua.com.services.DepartmentService;
import ua.com.utils.ParamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Component(value = "/controller/saveDepartment")
public class SaveDepartmentCommand implements Controller {

    private static final Logger LOG = LoggerFactory.getLogger(SaveDepartmentCommand.class);
    @Autowired
    private DepartmentService departmentService;


    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        Department department = getDepartmentFromRequest(request);
        try {
            departmentService.saveDepartment(department);
            response.sendRedirect("viewAllDepartment");
        } catch (ValidFieldException e) {
            LOG.debug("Not valid fields for save department: {}", department);
            request.setAttribute("errorMessageMap", e.getErrorsMap());
            request.getRequestDispatcher("/WEB-INF/jsp/saveDepartment.jsp").forward(request, response);
        }
    }

    private Department getDepartmentFromRequest(HttpServletRequest request) {
        String departmentIdStr = request.getParameter("departmentId");
        String departmentName = request.getParameter("departmentName");
        Department department = new Department();
        department.setId(ParamUtils.StringToLong(departmentIdStr));
        department.setName(ParamUtils.verifyString(departmentName));
        return department;
    }
}
