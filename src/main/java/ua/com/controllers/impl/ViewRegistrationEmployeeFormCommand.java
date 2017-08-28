package ua.com.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.controllers.Controller;
import ua.com.model.Department;
import ua.com.model.Employee;
import ua.com.services.DepartmentService;
import ua.com.services.EmployeeService;
import ua.com.utils.ParamUtils;

import javax.portlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component(value = "/controller/viewRegistrationEmployeeForm")
public class ViewRegistrationEmployeeFormCommand implements Controller {

    private final DepartmentService departmentService;

    private final EmployeeService employeeService;

    @Autowired
    public ViewRegistrationEmployeeFormCommand(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @Override
    public <T extends PortletRequest, E extends PortletResponse> void execute(T request, E response, PortletContext portletContext) throws IOException, PortletException {
        String employeeIdStr = request.getParameter("employeeId");
        Long employeeId = ParamUtils.StringToLong(employeeIdStr);
        if (employeeId != null) {
            Employee employee = employeeService.getEmployeeById(employeeId);
            request.setAttribute("employee", employee);
        }
        List<Department> departmentList = departmentService.viewAllDepartment();
        request.setAttribute("departmentList", departmentList);
        PortletRequestDispatcher requestDispatcher = portletContext.getRequestDispatcher("/WEB-INF/jsp/saveEmployee.jsp");
        requestDispatcher.include(request, response);
    }
}
