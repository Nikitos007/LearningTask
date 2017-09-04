package ua.com.controllers.impl;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import ua.com.model.Department;
import ua.com.model.Employee;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;

@org.springframework.stereotype.Controller(value = "MainPortlet")
@RequestMapping("VIEW")
public class MainPortlet {

    @RenderMapping
    public String handleRenderRequest(RenderRequest request, RenderResponse response, Model model) {
        return "defaultRender";
    }


    @RenderMapping(params = "action=department/viewDepartments")
    public String viewDepartments() {
        return "success";
    }

    @RenderMapping(params = "action=department/viewDepartmentSaveForm")
    public String viewDepartmentSaveForm() {
        return "success";
    }

    @ActionMapping(params = "action=department/saveDepartment")
    public void saveDepartment(@ModelAttribute("department") Department department, ActionRequest actionRequest, ActionResponse actionResponse, Model model) throws IOException {

        actionResponse.sendRedirect("/");
    }

    @ActionMapping(params = "action=department/deleteDepartment")
    public void deleteDepartment(@ModelAttribute("departmentId") Long departmentId, ActionRequest actionRequest, ActionResponse actionResponse, Model model) throws IOException {

        actionResponse.sendRedirect("/");
    }





    @RenderMapping(params = "action=employee/viewEmployees")
    public String viewEmployees() {
        return "success";
    }

    @RenderMapping(params = "action=employee/viewEmployeeSaveForm")
    public String viewEmployeeSaveForm() {
        return "success";
    }

    @ActionMapping(params = "action=employee/saveEmployee")
    public void saveEmployee(@ModelAttribute("employee") Employee employee, ActionRequest actionRequest, ActionResponse actionResponse, Model model) throws IOException {

        actionResponse.sendRedirect("/");
    }

    @ActionMapping(params = "action=employee/deleteEmployee")
    public void deleteEmployee(@ModelAttribute("employeeId") Long employeeId, ActionRequest actionRequest, ActionResponse actionResponse, Model model) throws IOException {

        actionResponse.sendRedirect("/");
    }





}
