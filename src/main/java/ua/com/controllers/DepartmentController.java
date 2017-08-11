package ua.com.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    private static final Logger LOG = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/delete")
    public String delete(Department department) {
        departmentService.delete(department);
        return "redirect:/";
    }

    @RequestMapping("/save")
    public ModelAndView save(Department department) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            departmentService.saveDepartment(department);
            modelAndView.setViewName("redirect:/");
        } catch (ValidFieldException e) {
            LOG.debug("Not valid fields for save department: {}", department);
            modelAndView.addObject("errorMessageMap", e.getErrorsMap());
            modelAndView.setViewName("saveDepartment");
        }
        return modelAndView;
    }

    @RequestMapping
    public ModelAndView viewAll() {
        ModelAndView modelAndView = new ModelAndView();
        List<Department> departmentList = departmentService.viewAllDepartment();
        modelAndView.addObject("departmentList", departmentList);
        modelAndView.setViewName("viewAllDepartment");
        return modelAndView;
    }

    @RequestMapping("/viewForm")
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
