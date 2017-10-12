class MainService {

    doAjax(options) {
        return new Promise(function (resolve, reject) {
            $.ajax(options)
                .done(resolve)
                .fail(reject);
        });
    }

    getAjaxOptions(typeReq, urlReq, dataReq) {
        return {
            type: typeReq,
            url: urlReq,
            data: JSON.stringify(dataReq),
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }
    }

}


class DepartmentService extends MainService {
    viewAll() {
        var options = super.getAjaxOptions("GET", "department/viewAll", "");
        super.doAjax(options).then(drawAllDepartments).catch(error => console.error("Error: DepartmentService -> viewAll: " + error)
    )
        ;

        function drawAllDepartments(result) {
            var mytable = $('<table></table>').attr({class: "table table-hover text-center"}).append($('<tbody></tbody>'));
            var rows = result.length;
            var thead = $('<thead></thead>');
            var rowHead = $('<tr></tr>');
            $('<td></td>').text('#').appendTo(rowHead);
            $('<td></td>').text('Department').appendTo(rowHead);
            $('<td></td>').text('Update').appendTo(rowHead);
            $('<td></td>').text('Delete').appendTo(rowHead);
            $('<td></td>').text('Employees').appendTo(rowHead);
            for (var i = 0; i < rows; i++) {
                var row = $('<tr></tr>').appendTo(mytable);
                $('<td></td>').text(i).appendTo(row);
                $('<td></td>').text(result[i].departmentName).appendTo(row);
                $('<td></td>').attr("onclick", "controller.doAction('department/viewSaveForm',  " + JSON.stringify(result[i]) + "  )").text('Update').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
                $('<td></td>').attr("onclick", "controller.doAction('department/delete',  " + JSON.stringify(result[i]) + "  )").text('Delete').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
                $('<td></td>').attr("onclick", "controller.doAction('employee/viewEmployeesByDepartment',  " + JSON.stringify(result[i]) + "  )").text('Employees').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
            }
            rowHead.appendTo(thead);
            thead.appendTo(mytable);
            var div = $('<div></div>').attr({class: "col-lg-12 text-center"}).append(mytable);
            $('#wrapper').empty();
            $('#wrapper').append($('<h1>Departments</h1>').attr({class: "text-center"}));
            $('#wrapper').append(div);
        }
    }

    viewSaveForm() {

        var departmentJson = arguments[0];
        if (departmentJson == undefined) {
            departmentJson = {
                "departmentId": null,
                "departmentName": null
            };
        }
        var options = super.getAjaxOptions("POST", "department/viewSaveForm", departmentJson);
        super.doAjax(options).then(drawSaveForm).catch(error => console.error("Error: DepartmentService -> viewSaveForm: " + error)
    )
        ;

        function drawSaveForm(result) {
            var saveForm = $('<form></form>').attr({
                class: "form-horizontal col-lg-offset-2",
                id: "departmentSaveForm"
            });
            var hiddenDepartmentId = $('<input/>').attr({
                type: "hidden",
                id: "departmentId",
                value: result.departmentId
            });
            var inputDepartmentName = $('<div></div>').attr({class: "form-group"});
            inputDepartmentName.append($('<label></label>').attr({for: "departmentName"}).text("Name:"));
            inputDepartmentName.append($('<input/>').attr({
                type: "text",
                class: "form-control",
                id: "departmentName",
                name: "departmentName",
                value: result.departmentName
            }));
            inputDepartmentName.append($('<span></span>').attr({id: "errorDepartmentName", class: "text-danger"}));
            var saveButton = $('<div></div>').attr({class: "form-group"});
            saveButton.append($('<div></div>').attr({class: "col-lg-9 col-lg-offset-3"}).append($('<button></button>').attr({
                type: "submit",
                class: "btn btn-primary"
            }).attr("onclick", "controller.doAction('department/save')").text("Save")));

            hiddenDepartmentId.appendTo(saveForm);
            inputDepartmentName.appendTo(saveForm);
            saveButton.appendTo(saveForm);

            $('#wrapper').empty();
            $('#wrapper').append($('<h1>Save department</h1>').attr({class: "text-center"}));
            $('#wrapper').append(saveForm);

            $(function () {
                controller.doAction("department/save");
            })

        }

    }

