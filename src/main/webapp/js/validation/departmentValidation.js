import MainValidation from "./mainValidation";

let departmentToSave;

export default class DepartmentValidation extends MainValidation {

    constructor() {
        super();
        this.checkUniqueName();
    }

    getDepartmentJson() {
        let departmentId = $('#departmentId').val();
        departmentId = (departmentId != undefined && departmentId.trim() == "") ? undefined : departmentId;
        let departmentName = $('#departmentName').val();
        let departmentJson = {
            "departmentId": +departmentId,
            "departmentName": departmentName
        }
        departmentToSave = departmentJson;
        return departmentJson;
    }

    checkUniqueName() {
        let checkUnique = super.checkUnique;
        let departmentJson = this.getDepartmentJson;
        return $.validator.addMethod('checkUniqueName', function (value, element) {
            return checkUnique('department/uniqueName', departmentJson());
        }, 'This name has already exist');
    }


    getDepartmentToSave() {
        return departmentToSave;
    }

    validateDepartment(saveDepartment, formId) {
        $('#' + formId).validate({
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
                saveDepartment();
            }
        });
    }

}