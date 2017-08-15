package ua.com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ua.com.model.Department;
import ua.com.model.Employee;
import ua.com.services.DepartmentService;

/**
 * Created on 14.08.17.
 */
@ControllerAdvice
public class GlobalExceptionHandlerController {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

    @Autowired
    private DepartmentService departmentService;

    @ExceptionHandler(ValidFieldException.class)
    public ModelAndView handleValidFieldException(ValidFieldException e) {
        Object obj = e.getObject();
        ModelAndView modelAndView = new ModelAndView("error");
        if (obj instanceof Department) {
            Department department = (Department) obj;
            LOG.debug("Not valid fields for save department: {}", department);
            modelAndView.addObject("errorMessageMap", e.getErrorsMap());
            modelAndView.setViewName("saveDepartment");
            return modelAndView;
        }
        if (obj instanceof Employee) {
            Employee employee = (Employee) obj;
            LOG.debug("Not valid fields for save employee");
            modelAndView.addObject("errorMessageMap", e.getErrorsMap());
            modelAndView.addObject("employee", employee);
            modelAndView.addObject("departmentList", departmentService.viewAllDepartment());
            modelAndView.setViewName("saveEmployee");
            return modelAndView;
        }
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleInternalServerError(Exception ex) {
        LOG.error(ex.getMessage());
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }


}