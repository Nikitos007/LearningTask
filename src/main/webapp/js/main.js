function MainService() {
    this.sendRequest = function sendRequest(typeReq, urlReq, dataReq, dataTypeReq, successReq) {
        $.ajax({
            type: typeReq,
            url: urlReq,
            data: dataReq,
            dataType: dataTypeReq,
            contentType: "application/json; charset=utf-8",
            success: successReq
        });
    }
}


function DepartmentService() {
    MainService.call(this);
    var doAjax = this.sendRequest;

    this.viewDepartments = function viewDepartments() {
        doAjax("GET", "department/viewAll", "", "json", drawAllDepartments);

        function drawAllDepartments(result) {
            mytable = $('<table></table>').attr({class: "table table-hover text-center"}).append($('<tbody></tbody>'));
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
                $('<td></td>').attr("onclick", "controller.doAction('department/viewDepartmentSaveForm',  '" + JSON.stringify(result[i]) + "'  )").text('Update').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
                $('<td></td>').attr("onclick", "controller.doAction('department/deleteDepartment',  '" + JSON.stringify(result[i]) + "'  )").text('Delete').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
                $('<td></td>').attr("onclick", "controller.doAction('employee/viewEmployeesByDepartment',  '" + JSON.stringify(result[i]) + "'  )").text('Employees').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
            }
            rowHead.appendTo(thead);
            thead.appendTo(mytable);
            var div = $('<div></div>').attr({class: "col-lg-12 text-center"}).append(mytable);
            $('#wrapper').empty();
            $('#wrapper').append($('<h1>Departments</h1>').attr({class: "text-center"}));
            $('#wrapper').append(div);
        }

    }

    this.viewDepartmentSaveForm = function viewDepartmentSaveForm() {
        var departmentJson = arguments[0];
        if (departmentJson == undefined) {
            departmentJson = {
                "departmentId": null,
                "departmentName": null
            };
            departmentJson = JSON.stringify(departmentJson);
        }
        doAjax("POST", "department/viewForm", departmentJson, "json", drawSaveForm);

        function drawSaveForm(result) {
            var saveForm = $('<form></form>').attr({class: "form-horizontal col-lg-offset-2"});
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
                value: result.departmentName
            }));
            inputDepartmentName.append($('<span></span>').attr({id: "errorDepartmentName", class: "text-danger"}));
            var saveButton = $('<div></div>').attr({class: "form-group"});
            saveButton.append($('<div></div>').attr({class: "col-lg-9 col-lg-offset-3"}).append($('<button></button>').attr({
                type: "button",
                class: "btn btn-primary"
            }).attr("onclick", "controller.doAction('department/saveDepartment')").text("Save")));

            hiddenDepartmentId.appendTo(saveForm);
            inputDepartmentName.appendTo(saveForm);
            saveButton.appendTo(saveForm);

            $('#wrapper').empty();
            $('#wrapper').append($('<h1>Save department</h1>').attr({class: "text-center"}));
            $('#wrapper').append(saveForm);
        }

    }

    this.saveDepartment = function saveDepartment() {
        var departmentId = $('#departmentId').val();
        departmentId = (departmentId.trim() == "") ? undefined : departmentId;
        var departmentName = $('#departmentName').val();
        var json = {
            "departmentId": +departmentId,
            "departmentName": departmentName
        };
        doAjax("POST", "department/save", JSON.stringify(json), "json", drawValidateException);

        function drawValidateException(result) {
            var errors = result.errors;
            if (errors != null) {
                $('#errorDepartmentName').text(result.errors.departmentName);
            } else {
                controller.doAction('department/viewDepartments');
            }
        }

    }

    this.deleteDepartment = function deleteDepartment() {
        var departmentJson = arguments[0];
        doAjax("POST", "department/delete", departmentJson, "json", function () {
            controller.doAction('department/viewDepartments');
        });

    }

}


