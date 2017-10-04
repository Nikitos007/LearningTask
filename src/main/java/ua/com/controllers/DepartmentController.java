package ua.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.exception.ValidFieldException;
import ua.com.model.Department;
import ua.com.services.DepartmentService;
import ua.com.wrappers.ValidateWrapper;

import java.util.List;


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


//    @RequestMapping(value = "/validate", method = RequestMethod.POST, consumes ="application/json", produces = "application/json")
//    public ValidateWrapper validate(@RequestBody Department department) {
//        try {
//            departmentService.validateByOval(department);
//            return null;
//        } catch (ValidFieldException e) {
//            return new ValidateWrapper(e.getErrorsMap());
//        }
//    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
    public ValidateWrapper save(@RequestBody Department department) {
        try {
            departmentService.saveDepartment(department);
            return new ValidateWrapper();
        } catch (ValidFieldException e) {
            return new ValidateWrapper(e.getErrorsMap());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/viewAll", method = RequestMethod.GET, produces = "application/json")
    public List<Department> viewAll() {
//        try {
//            Thread.sleep(300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
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

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewIndexPage() {
        List<Department> departmentList = departmentService.viewAllDepartment();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("departmentList", departmentList);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