    save() {
        var getAjaxOptions = super.getAjaxOptions;
        var doAjax = super.doAjax;

        var getDepartmentJson = function () {
            let departmentId = $('#departmentId').val();
            departmentId = (departmentId.trim() == "") ? undefined : departmentId;
            let departmentName = $('#departmentName').val();
            let departmentJson = {
                "departmentId": +departmentId,
                "departmentName": departmentName
            };
            return departmentJson;
        }


        $.validator.addMethod("onlyChars", function (value, element) {
                return /^[a-zA-Z\s]+$/.test(value);
            },
            "Please don't insert numbers and specified chars");

        $.validator.addMethod("checkUniqueName", function (value, element) {
            let isUniqueName;
            $.ajax({
                async: false,
                type: "POST",
                url: "department/uniqueName",
                data: JSON.stringify(getDepartmentJson()),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function (result) {
                    isUniqueName = result;
                }
            });
            return isUniqueName;
        }, "This name has already exist");


        $('#departmentSaveForm').validate({
            rules: {
                departmentName: {
                    required: true,
                    minlength: 3,
                    maxlength: 100,
                    onlyChars: true,
                    checkUniqueName: true
                }
            },
            messages: {
                departmentName: {
                    required: "Please enter a department's name",
                    minlength: "Min length is 3 letters",
                    maxlength: "Max length is 100 letters"
                }
            },
            submitHandler: function () {
                let departmentJson = getDepartmentJson();
                let options = getAjaxOptions("POST", "department/save", departmentJson);
                doAjax(options).then(function (result) {
                    controller.doAction('department/viewAll')
                }).catch(function (error) {
                    console.error("Error: DepartmentService -> save: " + error);
                    $('#wrapper').empty();
                    $('#wrapper').append($('<h1></h1>').attr({class: "text-center"}).text("404 Not found"));
                });
            }
        });

    }


    delete() {
        var departmentJson = arguments[0];
        var options = super.getAjaxOptions("POST", "department/delete", departmentJson);
        super.doAjax(options).then(function (result) {
            controller.doAction('department/viewAll')
        }).catch(error => console.error("Error: DepartmentService -> delete: " + error)
    )
        ;
    }


}


var departmentJson;

class EmployeeService extends MainService {

    viewEmployeesByDepartment() {
        if (arguments[0]) {
            departmentJson = arguments[0];
        }
        var options = super.getAjaxOptions("POST", "employee/viewEmployeesByDepartment", departmentJson);
        super.doAjax(options).then(drawEmployeesByDepartment).catch(error => console.error("EmployeeService -> viewEmployeesByDepartment: " + error)
    )
        ;

        function drawEmployeesByDepartment(result) {
            var mytable = $('<table></table>').attr({class: "table table-hover text-center"}).append($('<tbody></tbody>'));
            var rows = result.length;
            var thead = $('<thead></thead>');
            var rowHead = $('<tr></tr>');
            $('<td></td>').text('#').appendTo(rowHead);
            $('<td></td>').text('Name').appendTo(rowHead);
            $('<td></td>').text('Surname').appendTo(rowHead);
            $('<td></td>').text('Email').appendTo(rowHead);
            $('<td></td>').text('Hire date').appendTo(rowHead);
            $('<td></td>').text('Salary').appendTo(rowHead);
            $('<td></td>').text('Update').appendTo(rowHead);
            $('<td></td>').text('Delete').appendTo(rowHead);
            for (var i = 0; i < rows; i++) {
                var row = $('<tr></tr>').appendTo(mytable);
                $('<td></td>').text(i).appendTo(row);
                $('<td></td>').text(result[i].name).appendTo(row);
                $('<td></td>').text(result[i].surname).appendTo(row);
                $('<td></td>').text(result[i].email).appendTo(row);
                $('<td></td>').text(result[i].hireDate).appendTo(row);
                $('<td></td>').text(result[i].salary).appendTo(row);
                $('<td></td>').attr("onclick", "controller.doAction('employee/viewSaveForm',  " + JSON.stringify(result[i]) + "  )").text('Update').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
                $('<td></td>').attr("onclick", "controller.doAction('employee/delete',  " + JSON.stringify(result[i]) + "  )").text('Delete').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
            }
            rowHead.appendTo(thead);
            thead.appendTo(mytable);

            $('#wrapper').empty();
            $('#wrapper').append($('<h1></h1>').attr({class: "text-center"}).text("Employees of " + departmentJson.departmentName));
            $('#wrapper').append(mytable);
        }

    }

