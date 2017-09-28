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

    this.drawTable = function drawTable(columns) {


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
            $('#wrapper').append(div);


        }
    }


    this.viewDepartmentSaveForm = function viewDepartmentSaveForm() {
        var department = JSON.parse(arguments[0]);


        alert("id = " + department.departmentId)
        alert("name = " + department.departmentName);


        doAjax("GET", "department/viewForm", department, "json", drawSaveForm);

        alert("viewDepartmentSaveForm");


        function drawSaveForm() {

        }

    }

    this.saveDepartment = function saveDepartment() {
        alert("saveDepartment");
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

        if (arguments[1] != undefined) {
            alert(arguments[1]);
            alert("arguments[1].departmentName = " + arguments[1].departmentName);
            method(arguments[1]);
        } else {
            method();
        }
    }


}


var controller = new Controller();




