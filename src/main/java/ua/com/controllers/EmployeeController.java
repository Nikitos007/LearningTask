package ua.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.exception.ValidFieldException;
import ua.com.model.Department;
import ua.com.model.Employee;
import ua.com.services.DepartmentService;
import ua.com.services.EmployeeService;
import ua.com.wrappers.SaveEmployeeWrapper;

import java.util.List;

/**
 * Created on 10.08.17.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json")
    public void delete(@RequestBody Employee employee) {
        System.out.println();
        employeeService.deleteEmployee(employee);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Employee employee) throws ValidFieldException {
        employeeService.saveEmployee(employee);
        return "redirect:/employee/viewEmployee?departmentId=" + employee.getDepartment().getDepartmentId();
    }


    @ResponseBody
    @RequestMapping(value = "/viewEmployeesByDepartment", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<Employee> viewEmployee(@RequestBody Department department) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Employee> employeeList = employeeService.viewEmployeeByDepartmentId(department.getDepartmentId());
        return employeeList;
    }

    @RequestMapping(value = "/viewForm", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public SaveEmployeeWrapper viewForm(@RequestBody Employee employee) {
        SaveEmployeeWrapper saveEmployeeWrapper = new SaveEmployeeWrapper();
        Employee employeeDb = new Employee();
        if (employee.getEmployeeId() != null) {
            employeeDb = employeeService.getEmployeeById(employee.getEmployeeId());
        }
        saveEmployeeWrapper.setEmployee(employeeDb);
        List<Department> departmentList = departmentService.viewAllDepartment();
        saveEmployeeWrapper.setDepartmentList(departmentList);
        return saveEmployeeWrapper;


//        if (employeeRequest.getEmployeeId() != null) {
//            Employee employee = employeeService.getEmployeeById(employeeRequest.getEmployeeId());
//            model.addAttribute("employee", employee);
//        }
//        List<Department> departmentList = departmentService.viewAllDepartment();
//        model.addAttribute("departmentList", departmentList);
//        return "saveEmployee";

    }

}
