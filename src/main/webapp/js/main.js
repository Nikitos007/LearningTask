function drowTable() {
    var content = "<table>"
    for (i = 0; i < 3; i++) {
        content += '<tr><td>' + 'result ' + i + '</td></tr>';
    }
    content += "</table>"

    $('#here_table').append(content);
}

    // var table = $('<table></table>').addClass('foo');
    // for(i=0; i<3; i++){
    //     var row = $('<tr></tr>').addClass('bar').text('result ' + i);
    //     table.append(row);
    // }
    // $('#here_table').append(table);


// var controller = function (action) {
// console.log(action + " 555");
//     // sendRequest();
//
//     $('#wrapper').innerHTML = "<h1>DASDAS</h1>";
//
//
// }


function ajaxService() {

    this.sendRequest = function sendRequest(typeReq, urlReq, dataReq, dataTypeReq, successReq) {
        alert("sendRequest... typeReq: " + typeReq + " urlReq: " + urlReq + " dataReq: " + dataReq + " dataTypeReq: " + dataTypeReq);
        $.ajax({
            type: typeReq,
            url: urlReq,
            data: dataReq,
            success: function (res) {
                alert("res= " + res)
            }
        });
    }


}

function DepartmentService() {
    ajaxService.call(this);
    var doAjax = this.sendRequest;


    this.viewDepartments = function viewDepartments() {
        doAjax("GET", "department/viewAll", "", "json", drawAllDepartments);

        function drawAllDepartments(result) {
            alert("drawAllDepartments from server " + result);
        }

    }

    this.viewDepartmentSaveForm = function viewDepartmentSaveForm() {
        alert("viewDepartmentSaveForm");
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

    this.doAction = function (action) {
        var method = actionMap.get(action);
        method();
    }


}


var controller = new Controller();




