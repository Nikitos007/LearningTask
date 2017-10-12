package ua.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.exception.ValidFieldException;
import ua.com.model.Department;
import ua.com.model.Employee;
import ua.com.services.DepartmentService;
import ua.com.services.EmployeeService;
import ua.com.utils.validation.ValidationUniqueEmployeeEmail;
import ua.com.wrappers.SaveEmployeeWrapper;
import ua.com.wrappers.ViewSaveEmployeeFormWrapper;

import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    private ValidationUniqueEmployeeEmail uniqueEmployeeEmail;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @ResponseBody
    @RequestMapping(value = "/viewEmployeesByDepartment", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<Employee> viewEmployee(@RequestBody Department department) {
        List<Employee> employeeList = employeeService.viewEmployeeByDepartmentId(department.getDepartmentId());
        return employeeList;
    }

    @RequestMapping(value = "/uniqueEmail", method = RequestMethod.POST, consumes = "application/json")
    public Boolean checkUniqueName(@RequestBody Employee employee) {
        boolean isInique = uniqueEmployeeEmail.isSatisfied(employee, employee);
        return isInique;
    }

    @RequestMapping(value = "/viewSaveForm", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ViewSaveEmployeeFormWrapper viewForm(@RequestBody Employee employee) {
        ViewSaveEmployeeFormWrapper viewSaveEmployeeFormWrapper = new ViewSaveEmployeeFormWrapper();
        Employee employeeDb = new Employee();
        if (employee.getEmployeeId() != null) {
            employeeDb = employeeService.getEmployeeById(employee.getEmployeeId());
        }
        viewSaveEmployeeFormWrapper.setEmployee(employeeDb);
        viewSaveEmployeeFormWrapper.setDepartment(employeeDb.getDepartment());
        List<Department> departmentList = departmentService.viewAllDepartment();
        viewSaveEmployeeFormWrapper.setDepartmentList(departmentList);
        return viewSaveEmployeeFormWrapper;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Boolean save(@RequestBody SaveEmployeeWrapper saveEmployeeWrapper) {
        Department department = new Department();
        department.setDepartmentId(saveEmployeeWrapper.getDepartmentId());
        Employee employee = saveEmployeeWrapper.getEmployee();
        employee.setDepartment(department);
        try {
            employeeService.saveEmployee(employee);
            return true;
        } catch (ValidFieldException e) {
            return null;
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public boolean delete(@RequestBody Employee employee) {
        employeeService.deleteEmployee(employee);
        return true;
    }

}
