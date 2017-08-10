package ua.com.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.controllers.Controller;
import ua.com.model.Department;
import ua.com.model.Employee;
import ua.com.services.DepartmentService;
import ua.com.services.EmployeeService;
import ua.com.utils.ParamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Component(value = "/controller/viewRegistrationEmployeeForm")
public class ViewRegistrationEmployeeFormCommand implements Controller {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        String employeeIdStr = request.getParameter("employeeId");
        Long employeeId = ParamUtils.StringToLong(employeeIdStr);
        if (employeeId != null) {
            Employee employee = employeeService.getEmployeeById(employeeId);
            request.setAttribute("employee", employee);
        }
        List<Department> departmentList = departmentService.viewAllDepartment();
        request.setAttribute("departmentList", departmentList);
        request.getRequestDispatcher("/WEB-INF/jsp/saveEmployee.jsp").forward(request, response);
    }
}
