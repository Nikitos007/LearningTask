package ua.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.exception.ValidFieldException;
import ua.com.model.Department;
import ua.com.services.DepartmentService;

import java.util.List;

/**
 * Created on 10.08.17.
 */
@RestController
@RequestMapping({"/", "/department"})
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json")
    public void delete(@RequestBody Department department) {
        departmentService.delete(department);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
    public void save(@RequestBody Department department) {
        try {
            if (department.getDepartmentId() == 0) {
                department.setDepartmentId(null);
            }
            departmentService.saveDepartment(department);
        } catch (ValidFieldException e) {
            //TODO validation on backend
        }

    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewIndexPage() {
        List<Department> departmentList = departmentService.viewAllDepartment();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("departmentList", departmentList);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/viewAll", method = RequestMethod.GET, produces = "application/json")
    public List<Department> viewAll() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Department> departmentList = departmentService.viewAllDepartment();
        return departmentList;
    }

    @ResponseBody
    @RequestMapping(value = "/viewForm", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Department viewForm(@RequestBody Department department) {
        if (department.getDepartmentId() != null) {
            Department departmenDb = departmentService.getDepartmentById(department.getDepartmentId());
            return departmenDb;
        }

        return new Department();
    }
}