function EmployeeService() {
    MainService.call(this);
    var doAjax = this.sendRequest;
    var departmentJson;

    this.viewEmployeesByDepartment = function viewEmployeesByDepartment() {
        departmentJson = arguments[0];
        doAjax("POST", "employee/viewEmployeesByDepartment", departmentJson, "json", drawEmployeesByDepartment);

        function drawEmployeesByDepartment(result) {
            mytable = $('<table></table>').attr({class: "table table-hover text-center"}).append($('<tbody></tbody>'));
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
                $('<td></td>').attr("onclick", "controller.doAction('employee/viewEmployeeSaveForm',  '" + JSON.stringify(result[i]) + "'  )").text('Update').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
                $('<td></td>').attr("onclick", "controller.doAction('employee/deleteEmployee',  '" + JSON.stringify(result[i]) + "'  )").text('Delete').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
            }
            rowHead.appendTo(thead);
            thead.appendTo(mytable);

            $('#wrapper').empty();
            $('#wrapper').append($('<h1></h1>').attr({class: "text-center"}).text("Employees of " + JSON.parse(departmentJson).departmentName));
            $('#wrapper').append(mytable);
        }

    }

    this.viewEmployeeSaveForm = function viewEmployeeSaveForm() {
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
            employeeJson = JSON.stringify(employeeJson);
        }
        doAjax("POST", "employee/viewForm", employeeJson, "json", drawSaveForm);

        function drawSaveForm(result) {
            departmentJson = result.department;
            var saveForm = $('<form></form>').attr({class: "form-horizontal col-lg-offset-2"});
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
                value: result.employee.name
            }));
            inputEmployeeName.append($('<span></span>').attr({id: "errorName", class: "text-danger"}));
            var inputEmployeeSurname = $('<div></div>').attr({class: "form-group"});
            inputEmployeeSurname.append($('<label></label>').attr({for: "surname"}).text("Surname:"));
            inputEmployeeSurname.append($('<input/>').attr({
                type: "text",
                class: "form-control",
                id: "surname",
                value: result.employee.surname
            }));
            inputEmployeeSurname.append($('<span></span>').attr({id: "errorSurname", class: "text-danger"}));
            var inputEmployeeEmail = $('<div></div>').attr({class: "form-group"});
            inputEmployeeEmail.append($('<label></label>').attr({for: "email"}).text("Email:"));
            inputEmployeeEmail.append($('<input/>').attr({
                type: "text",
                class: "form-control",
                id: "email",
                value: result.employee.email
            }));
            inputEmployeeEmail.append($('<span></span>').attr({id: "errorEmail", class: "text-danger"}));
            var inputEmployeeHireDate = $('<div></div>').attr({class: "form-group"});
            inputEmployeeHireDate.append($('<label></label>').attr({for: "hireDate"}).text("Hire date:"));
            inputEmployeeHireDate.append($('<input/>').attr({
                type: "date",
                class: "form-control",
                id: "hireDate",
                value: result.employee.hireDate
            }));
            inputEmployeeHireDate.append($('<span></span>').attr({id: "errorHireDate", class: "text-danger"}));
            var inputEmployeeSalary = $('<div></div>').attr({class: "form-group"});
            inputEmployeeSalary.append($('<label></label>').attr({for: "salary"}).text("Salary:"));
            inputEmployeeSalary.append($('<input/>').attr({
                type: "number",
                class: "form-control",
                id: "salary",
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
            }).attr("onclick", "controller.doAction('employee/saveEmployee')").text("Save")));

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
        }

    }


    this.saveEmployee = function saveEmployee() {
        var employeeId = $('#employeeId').val();
        employeeId = (employeeId.trim() == "") ? undefined : employeeId;
        var name = $('#name').val();
        var surname = $('#surname').val();
        var hireDate = $('#hireDate').val();
        var email = $('#email').val();
        var salary = $('#salary').val();
        salary = (salary.trim() == "") ? undefined : salary;
        var departmentId = $('#departmentId').val();
        var departmentName = $('#departmentId option:selected').text();

        departmentJson = JSON.stringify({
            "departmentId": +departmentId,
            "departmentName": departmentName
        });

        var json = {
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
        doAjax("POST", "employee/save", JSON.stringify(json), "json", drawValidateException);

        function drawValidateException(result) {
            var errors = result.errors;
            if (errors != null) {
                var errorName = result.errors.name;
                var errorSurname = result.errors.surname;
                var errorEmail = result.errors.email;
                var errorHireDate = result.errors.hireDate;
                var errorSalary = result.errors.salary;
                (errorName == undefined) ? $('#errorName').empty() : $('#errorName').text(errorName);
                (errorSurname == undefined) ? $('#errorSurname').empty() : $('#errorSurname').text(errorSurname);
                (errorEmail == undefined) ? $('#errorEmail').empty() : $('#errorEmail').text(errorEmail);
                (errorHireDate == undefined) ? $('#errorHireDate').empty() : $('#errorHireDate').text(errorHireDate);
                (errorSalary == undefined) ? $('#errorSalary').empty() : $('#errorSalary').text(errorSalary);
            } else {
                controller.doAction('employee/viewEmployeesByDepartment', departmentJson);
            }
        }

    }

    this.deleteEmployee = function deleteEmployee() {
        var employeeJson = arguments[0];
        doAjax("POST", "employee/delete", employeeJson, "json", function () {
            controller.doAction("employee/viewEmployeesByDepartment");
        });

    }

}


function Controller() {
    var departmentService = new DepartmentService();
    var employeeService = new EmployeeService();

    var actionMap = new Map();
    actionMap.set("department/viewDepartments", departmentService.viewDepartments);
    actionMap.set("department/viewDepartmentSaveForm", departmentService.viewDepartmentSaveForm);
    actionMap.set("department/saveDepartment", departmentService.saveDepartment);
    actionMap.set("department/deleteDepartment", departmentService.deleteDepartment);
    actionMap.set("employee/viewEmployeesByDepartment", employeeService.viewEmployeesByDepartment);
    actionMap.set("employee/viewEmployeeSaveForm", employeeService.viewEmployeeSaveForm);
    actionMap.set("employee/saveEmployee", employeeService.saveEmployee);
    actionMap.set("employee/deleteEmployee", employeeService.deleteEmployee);


    this.doAction = function () {
        var method = actionMap.get(arguments[0]);
        if (method == undefined) {
            method = actionMap.get("department/viewDepartments");
        }
        if (arguments[1] != undefined) {
            method(arguments[1]);
        } else {
            method();
        }
    }

}

var controller = new Controller();




