package ua.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.exception.ValidFieldException;
import ua.com.model.Department;
import ua.com.services.DepartmentService;

import java.util.List;

/**
 * Created on 10.08.17.
 */
@Controller
@RequestMapping({"/", "/department"})
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Department department) {
        departmentService.delete(department);
        return "redirect:/";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Department department) throws ValidFieldException {
        departmentService.saveDepartment(department);
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewAll() {
        List<Department> departmentList = departmentService.viewAllDepartment();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("departmentList", departmentList);
        modelAndView.setViewName("viewAllDepartment");
        return modelAndView;
    }

    @RequestMapping(value = "/viewForm", method = RequestMethod.GET)
    public ModelAndView viewForm(Department department) {
        ModelAndView modelAndView = new ModelAndView();
        if (department.getDepartmentId() != null) {
            Department departmenDb = departmentService.getDepartmentById(department.getDepartmentId());
            modelAndView.addObject("department", departmenDb);
        }
        modelAndView.setViewName("saveDepartment");
        return modelAndView;
    }
}
