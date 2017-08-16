package ua.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Employee employee, @RequestParam(value = "departmentId") Long departmentId) {
        employeeService.deleteEmployee(employee);
        return "redirect:/employee/viewEmployee?departmentId=" + departmentId;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Employee employee) throws ValidFieldException {
        employeeService.saveEmployee(employee);
        return "redirect:/employee/viewEmployee?departmentId=" + employee.getDepartment().getDepartmentId();
    }

    @RequestMapping(value = "/viewEmployee", method = RequestMethod.GET)
    public String viewEmployee(Model model, @RequestParam(value = "departmentId") Long departmentId) {
        model.addAttribute("employeeList", employeeService.viewEmployeeByDepartmentId(departmentId));
        return "employeeByDepartment";
    }

    @RequestMapping(value = "/viewForm", method = RequestMethod.GET)
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
