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
        doAjax("POST", "department/delete", departmentJson, "json", function (result) {
            controller.doAction('department/viewDepartments');
        });

    }

}
