package ua.com.controllers.impl;

import ua.com.controllers.Controller;
import ua.com.model.Department;
import ua.com.model.Employee;
import ua.com.services.DepartmentService;
import ua.com.services.EmployeeService;
import ua.com.services.impl.DepartmentServiceImpl;
import ua.com.services.impl.EmployeeServiceImpl;
import ua.com.utils.ParamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class ViewRegistrationEmployeeFormCommand implements Controller {

    private DepartmentService departmentService = new DepartmentServiceImpl();
    private EmployeeService employeeService = new EmployeeServiceImpl();

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String employeeIdStr = request.getParameter("employeeId");
        Long employeeId = ParamUtils.StringToLong(employeeIdStr);
        if (employeeId != null) {
            Employee employee = employeeService.getEmployeeById(employeeId);
            Department employeeDepartment = departmentService.getDepartmentById(employee.getDepartmentId());
            request.setAttribute("employee", employee);
            request.setAttribute("employeeDepartment", employeeDepartment);
        }
        List<Department> departmentList = departmentService.viewAllDepartment();
        request.setAttribute("departmentList", departmentList);
        request.getRequestDispatcher("/WEB-INF/jsp/saveEmployee.jsp").forward(request, response);
    }
}
