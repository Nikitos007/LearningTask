'use strict';

import DepartmentService from "./departmentService";
import EmployeeService from "./employeeService";

export default class Controller {
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
    }

}



