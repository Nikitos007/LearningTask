'use strict';

import MainService from "./mainService";
import Validation from "./validation/validation";


export default class DepartmentService extends MainService {

    constructor() {
        super();
        this.validator = new Validation();
        this.departmentValidator = this.validator.departmentValidate;

        this.viewSaveForm = this.viewSaveForm.bind(this);
        this.save = this.save.bind(this);

    }

    viewAll() {
        let options = super.getAjaxOptions("GET", "department/viewAll", "");
        super.doAjax(options).then(drawAllDepartments).catch(error => console.error("Error: DepartmentService -> viewAll: " + error));

        function drawAllDepartments(result) {
            let mytable = $('<table></table>').attr({class: "table table-hover text-center"}).append($('<tbody></tbody>'));
            let thead = $('<thead></thead>');
            let rowHead = $('<tr></tr>');
            $('<td></td>').text('#').appendTo(rowHead);
            $('<td></td>').text('Department').appendTo(rowHead);
            $('<td></td>').text('Update').appendTo(rowHead);
            $('<td></td>').text('Delete').appendTo(rowHead);
            $('<td></td>').text('Employees').appendTo(rowHead);
            for (let i = 0; i < result.length; i++) {
                let row = $('<tr></tr>').appendTo(mytable);
                $('<td></td>').text(i).appendTo(row);
                $('<td></td>').text(result[i].departmentName).appendTo(row);
                $('<td></td>').attr("onclick", "department.controller.doAction('department/viewSaveForm',  " + JSON.stringify(result[i]) + "  )").text('Update').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
                $('<td></td>').attr("onclick", "department.controller.doAction('department/delete',  " + JSON.stringify(result[i]) + "  )").text('Delete').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
                $('<td></td>').attr("onclick", "department.controller.doAction('employee/viewEmployeesByDepartment',  " + JSON.stringify(result[i]) + "  )").text('Employees').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
            }
            rowHead.appendTo(thead);
            thead.appendTo(mytable);
            let div = $('<div></div>').attr({class: "col-lg-12 text-center"}).append(mytable);
            let wrapper = $('#wrapper');
            wrapper.empty();
            wrapper.append($('<h1>Departments</h1>').attr({class: "text-center"}));
            wrapper.append(div);
        }
    }

    viewSaveForm() {
        let departmentJson = arguments[0];
        let options = super.getAjaxOptions("POST", "department/viewSaveForm", departmentJson);
        super.doAjax(options).then(drawSaveForm).catch(error => console.error("Error: DepartmentService -> viewSaveForm: " + error));

        let validator = this.departmentValidator;
        let saveDepartment = this.save;


        function drawSaveForm(result) {
            let saveForm = $('<form></form>').attr({
                class: "form-horizontal col-lg-offset-2",
                id: "departmentSaveForm"
            });
            let hiddenDepartmentId = $('<input/>').attr({
                type: "hidden",
                id: "departmentId",
                value: result.departmentId
            });
            let inputDepartmentName = $('<div></div>').attr({class: "form-group"});
            inputDepartmentName.append($('<label></label>').attr({for: "departmentName"}).text("Name:"));
            inputDepartmentName.append($('<input/>').attr({
                type: "text",
                class: "form-control",
                id: "departmentName",
                name: "departmentName",
                value: result.departmentName
            }));
            inputDepartmentName.append($('<span></span>').attr({id: "errorDepartmentName", class: "text-danger"}));
            let saveButton = $('<div></div>').attr({class: "form-group"});
            saveButton.append($('<div></div>').attr({class: "col-lg-9 col-lg-offset-3"}).append($('<button></button>').attr({
                type: "submit",
                class: "btn btn-primary"
            }).text("Save")));
            // }).attr("onclick", "department.controller.doAction('department/save')").text("Save")));

            hiddenDepartmentId.appendTo(saveForm);
            inputDepartmentName.appendTo(saveForm);
            saveButton.appendTo(saveForm);
            let wrapper = $('#wrapper');
            wrapper.empty();
            wrapper.append($('<h1>Save department</h1>').attr({class: "text-center"}));
            wrapper.append(saveForm);

            $(() => {
                validator(() => {
                    return saveDepartment();
                }, 'departmentSaveForm')
            });
        }


    }


    save() {
        let options = super.getAjaxOptions("POST", "department/save", this.validator.getDepartmentToSave());
        super.doAjax(options).then(function (result) {
            department.controller.doAction('department/viewAll')
        }).catch(function (error) {
            console.error("Error: DepartmentService -> save: " + error);
            $('#wrapper').empty();
            $('#wrapper').append($('<h1></h1>').attr({class: "text-center"}).text("404 Not found"));
        });


        // let getAjaxOptions = super.getAjaxOptions;
        // let doAjax = super.doAjax;

        // let getDepartmentJson = function () {
        //     let departmentId = $('#departmentId').val();
        //     departmentId = (departmentId.trim() == "") ? undefined : departmentId;
        //     let departmentName = $('#departmentName').val();
        //     let departmentJson = {
        //         "departmentId": +departmentId,
        //         "departmentName": departmentName
        //     };
        //     return departmentJson;
        // };


        // let departmentJson = getDepartmentJson();


        // let saveValidDepartment = function () {
        //      let options = getAjaxOptions("POST", "department/save", departmentJson);
        //      doAjax(options).then(function (result) {
        //          department.controller.doAction('department/viewAll')
        //      }).catch(function (error) {
        //          console.error("Error: DepartmentService -> save: " + error);
        //          $('#wrapper').empty();
        //          $('#wrapper').append($('<h1></h1>').attr({class: "text-center"}).text("404 Not found"));
        //      });
        //  };

        // let validation = new Validation();
        // validation.departmentValidate(saveValidDepartment, departmentJson);







    }


    delete() {
        let departmentJson = arguments[0];
        let options = super.getAjaxOptions("POST", "department/delete", departmentJson);
        super.doAjax(options).then(function (result) {
            department.controller.doAction('department/viewAll')
        }).catch(error => console.error("Error: DepartmentService -> delete: " + error));
    }


}