    viewSaveForm() {
        var employeeJson = arguments[0];
        if (employeeJson == undefined) {
            employeeJson = {
                "employeeId": null,
                "name": null,
                "surname": null,
                "hireDate": null,
                "email": null,
                "salary": null
            };
        }
        var options = super.getAjaxOptions("POST", "employee/viewSaveForm", employeeJson);
        super.doAjax(options).then(drawSaveForm).catch(error => console.error("EmployeeService -> viewSaveForm: " + error)
    )
        ;

        function drawSaveForm(result) {
            var departmentJson = result.department;
            var saveForm = $('<form></form>').attr({class: "form-horizontal col-lg-offset-2", id: "employeeSaveForm"});
            var employeeId = $('<input/>').attr({
                type: "hidden",
                id: "employeeId",
                value: result.employee.employeeId
            });
            var inputEmployeeName = $('<div></div>').attr({class: "form-group"});
            inputEmployeeName.append($('<label></label>').attr({for: "name"}).text("Name:"));
            inputEmployeeName.append($('<input/>').attr({
                type: "text",
                class: "form-control",
                id: "name",
                name: "name",
                value: result.employee.name
            }));
            inputEmployeeName.append($('<span></span>').attr({id: "errorName", class: "text-danger"}));
            var inputEmployeeSurname = $('<div></div>').attr({class: "form-group"});
            inputEmployeeSurname.append($('<label></label>').attr({for: "surname"}).text("Surname:"));
            inputEmployeeSurname.append($('<input/>').attr({
                type: "text",
                class: "form-control",
                id: "surname",
                name: "surname",
                value: result.employee.surname
            }));
            inputEmployeeSurname.append($('<span></span>').attr({id: "errorSurname", class: "text-danger"}));
            var inputEmployeeEmail = $('<div></div>').attr({class: "form-group"});
            inputEmployeeEmail.append($('<label></label>').attr({for: "email"}).text("Email:"));
            inputEmployeeEmail.append($('<input/>').attr({
                type: "text",
                class: "form-control",
                id: "email",
                name: "email",
                value: result.employee.email
            }));
            inputEmployeeEmail.append($('<span></span>').attr({id: "errorEmail", class: "text-danger"}));
            var inputEmployeeHireDate = $('<div></div>').attr({class: "form-group"});
            inputEmployeeHireDate.append($('<label></label>').attr({for: "hireDate"}).text("Hire date:"));
            inputEmployeeHireDate.append($('<input/>').attr({
                type: "date",
                class: "form-control",
                id: "hireDate",
                name: "hireDate",
                value: result.employee.hireDate
            }));
            inputEmployeeHireDate.append($('<span></span>').attr({id: "errorHireDate", class: "text-danger"}));
            var inputEmployeeSalary = $('<div></div>').attr({class: "form-group"});
            inputEmployeeSalary.append($('<label></label>').attr({for: "salary"}).text("Salary:"));
            inputEmployeeSalary.append($('<input/>').attr({
                type: "number",
                class: "form-control",
                id: "salary",
                name: "salary",
                value: result.employee.salary
            }));
            inputEmployeeSalary.append($('<span></span>').attr({id: "errorSalary", class: "text-danger"}));
            var selectDepartment = $('<div></div>').attr({class: "form-group"});
            selectDepartment.append($('<label></label>').attr({for: "selectDepartment"}).text("Department:"));
            var select = $('<select></select>').attr({class: "form-control", id: "departmentId"});
            for (var i = 0; i < result.departmentList.length; i++) {
                if (departmentJson != undefined && result.departmentList[i].departmentId == departmentJson.departmentId) {
                    $('<option></option>').attr('selected', 'selected').attr({
                        value: result.departmentList[i].departmentId
                    }).text(result.departmentList[i].departmentName).appendTo(select);
                } else {
                    $('<option></option>').attr({value: result.departmentList[i].departmentId}).text(result.departmentList[i].departmentName).appendTo(select);
                }
            }
            select.appendTo(selectDepartment);

            var saveButton = $('<div></div>').attr({class: "form-group"});
            saveButton.append($('<div></div>').attr({class: "col-lg-9 col-lg-offset-3"}).append($('<button></button>').attr({
                type: "button",
                class: "btn btn-primary"
            }).attr("onclick", "controller.doAction('employee/save')").text("Save")));

            employeeId.appendTo(saveForm);
            inputEmployeeName.appendTo(saveForm);
            inputEmployeeSurname.appendTo(saveForm);
            inputEmployeeEmail.appendTo(saveForm);
            inputEmployeeHireDate.appendTo(saveForm);
            inputEmployeeSalary.appendTo(saveForm);
            selectDepartment.appendTo(saveForm);
            saveButton.appendTo(saveForm);

            $('#wrapper').empty();
            $('#wrapper').append($('<h1>Save employee</h1>').attr({class: "text-center"}));
            $('#wrapper').append(saveForm);

            $(function () {
                controller.doAction("employee/save");
            })
        }

    }

