package ua.com.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.controllers.Controller;
import ua.com.exception.ValidFieldException;
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
import java.util.Date;

@Component(value = "/controller/saveEmployee")
public class SaveEmployeeCommand implements Controller {

    private static final Logger LOG = LoggerFactory.getLogger(SaveEmployeeCommand.class);

    private final EmployeeService employeeService;

    private final DepartmentService departmentService;

    @Autowired
    public SaveEmployeeCommand(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @Override
    public <T extends PortletRequest, E extends PortletResponse> void execute(T request, E response, PortletContext portletContext) throws IOException, PortletException {
        Employee employee = getEmployeeFromRequest(request);
        try {
            employeeService.saveEmployee(employee);
            ((ActionResponse)response).sendRedirect("/");
//            response.sendRedirect("viewDepartment?departmentId=" + employee.getDepartment().getId());
        } catch (ValidFieldException e) {
            LOG.debug("Not valid fields for save employee: {}", employee);
            request.setAttribute("errorMessageMap", e.getErrorsMap());
            request.setAttribute("employee", employee);
            request.setAttribute("departmentList", departmentService.viewAllDepartment());
            portletContext.getRequestDispatcher("/WEB-INF/jsp/saveEmployee.jsp").include(request, response);
        }
    }

    private <T extends PortletRequest> Employee getEmployeeFromRequest(T request) {
        Long employeeId = ParamUtils.StringToLong(request.getParameter("employeeId"));
        String employeeName = ParamUtils.verifyString(request.getParameter("employeeName"));
        String employeeSurname = ParamUtils.verifyString(request.getParameter("employeeSurname"));
        Date employeeHireDate = ParamUtils.verifyDate(request.getParameter("employeeHireDate"));
        String employeeEmail = ParamUtils.verifyString(request.getParameter("employeeEmail"));
        Integer employeeSalary = ParamUtils.StringToInteger(request.getParameter("employeeSalary"));
        Long employeeDepartmentId = ParamUtils.StringToLong(request.getParameter("employeeDepartmentId"));
        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setName(employeeName);
        employee.setSurname(employeeSurname);
        employee.setHireDate(employeeHireDate);
        employee.setEmail(employeeEmail);
        employee.setSalary(employeeSalary);
        Department department = new Department();
        department.setId(employeeDepartmentId);
        employee.setDepartment(department);
        return employee;
    }

}
