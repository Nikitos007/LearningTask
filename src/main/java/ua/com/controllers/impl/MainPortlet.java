package ua.com.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import ua.com.exception.ValidFieldException;
import ua.com.model.Department;
import ua.com.model.Employee;
import ua.com.services.DepartmentService;
import ua.com.services.EmployeeService;
import ua.com.utils.ParamUtils;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.List;

@org.springframework.stereotype.Controller(value = "MainPortlet")
@RequestMapping("VIEW")
public class MainPortlet {

    private static final Logger LOG = LoggerFactory.getLogger(MainPortlet.class);

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @RenderMapping
    public String viewDepartments(RenderRequest request, RenderResponse response, Model model) {
        List<Department> departmentList = departmentService.viewAllDepartment();
        request.setAttribute("departmentList", departmentList);
        return "viewDepartments";
    }

    @RenderMapping(params = "action=department/viewDepartmentSaveForm")
    public String viewDepartmentSaveForm(RenderRequest request, RenderResponse response, Model model) {
        String departmentIdStr = request.getParameter("departmentId");
        Long departmentId = ParamUtils.StringToLong(departmentIdStr);
        if (departmentId != null) {
            Department department = departmentService.getDepartmentById(departmentId);
            department.setName(department.getName());
            request.setAttribute("department", department);
        }
        return "saveDepartmentForm";
    }

    @ActionMapping(params = "action=department/saveDepartment")
    public void saveDepartment(@ModelAttribute("department") Department department, ActionRequest request, ActionResponse response) throws IOException {
        try {
            departmentService.saveDepartment(department);
            request.removeAttribute("action");
            response.sendRedirect("/");
        } catch (ValidFieldException e) {
            LOG.debug("Not valid fields for save department: {}", department);
            request.setAttribute("errorMessageMap", e.getErrorsMap());
            request.setAttribute("department", department);
            response.setRenderParameter("action", "department/viewDepartmentSaveForm");
        }
    }

    @ActionMapping(params = "action=department/deleteDepartment")
    public void deleteDepartment(@RequestParam("departmentId") Long departmentId, ActionRequest request, ActionResponse response, Model model) throws IOException {
        Department department = new Department();
        department.setId(departmentId);
        departmentService.delete(department);
        response.sendRedirect("/");
    }

    @RenderMapping(params = "action=employee/viewEmployees")
    public String viewEmployees(RenderRequest request, RenderResponse response, Model model) {
        Long departmentId = ParamUtils.StringToLong(String.valueOf(request.getAttribute("departmentId")));
        if (departmentId == null) {
            String departmentIdStr = request.getParameter("departmentId");
            departmentId = Long.parseLong(departmentIdStr);
        }
        request.setAttribute("employeeList", employeeService.viewEmployeeByDepartmentId(departmentId));
        return "viewEmployees";
    }

    @RenderMapping(params = "action=employee/viewEmployeeSaveForm")
    public String viewEmployeeSaveForm(RenderRequest request, RenderResponse response, Model model) {
        Long employeeId = ParamUtils.StringToLong(request.getParameter("employeeId"));
        if (employeeId != null) {
            Employee employee = employeeService.getEmployeeById(employeeId);
            request.setAttribute("employee", employee);
        }
        List<Department> departmentList = departmentService.viewAllDepartment();
        request.setAttribute("departmentList", departmentList);
        return "saveEmployeeForm";
    }

    @ActionMapping(params = "action=employee/deleteEmployee")
    public void deleteEmployee(@RequestParam("employeeId") Long employeeId, ActionRequest request, ActionResponse response) throws IOException {
        String departmentIdStr = request.getParameter("departmentId");
        Long departmentId = ParamUtils.StringToLong(departmentIdStr);
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee != null) {
            employeeService.deleteEmployee(employee);
        }
        request.setAttribute("departmentId", departmentId);
        response.setRenderParameter("action", "employee/viewEmployees");
//        response.sendRedirect("/");
    }


    @ActionMapping(params = "action=employee/saveEmployee")
    public void saveEmployee(@ModelAttribute("employee") Employee employee, ActionRequest request, ActionResponse response) throws IOException {
        try {
            employeeService.saveEmployee(employee);
            request.setAttribute("departmentId", employee.getDepartment().getId());
            response.setRenderParameter("action", "employee/viewEmployees");
//            response.sendRedirect("/");
        } catch (ValidFieldException e) {
            LOG.debug("Not valid fields for save employee: {}", employee);
            request.setAttribute("errorMessageMap", e.getErrorsMap());
            request.setAttribute("employee", employee);
            request.setAttribute("departmentList", departmentService.viewAllDepartment());
            request.setAttribute("departmentId", employee.getDepartment().getId());
            response.setRenderParameter("action", "employee/viewEmployeeSaveForm");
        }
    }

}
