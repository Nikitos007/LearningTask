'use strict';

import MainService from "./mainService";
import Validation from "./validation/employeeValidation";

let departmentJson;

export default class EmployeeService extends MainService {

    constructor() {
        super();
        this.validator = new Validation();
        this.employeeValidator = this.validator.validateEmployee;
        this.viewSaveForm = this.viewSaveForm.bind(this);
        this.save = this.save.bind(this);
    }

    viewEmployeesByDepartment() {
        departmentJson = arguments[0];
        let options = super.getAjaxOptions("POST", "employee/viewEmployeesByDepartment", departmentJson);
        super.doAjax(options).then(drawEmployeesByDepartment).catch(error => console.error("EmployeeService -> viewEmployeesByDepartment: " + error));

        function drawEmployeesByDepartment(result) {
            let mytable = $('<table></table>').attr({class: "table table-hover text-center"}).append($('<tbody></tbody>'));
            let thead = $('<thead></thead>');
            let rowHead = $('<tr></tr>');
            $('<td></td>').text('#').appendTo(rowHead);
            $('<td></td>').text('Name').appendTo(rowHead);
            $('<td></td>').text('Surname').appendTo(rowHead);
            $('<td></td>').text('Email').appendTo(rowHead);
            $('<td></td>').text('Hire date').appendTo(rowHead);
            $('<td></td>').text('Salary').appendTo(rowHead);
            $('<td></td>').text('Update').appendTo(rowHead);
            $('<td></td>').text('Delete').appendTo(rowHead);
            for (let i = 0; i < result.length; i++) {
                let row = $('<tr></tr>').appendTo(mytable);
                $('<td></td>').text(i).appendTo(row);
                $('<td></td>').text(result[i].name).appendTo(row);
                $('<td></td>').text(result[i].surname).appendTo(row);
                $('<td></td>').text(result[i].email).appendTo(row);
                $('<td></td>').text(result[i].hireDate).appendTo(row);
                $('<td></td>').text(result[i].salary).appendTo(row);
                $('<td></td>').attr("onclick", "department.controller.doAction('employee/viewSaveForm',  " + JSON.stringify(result[i]) + "  )").text('Update').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
                $('<td></td>').attr("onclick", "department.controller.doAction('employee/delete',  " + JSON.stringify(result[i]) + "  )").text('Delete').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
            }
            rowHead.appendTo(thead);
            thead.appendTo(mytable);
            let wrapper = $('#wrapper');
            wrapper.empty();
            wrapper.append($('<h1></h1>').attr({class: "text-center"}).text("Employees of " + departmentJson.departmentName));
            wrapper.append(mytable);
        }

    }

