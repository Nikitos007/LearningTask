import MainValidation from "./mainValidation";

let employeeToSave;
let departmentOfEmployee;

export default class EmployeeValidation extends MainValidation {

    constructor() {
        super();
        this.checkUniqueEmail();
    }

    getEmployeeJson() {
        let employeeId = $('#employeeId').val();
        employeeId = (employeeId != undefined && employeeId.trim() === '') ? undefined : employeeId;
        let name = $('#name').val();
        let surname = $('#surname').val();
        let hireDate = $('#hireDate').val();
        let email = $('#email').val();
        let salary = $('#salary').val();
        salary = (salary != undefined && salary.trim() === '') ? undefined : salary;
        let departmentId = $('#departmentId').val();
        let departmentName = $('#departmentId option:selected').text();

        departmentOfEmployee = JSON.stringify({
            "departmentId": +departmentId,
            "departmentName": departmentName
        });
        let employeeJson = {
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
        employeeToSave = employeeJson;
        return employeeJson;
    }

    checkUniqueEmail() {
        let checkUnique = super.checkUnique;
        let employeeJson = this.getEmployeeJson;
        return $.validator.addMethod('checkUniqueEmail', function (value, element) {
            return checkUnique('employee/uniqueEmail', employeeJson().employee);
        }, 'This email has already exist');
    }


    getEmployeeToSave() {
        return employeeToSave;
    }

    getDepartmentOfEmployee() {
        return departmentOfEmployee;
    }

    validateEmployee(saveEmployee, formId) {
        $('#' + formId).validate({
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
                    checkEmail: true,
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

                saveEmployee();

                // let options = getAjaxOptions("POST", "employee/save", getEmployeeJson());
                // doAjax(options).then(function (result) {
                //     department.controller.doAction("employee/viewEmployeesByDepartment", JSON.parse(departmentJson));
                // }).catch(function (error) {
                //     console.error("EmployeeService -> save: " + error);
                //     let wrapper = $('#wrapper');
                //     wrapper.empty();
                //     wrapper.append($('<h1></h1>').attr({class: "text-center"}).text("404 Not found"));
                // });

            }
        });

    }


}
