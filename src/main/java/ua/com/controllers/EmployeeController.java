package ua.com.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.exception.ValidFieldException;
import ua.com.model.Department;
import ua.com.model.Employee;
import ua.com.services.DepartmentService;
import ua.com.services.EmployeeService;

import java.util.List;

/**
 * Created on 10.08.17.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @RequestMapping("/delete")
    public String delete(Employee employee, @RequestParam(value = "departmentId") Long departmentId) {
        employeeService.deleteEmployee(employee);
        return "redirect:/employee/viewEmployee?departmentId=" + departmentId;
    }

    @RequestMapping("/save")
    public String save(Model model, Employee employee, @RequestParam(value = "departmentId") Long departmentId) throws ValidFieldException {
        Department department = new Department();
        department.setDepartmentId(departmentId);
        employee.setDepartment(department);
        employeeService.saveEmployee(employee);
        return "redirect:/employee/viewEmployee?departmentId=" + employee.getDepartment().getDepartmentId();
    }

    @RequestMapping("/viewEmployee")
    public String viewEmployee(Model model, @RequestParam(value = "departmentId") Long departmentId) {
        model.addAttribute("employeeList", employeeService.viewEmployeeByDepartmentId(departmentId));
        return "employeeByDepartment";
    }

    @RequestMapping("/viewForm")
    public String viewForm(Model model, Employee employeeRequest) {
        if (employeeRequest.getEmployeeId() != null) {
            Employee employee = employeeService.getEmployeeById(employeeRequest.getEmployeeId());
            model.addAttribute("employee", employee);
        }
        List<Department> departmentList = departmentService.viewAllDepartment();
        model.addAttribute("departmentList", departmentList);
        return "saveEmployee";
    }

}