    save() {
        let getAjaxOptions = super.getAjaxOptions;
        let doAjax = super.doAjax;

        let getEmployeeJson = function () {
            let employeeId = $('#employeeId').val();
            employeeId = (employeeId.trim() === '') ? undefined : employeeId;
            let name = $('#name').val();
            let surname = $('#surname').val();
            let hireDate = $('#hireDate').val();
            let email = $('#email').val();
            let salary = $('#salary').val();
            salary = (salary.trim() === '') ? undefined : salary;
            let departmentId = $('#departmentId').val();
            let departmentName = $('#departmentId option:selected').text();

            departmentJson = JSON.stringify({
                "departmentId": +departmentId,
                "departmentName": departmentName
            });

            return {
                "employee": {
                "employeeId": +employeeId,
                    "name": name,
                    "surname": surname,
                    "hireDate": hireDate,
                    "email": email,
                    "salary": +salary
            },
                "departmentId": +departmentId
            };
        }


        $.validator.addMethod("datePattern", function (value, element) {
                return /^([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))$/.test(value);
            },
            "Please enter date in format (yyyy-mm-dd)");


        $.validator.addMethod("onlyDigits", function (value, element) {
                return /^\d+$/.test(value);
            },
            "Please enter only digits");


        $.validator.addMethod("onlyChars", function (value, element) {
                return /^[a-zA-Z\s]+$/.test(value);
            },
            "Please use only chars");

        $.validator.addMethod("checkUniqueEmail", function (value, element) {
            let isUniqueEmail;
            $.ajax({
                async: false,
                type: "POST",
                url: "employee/uniqueEmail",
                data: JSON.stringify(getEmployeeJson()),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function (result) {
                    isUniqueEmail = result;
                }
            });
            return isUniqueEmail;
        }, "This email has already exist");


        $('#employeeSaveForm').validate({
            rules: {
                name: {
                    required: true,
                    minlength: 3,
                    maxlength: 100,
                    onlyChars: true
                },
                surname: {
                    required: true,
                    minlength: 3,
                    maxlength: 100,
                    onlyChars: true
                },
                email: {
                    required: true,
                    minlength: 3,
                    maxlength: 100,
                    checkUniqueEmail: true
                },
                hireDate: {
                    required: true,
                    datePattern: true
                },
                salary: {
                    required: true,
                    onlyDigits: true
                }

            },
            messages: {
                name: {
                    required: "Please enter an employee's name",
                    minlength: "Min length is 3 letters",
                    maxlength: "Max length is 100 letters"
                },
                surname: {
                    required: "Please enter an employee's surname",
                    minlength: "Min length is 3 letters",
                    maxlength: "Max length is 100 letters"
                },
                email: {
                    required: "Please enter an email",
                    minlength: "Min length is 3 letters",
                    maxlength: "Max length is 100 letters"
                },
                hireDate: {
                    required: "Please enter a hireDate"
                },
                salary: {
                    required: "Please enter a salary"
                }
            },
            submitHandler: function () {

                var options = getAjaxOptions("POST", "employee/save", getEmployeeJson());
                doAjax(options).then(function (result) {
                    controller.doAction("employee/viewEmployeesByDepartment", departmentJson);
                }).catch(function (error) {
                    console.error("EmployeeService -> save: " + error);
                    $('#wrapper').empty();
                    $('#wrapper').append($('<h1></h1>').attr({class: "text-center"}).text("404 Not found"));
                });

            }
        });







    }

    delete() {
        var employeeJson = arguments[0];
        var options = super.getAjaxOptions("POST", "employee/delete", employeeJson);
        super.doAjax(options).then(function (result) {
            controller.doAction("employee/viewEmployeesByDepartment");
        }).catch(error => console.error("EmployeeService -> delete: " + error)
    )
        ;
    }
}


class Controller {

    constructor() {
        this.departmentService = new DepartmentService();
        this.employeeService = new EmployeeService();
        this.actionsMap = new Map();
        this.actionsMap.set("department/viewAll", this.departmentService.viewAll);
        this.actionsMap.set("department/viewSaveForm", this.departmentService.viewSaveForm);
        this.actionsMap.set("department/save", this.departmentService.save);
        this.actionsMap.set("department/delete", this.departmentService.delete);
        this.actionsMap.set("employee/viewEmployeesByDepartment", this.employeeService.viewEmployeesByDepartment);
        this.actionsMap.set("employee/viewSaveForm", this.employeeService.viewSaveForm);
        this.actionsMap.set("employee/save", this.employeeService.save);
        this.actionsMap.set("employee/delete", this.employeeService.delete);
    }


    doAction() {
        let action = this.actionsMap.get(arguments[0]);
        if (action == undefined) {
            action = this.actionsMap.get("department/viewAll");
        }
        action(arguments[1]);
        console.log("doAction --- args[0] = " + arguments[0]);
    }
}


let controller = new Controller();

