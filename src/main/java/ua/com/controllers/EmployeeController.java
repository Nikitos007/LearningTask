package ua.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.exception.ValidFieldException;
import ua.com.model.Department;
import ua.com.model.Employee;
import ua.com.services.DepartmentService;
import ua.com.services.EmployeeService;
import ua.com.wrappers.SaveEmployeeWrapper;
import ua.com.wrappers.ValidateWrapper;
import ua.com.wrappers.ViewSaveEmployeeFormWrapper;

import java.util.List;


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
    public boolean delete(@RequestBody Employee employee) {
        employeeService.deleteEmployee(employee);
        return true;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ValidateWrapper save(@RequestBody SaveEmployeeWrapper saveEmployeeWrapper) {
        Department department = new Department();
        department.setDepartmentId(saveEmployeeWrapper.getDepartmentId());
        Employee employee = saveEmployeeWrapper.getEmployee();
        employee.setDepartment(department);
        try {
            employeeService.saveEmployee(employee);
            return new ValidateWrapper();
        } catch (ValidFieldException e) {
            return new ValidateWrapper(e.getErrorsMap());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/viewEmployeesByDepartment", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<Employee> viewEmployee(@RequestBody Department department) {
        List<Employee> employeeList = employeeService.viewEmployeeByDepartmentId(department.getDepartmentId());
        return employeeList;
    }

    @RequestMapping(value = "/viewForm", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
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

}