    viewSaveForm() {
        let employeeJson = arguments[0];
        let options = super.getAjaxOptions("POST", "employee/viewSaveForm", employeeJson);
        super.doAjax(options).then(drawSaveForm).catch(error => console.error("EmployeeService -> viewSaveForm: " + error));
        let validator = this.employeeValidator;
        let saveEmployee = this.save;
        const EMPLOYEE_SAVE_FORM = 'employeeSaveForm';

        function drawSaveForm(result) {
            let departmentJson = result.department;
            let saveForm = $('<form></form>').attr({class: "form-horizontal col-lg-offset-2", id: EMPLOYEE_SAVE_FORM});
            let employeeId = $('<input/>').attr({
                type: "hidden",
                id: "employeeId",
                value: result.employee.employeeId
            });
            let inputEmployeeName = $('<div></div>').attr({class: "form-group"});
            inputEmployeeName.append($('<label></label>').attr({for: "name"}).text("Name:"));
            inputEmployeeName.append($('<input/>').attr({
                type: "text",
                class: "form-control",
                id: "name",
                name: "name",
                value: result.employee.name
            }));
            let inputEmployeeSurname = $('<div></div>').attr({class: "form-group"});
            inputEmployeeSurname.append($('<label></label>').attr({for: "surname"}).text("Surname:"));
            inputEmployeeSurname.append($('<input/>').attr({
                type: "text",
                class: "form-control",
                id: "surname",
                name: "surname",
                value: result.employee.surname
            }));
            let inputEmployeeEmail = $('<div></div>').attr({class: "form-group"});
            inputEmployeeEmail.append($('<label></label>').attr({for: "email"}).text("Email:"));
            inputEmployeeEmail.append($('<input/>').attr({
                type: "text",
                class: "form-control",
                id: "email",
                name: "email",
                value: result.employee.email
            }));
            let inputEmployeeHireDate = $('<div></div>').attr({class: "form-group"});
            inputEmployeeHireDate.append($('<label></label>').attr({for: "hireDate"}).text("Hire date:"));
            inputEmployeeHireDate.append($('<input/>').attr({
                type: "date",
                class: "form-control",
                id: "hireDate",
                name: "hireDate",
                value: result.employee.hireDate
            }));
            let inputEmployeeSalary = $('<div></div>').attr({class: "form-group"});
            inputEmployeeSalary.append($('<label></label>').attr({for: "salary"}).text("Salary:"));
            inputEmployeeSalary.append($('<input/>').attr({
                type: "number",
                class: "form-control",
                id: "salary",
                name: "salary",
                value: result.employee.salary
            }));
            let selectDepartment = $('<div></div>').attr({class: "form-group"});
            selectDepartment.append($('<label></label>').attr({for: "selectDepartment"}).text("Department:"));
            let select = $('<select></select>').attr({class: "form-control", id: "departmentId"});
            for (let i = 0; i < result.departmentList.length; i++) {
                if (departmentJson != undefined && result.departmentList[i].departmentId == departmentJson.departmentId) {
                    $('<option></option>').attr('selected', 'selected').attr({
                        value: result.departmentList[i].departmentId
                    }).text(result.departmentList[i].departmentName).appendTo(select);
                } else {
                    $('<option></option>').attr({value: result.departmentList[i].departmentId}).text(result.departmentList[i].departmentName).appendTo(select);
                }
            }
            select.appendTo(selectDepartment);
            let saveButton = $('<div></div>').attr({class: "form-group"});
            saveButton.append($('<div></div>').attr({class: "col-lg-9 col-lg-offset-3"}).append($('<button></button>').attr({
                type: "submit",
                class: "btn btn-primary"
            }).text("Save")));

            employeeId.appendTo(saveForm);
            inputEmployeeName.appendTo(saveForm);
            inputEmployeeSurname.appendTo(saveForm);
            inputEmployeeEmail.appendTo(saveForm);
            inputEmployeeHireDate.appendTo(saveForm);
            inputEmployeeSalary.appendTo(saveForm);
            selectDepartment.appendTo(saveForm);
            saveButton.appendTo(saveForm);
            let wrapper = $('#wrapper');
            wrapper.empty();
            wrapper.append($('<h1>Save employee</h1>').attr({class: "text-center"}));
            wrapper.append(saveForm);

            $(() => {
                validator(() => {
                    return saveEmployee();
                }, EMPLOYEE_SAVE_FORM)
            });

        }

    }

    save() {
        departmentJson = this.validator.getDepartmentOfEmployee();
        let options = super.getAjaxOptions("POST", "employee/save", this.validator.getEmployeeToSave());
        super.doAjax(options).then(function (result) {
            department.controller.doAction("employee/viewEmployeesByDepartment", JSON.parse(departmentJson));
        }).catch(function (error) {
            console.error("EmployeeService -> save: " + error);
            let wrapper = $('#wrapper');
            wrapper.empty();
            wrapper.append($('<h1></h1>').attr({class: "text-center"}).text("404 Not found"));
        });
    }

    delete() {
        let employeeJson = arguments[0];
        let options = super.getAjaxOptions("POST", "employee/delete", employeeJson);
        super.doAjax(options).then(function (result) {
            department.controller.doAction("employee/viewEmployeesByDepartment", departmentJson);
        }).catch(error => console.error("EmployeeService -> delete: " + error));
    }
}
