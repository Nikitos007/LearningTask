// var table = $('<table></table>').addClass('foo');
// for(i=0; i<3; i++){
//     var row = $('<tr></tr>').addClass('bar').text('result ' + i);
//     table.append(row);
// }
// $('#here_table').append(table);


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
            mytable = $('<table></table>').attr({class: "table table-hover"}).append($('<tbody></tbody>'));
            var rows = result.length;
            var cols = 2;
            var tr = [];

            var thead = $('<thead></thead>');
            rowHead = $('<tr></tr>');
            $('<td></td>').text('#').appendTo(rowHead);
            $('<td></td>').text('Department').appendTo(rowHead);
            $('<td></td>').text('Update').appendTo(rowHead);
            $('<td></td>').text('Delete').appendTo(rowHead);
            $('<td></td>').text('Employees').appendTo(rowHead);
            rowHead.appendTo(thead);
            thead.appendTo(mytable);

            for (var i = 0; i < rows; i++) {
                var row = $('<tr></tr>').appendTo(mytable);
                $('<td></td>').text(i).appendTo(row);
                $('<td></td>').text(result[i].departmentName).appendTo(row);

                $('<td></td>').attr("onclick", "controller.doAction('department/viewDepartmentSaveForm',  '" + JSON.stringify(result[i]) + "'  )").text('Update').appendTo(row);

            }
            var div = $('<div></div>').attr({class: "col-lg-12 text-center"}).append(mytable);

            $('#wrapper').empty();
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
            var header = $('<h1></h1>').attr({class: "text-center"}).text("Save department");
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
            var saveButton = $('<div></div>').attr({class: "form-group"});
            saveButton.append($('<div></div>').attr({class: "col-lg-9 col-lg-offset-3"}).append($('<button></button>').attr({
                type: "button",
                class: "btn btn-primary"
            }).attr("onclick", "controller.doAction('department/saveDepartment')").text("Save")));

            hiddenDepartmentId.appendTo(saveForm);
            inputDepartmentName.appendTo(saveForm);
            saveButton.appendTo(saveForm);

            $('#wrapper').empty();
            $('#wrapper').append(header);
            $('#wrapper').append(saveForm);
        }


    }

    this.saveDepartment = function saveDepartment() {
        var departmentId = $('#departmentId').val();
        var departmentName = $('#departmentName').val();
        var json = {
            "departmentId": +departmentId,
            "departmentName": departmentName
        };
        alert(JSON.stringify(json));
        doAjax("POST", "department/save", JSON.stringify(json), "application/json", function () {
            alert("saveDepartment");
        });
        controller.doAction('department/viewDepartments');
    }


    this.deleteDepartment = function deleteDepartment() {
        alert("deleteDepartment");
    }

    function validateDepartmentSaveForm() {

    }


}

function EmployeeService() {

    function viewEmployeesByDepartment() {

    }

    function viewEmployeeSaveForm() {

    }

    function saveEmployee() {

    }

    function deleteEmployee() {

    }

    function validateEmployeeSaveForm() {

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




