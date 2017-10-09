class MainService {
    ajax(typeReq, urlReq, dataReq, successReq) {
        console.log("*** ajax");
        $.ajax({
            type: typeReq,
            url: urlReq,
            data: dataReq,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: successReq
        });
    }
}


class DepartmentService extends MainService {
    viewAll() {
        super.ajax("GET", "department/viewAll", "", drawAllDepartments);
        console.log("DepartmentService --- viewAll");

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

    viewSaveForm() {
        super.ajax();
        console.log("DepartmentService --- viewSaveForm");
    }

    save() {
        super.ajax();
        console.log("DepartmentService --- save");
    }

    delete() {
        super.ajax();
        console.log("DepartmentService --- delete");
    }
}


class EmployeeService extends MainService {
    viewEmployeesByDepartment() {
        super.ajax();
        console.log("EmployeeService --- viewEmployeesByDepartment");
    }

    viewSaveForm() {
        super.ajax();
        console.log("EmployeeService --- viewSaveForm");
    }

    save() {
        super.ajax();
        console.log("EmployeeService --- save");
    }

    delete() {
        super.ajax();
        console.log("EmployeeService --- delete");
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


    doAction(

...
    args
) {
    console
.
    log(

    "doAction --- args[0] = "
+
    args
    [0]
)
    ;
    let
    action = this.actionsMap.get(args[0]);

    if(action

==
    undefined
) {
    action = this.actionsMap.get("department/viewAll");
}
action(args[1]);
console.log("doAction");
    }
}


let controller = new Controller();


// controller.doAction();
// Создаём новый автомобиль
// Создаём Porscheontroller();