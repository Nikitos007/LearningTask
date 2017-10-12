package ua.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.exception.ValidFieldException;
import ua.com.model.Department;
import ua.com.services.DepartmentService;
import ua.com.utils.validation.ValidationUniqueDepartmentName;

import java.util.List;


@RestController
@RequestMapping({"/", "/department"})
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ResponseBody
    @RequestMapping(value = "/viewAll", method = RequestMethod.GET, produces = "application/json")
    public List<Department> viewAll() {
        List<Department> departmentList = departmentService.viewAllDepartment();
        return departmentList;
    }

    @ResponseBody
    @RequestMapping(value = "/viewSaveForm", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Department viewForm(@RequestBody Department department) {
        if (department.getDepartmentId() != null) {
            Department departmenDb = departmentService.getDepartmentById(department.getDepartmentId());
            return departmenDb;
        }
        return new Department();
    }

    @Autowired
    ValidationUniqueDepartmentName uniqueDepartmentName;

    @RequestMapping(value = "/uniqueName", method = RequestMethod.POST, consumes = "application/json")
    public Boolean checkUniqueName(@RequestBody Department department) {
        return uniqueDepartmentName.isSatisfied(department, department);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
    public Boolean save(@RequestBody Department department) {
        try {
            departmentService.saveDepartment(department);
            return true;
        } catch (ValidFieldException e) {
            return null;
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json")
    public boolean delete(@RequestBody Department department) {
        departmentService.delete(department);
        return true;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewIndexPage() {
        List<Department> departmentList = departmentService.viewAllDepartment();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("departmentList", departmentList);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
