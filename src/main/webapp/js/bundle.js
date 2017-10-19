var department =
/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 1);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var MainService = function () {
    function MainService() {
        _classCallCheck(this, MainService);
    }

    _createClass(MainService, [{
        key: "doAjax",
        value: function doAjax(options) {
            return new Promise(function (resolve, reject) {
                $.ajax(options).done(resolve).fail(reject);
            });
        }
    }, {
        key: "getAjaxOptions",
        value: function getAjaxOptions(typeReq, urlReq, dataReq) {
            return {
                type: typeReq,
                url: urlReq,
                data: JSON.stringify(dataReq),
                dataType: "json",
                contentType: "application/json; charset=utf-8"
            };
        }
    }]);

    return MainService;
}();

exports.default = MainService;

/***/ }),
/* 1 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var _controller = __webpack_require__(2);

var _controller2 = _interopRequireDefault(_controller);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var controller = new _controller2.default();
exports.controller = controller;

/***/ }),
/* 2 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _departmentService = __webpack_require__(3);

var _departmentService2 = _interopRequireDefault(_departmentService);

var _employeeService = __webpack_require__(5);

var _employeeService2 = _interopRequireDefault(_employeeService);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var Controller = function () {
    function Controller() {
        _classCallCheck(this, Controller);

        this.departmentService = new _departmentService2.default();
        this.employeeService = new _employeeService2.default();
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

    _createClass(Controller, [{
        key: "doAction",
        value: function doAction() {
            var action = this.actionsMap.get(arguments[0]);
            if (action == undefined) {
                action = this.actionsMap.get("department/viewAll");
            }
            action(arguments[1]);
        }
    }]);

    return Controller;
}();

exports.default = Controller;

/***/ }),
/* 3 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _get = function get(object, property, receiver) { if (object === null) object = Function.prototype; var desc = Object.getOwnPropertyDescriptor(object, property); if (desc === undefined) { var parent = Object.getPrototypeOf(object); if (parent === null) { return undefined; } else { return get(parent, property, receiver); } } else if ("value" in desc) { return desc.value; } else { var getter = desc.get; if (getter === undefined) { return undefined; } return getter.call(receiver); } };

var _mainService = __webpack_require__(0);

var _mainService2 = _interopRequireDefault(_mainService);

var _validation = __webpack_require__(6);

var _validation2 = _interopRequireDefault(_validation);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var DepartmentService = function (_MainService) {
    _inherits(DepartmentService, _MainService);

    function DepartmentService() {
        _classCallCheck(this, DepartmentService);

        var _this = _possibleConstructorReturn(this, (DepartmentService.__proto__ || Object.getPrototypeOf(DepartmentService)).call(this));

        _this.validator = new _validation2.default();
        _this.departmentValidator = _this.validator.departmentValidate;

        _this.viewSaveForm = _this.viewSaveForm.bind(_this);
        _this.save = _this.save.bind(_this);

        return _this;
    }

    _createClass(DepartmentService, [{
        key: "viewAll",
        value: function viewAll() {
            var options = _get(DepartmentService.prototype.__proto__ || Object.getPrototypeOf(DepartmentService.prototype), "getAjaxOptions", this).call(this, "GET", "department/viewAll", "");
            _get(DepartmentService.prototype.__proto__ || Object.getPrototypeOf(DepartmentService.prototype), "doAjax", this).call(this, options).then(drawAllDepartments).catch(function (error) {
                return console.error("Error: DepartmentService -> viewAll: " + error);
            });

            function drawAllDepartments(result) {
                var mytable = $('<table></table>').attr({ class: "table table-hover text-center" }).append($('<tbody></tbody>'));
                var thead = $('<thead></thead>');
                var rowHead = $('<tr></tr>');
                $('<td></td>').text('#').appendTo(rowHead);
                $('<td></td>').text('Department').appendTo(rowHead);
                $('<td></td>').text('Update').appendTo(rowHead);
                $('<td></td>').text('Delete').appendTo(rowHead);
                $('<td></td>').text('Employees').appendTo(rowHead);
                for (var i = 0; i < result.length; i++) {
                    var row = $('<tr></tr>').appendTo(mytable);
                    $('<td></td>').text(i).appendTo(row);
                    $('<td></td>').text(result[i].departmentName).appendTo(row);
                    $('<td></td>').attr("onclick", "department.controller.doAction('department/viewSaveForm',  " + JSON.stringify(result[i]) + "  )").text('Update').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
                    $('<td></td>').attr("onclick", "department.controller.doAction('department/delete',  " + JSON.stringify(result[i]) + "  )").text('Delete').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
                    $('<td></td>').attr("onclick", "department.controller.doAction('employee/viewEmployeesByDepartment',  " + JSON.stringify(result[i]) + "  )").text('Employees').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
                }
                rowHead.appendTo(thead);
                thead.appendTo(mytable);
                var div = $('<div></div>').attr({ class: "col-lg-12 text-center" }).append(mytable);
                var wrapper = $('#wrapper');
                wrapper.empty();
                wrapper.append($('<h1>Departments</h1>').attr({ class: "text-center" }));
                wrapper.append(div);
            }
        }
    }, {
        key: "viewSaveForm",
        value: function viewSaveForm() {
            var departmentJson = arguments[0];
            var options = _get(DepartmentService.prototype.__proto__ || Object.getPrototypeOf(DepartmentService.prototype), "getAjaxOptions", this).call(this, "POST", "department/viewSaveForm", departmentJson);
            _get(DepartmentService.prototype.__proto__ || Object.getPrototypeOf(DepartmentService.prototype), "doAjax", this).call(this, options).then(drawSaveForm).catch(function (error) {
                return console.error("Error: DepartmentService -> viewSaveForm: " + error);
            });

            var validator = this.departmentValidator;
            var saveDepartment = this.save;

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
                var inputDepartmentName = $('<div></div>').attr({ class: "form-group" });
                inputDepartmentName.append($('<label></label>').attr({ for: "departmentName" }).text("Name:"));
                inputDepartmentName.append($('<input/>').attr({
                    type: "text",
                    class: "form-control",
                    id: "departmentName",
                    name: "departmentName",
                    value: result.departmentName
                }));
                inputDepartmentName.append($('<span></span>').attr({ id: "errorDepartmentName", class: "text-danger" }));
                var saveButton = $('<div></div>').attr({ class: "form-group" });
                saveButton.append($('<div></div>').attr({ class: "col-lg-9 col-lg-offset-3" }).append($('<button></button>').attr({
                    type: "submit",
                    class: "btn btn-primary"
                }).text("Save")));
                // }).attr("onclick", "department.controller.doAction('department/save')").text("Save")));

                hiddenDepartmentId.appendTo(saveForm);
                inputDepartmentName.appendTo(saveForm);
                saveButton.appendTo(saveForm);
                var wrapper = $('#wrapper');
                wrapper.empty();
                wrapper.append($('<h1>Save department</h1>').attr({ class: "text-center" }));
                wrapper.append(saveForm);

                $(function () {
                    validator(function () {
                        return saveDepartment();
                    }, 'departmentSaveForm');
                });
            }
        }
    }, {
        key: "save",
        value: function save() {
            var options = _get(DepartmentService.prototype.__proto__ || Object.getPrototypeOf(DepartmentService.prototype), "getAjaxOptions", this).call(this, "POST", "department/save", this.validator.getDepartmentToSave());
            _get(DepartmentService.prototype.__proto__ || Object.getPrototypeOf(DepartmentService.prototype), "doAjax", this).call(this, options).then(function (result) {
                department.controller.doAction('department/viewAll');
            }).catch(function (error) {
                console.error("Error: DepartmentService -> save: " + error);
                $('#wrapper').empty();
                $('#wrapper').append($('<h1></h1>').attr({ class: "text-center" }).text("404 Not found"));
            });

            // let getAjaxOptions = super.getAjaxOptions;
            // let doAjax = super.doAjax;

            // let getDepartmentJson = function () {
            //     let departmentId = $('#departmentId').val();
            //     departmentId = (departmentId.trim() == "") ? undefined : departmentId;
            //     let departmentName = $('#departmentName').val();
            //     let departmentJson = {
            //         "departmentId": +departmentId,
            //         "departmentName": departmentName
            //     };
            //     return departmentJson;
            // };


            // let departmentJson = getDepartmentJson();


            // let saveValidDepartment = function () {
            //      let options = getAjaxOptions("POST", "department/save", departmentJson);
            //      doAjax(options).then(function (result) {
            //          department.controller.doAction('department/viewAll')
            //      }).catch(function (error) {
            //          console.error("Error: DepartmentService -> save: " + error);
            //          $('#wrapper').empty();
            //          $('#wrapper').append($('<h1></h1>').attr({class: "text-center"}).text("404 Not found"));
            //      });
            //  };

            // let validation = new Validation();
            // validation.departmentValidate(saveValidDepartment, departmentJson);

        }
    }, {
        key: "delete",
        value: function _delete() {
            var departmentJson = arguments[0];
            var options = _get(DepartmentService.prototype.__proto__ || Object.getPrototypeOf(DepartmentService.prototype), "getAjaxOptions", this).call(this, "POST", "department/delete", departmentJson);
            _get(DepartmentService.prototype.__proto__ || Object.getPrototypeOf(DepartmentService.prototype), "doAjax", this).call(this, options).then(function (result) {
                department.controller.doAction('department/viewAll');
            }).catch(function (error) {
                return console.error("Error: DepartmentService -> delete: " + error);
            });
        }
    }]);

    return DepartmentService;
}(_mainService2.default);

exports.default = DepartmentService;

/***/ }),
/* 4 */,
/* 5 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _get = function get(object, property, receiver) { if (object === null) object = Function.prototype; var desc = Object.getOwnPropertyDescriptor(object, property); if (desc === undefined) { var parent = Object.getPrototypeOf(object); if (parent === null) { return undefined; } else { return get(parent, property, receiver); } } else if ("value" in desc) { return desc.value; } else { var getter = desc.get; if (getter === undefined) { return undefined; } return getter.call(receiver); } };

var _mainService = __webpack_require__(0);

var _mainService2 = _interopRequireDefault(_mainService);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var departmentJson = void 0;

var EmployeeService = function (_MainService) {
    _inherits(EmployeeService, _MainService);

    function EmployeeService() {
        _classCallCheck(this, EmployeeService);

        return _possibleConstructorReturn(this, (EmployeeService.__proto__ || Object.getPrototypeOf(EmployeeService)).apply(this, arguments));
    }

    _createClass(EmployeeService, [{
        key: "viewEmployeesByDepartment",
        value: function viewEmployeesByDepartment() {
            departmentJson = arguments[0];
            var options = _get(EmployeeService.prototype.__proto__ || Object.getPrototypeOf(EmployeeService.prototype), "getAjaxOptions", this).call(this, "POST", "employee/viewEmployeesByDepartment", departmentJson);
            _get(EmployeeService.prototype.__proto__ || Object.getPrototypeOf(EmployeeService.prototype), "doAjax", this).call(this, options).then(drawEmployeesByDepartment).catch(function (error) {
                return console.error("EmployeeService -> viewEmployeesByDepartment: " + error);
            });

            function drawEmployeesByDepartment(result) {
                var mytable = $('<table></table>').attr({ class: "table table-hover text-center" }).append($('<tbody></tbody>'));
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
                    $('<td></td>').attr("onclick", "department.controller.doAction('employee/viewSaveForm',  " + JSON.stringify(result[i]) + "  )").text('Update').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
                    $('<td></td>').attr("onclick", "department.controller.doAction('employee/delete',  " + JSON.stringify(result[i]) + "  )").text('Delete').attr('onmouseenter', "this.style.cursor = 'pointer'").appendTo(row);
                }
                rowHead.appendTo(thead);
                thead.appendTo(mytable);

                $('#wrapper').empty();
                $('#wrapper').append($('<h1></h1>').attr({ class: "text-center" }).text("Employees of " + departmentJson.departmentName));
                $('#wrapper').append(mytable);
            }
        }
    }, {
        key: "viewSaveForm",
        value: function viewSaveForm() {
            var employeeJson = arguments[0];
            var options = _get(EmployeeService.prototype.__proto__ || Object.getPrototypeOf(EmployeeService.prototype), "getAjaxOptions", this).call(this, "POST", "employee/viewSaveForm", employeeJson);
            _get(EmployeeService.prototype.__proto__ || Object.getPrototypeOf(EmployeeService.prototype), "doAjax", this).call(this, options).then(drawSaveForm).catch(function (error) {
                return console.error("EmployeeService -> viewSaveForm: " + error);
            });

            function drawSaveForm(result) {
                var departmentJson = result.department;
                var saveForm = $('<form></form>').attr({ class: "form-horizontal col-lg-offset-2", id: "employeeSaveForm" });
                var employeeId = $('<input/>').attr({
                    type: "hidden",
                    id: "employeeId",
                    value: result.employee.employeeId
                });
                var inputEmployeeName = $('<div></div>').attr({ class: "form-group" });
                inputEmployeeName.append($('<label></label>').attr({ for: "name" }).text("Name:"));
                inputEmployeeName.append($('<input/>').attr({
                    type: "text",
                    class: "form-control",
                    id: "name",
                    name: "name",
                    value: result.employee.name
                }));
                inputEmployeeName.append($('<span></span>').attr({ id: "errorName", class: "text-danger" }));
                var inputEmployeeSurname = $('<div></div>').attr({ class: "form-group" });
                inputEmployeeSurname.append($('<label></label>').attr({ for: "surname" }).text("Surname:"));
                inputEmployeeSurname.append($('<input/>').attr({
                    type: "text",
                    class: "form-control",
                    id: "surname",
                    name: "surname",
                    value: result.employee.surname
                }));
                inputEmployeeSurname.append($('<span></span>').attr({ id: "errorSurname", class: "text-danger" }));
                var inputEmployeeEmail = $('<div></div>').attr({ class: "form-group" });
                inputEmployeeEmail.append($('<label></label>').attr({ for: "email" }).text("Email:"));
                inputEmployeeEmail.append($('<input/>').attr({
                    type: "text",
                    class: "form-control",
                    id: "email",
                    name: "email",
                    value: result.employee.email
                }));
                inputEmployeeEmail.append($('<span></span>').attr({ id: "errorEmail", class: "text-danger" }));
                var inputEmployeeHireDate = $('<div></div>').attr({ class: "form-group" });
                inputEmployeeHireDate.append($('<label></label>').attr({ for: "hireDate" }).text("Hire date:"));
                inputEmployeeHireDate.append($('<input/>').attr({
                    type: "date",
                    class: "form-control",
                    id: "hireDate",
                    name: "hireDate",
                    value: result.employee.hireDate
                }));
                inputEmployeeHireDate.append($('<span></span>').attr({ id: "errorHireDate", class: "text-danger" }));
                var inputEmployeeSalary = $('<div></div>').attr({ class: "form-group" });
                inputEmployeeSalary.append($('<label></label>').attr({ for: "salary" }).text("Salary:"));
                inputEmployeeSalary.append($('<input/>').attr({
                    type: "number",
                    class: "form-control",
                    id: "salary",
                    name: "salary",
                    value: result.employee.salary
                }));
                inputEmployeeSalary.append($('<span></span>').attr({ id: "errorSalary", class: "text-danger" }));
                var selectDepartment = $('<div></div>').attr({ class: "form-group" });
                selectDepartment.append($('<label></label>').attr({ for: "selectDepartment" }).text("Department:"));
                var select = $('<select></select>').attr({ class: "form-control", id: "departmentId" });
                for (var i = 0; i < result.departmentList.length; i++) {
                    if (departmentJson != undefined && result.departmentList[i].departmentId == departmentJson.departmentId) {
                        $('<option></option>').attr('selected', 'selected').attr({
                            value: result.departmentList[i].departmentId
                        }).text(result.departmentList[i].departmentName).appendTo(select);
                    } else {
                        $('<option></option>').attr({ value: result.departmentList[i].departmentId }).text(result.departmentList[i].departmentName).appendTo(select);
                    }
                }
                select.appendTo(selectDepartment);

                var saveButton = $('<div></div>').attr({ class: "form-group" });
                saveButton.append($('<div></div>').attr({ class: "col-lg-9 col-lg-offset-3" }).append($('<button></button>').attr({
                    type: "submit",
                    class: "btn btn-primary"
                }).attr("onclick", "department.controller.doAction('employee/save')").text("Save")));

                employeeId.appendTo(saveForm);
                inputEmployeeName.appendTo(saveForm);
                inputEmployeeSurname.appendTo(saveForm);
                inputEmployeeEmail.appendTo(saveForm);
                inputEmployeeHireDate.appendTo(saveForm);
                inputEmployeeSalary.appendTo(saveForm);
                selectDepartment.appendTo(saveForm);
                saveButton.appendTo(saveForm);

                $('#wrapper').empty();
                $('#wrapper').append($('<h1>Save employee</h1>').attr({ class: "text-center" }));
                $('#wrapper').append(saveForm);

                $(function () {
                    department.controller.doAction("employee/save");
                });
            }
        }
    }, {
        key: "save",
        value: function save() {
            var getAjaxOptions = _get(EmployeeService.prototype.__proto__ || Object.getPrototypeOf(EmployeeService.prototype), "getAjaxOptions", this);
            var doAjax = _get(EmployeeService.prototype.__proto__ || Object.getPrototypeOf(EmployeeService.prototype), "doAjax", this);

            var getEmployeeJson = function getEmployeeJson() {
                var employeeId = $('#employeeId').val();
                employeeId = employeeId.trim() === '' ? undefined : employeeId;
                var name = $('#name').val();
                var surname = $('#surname').val();
                var hireDate = $('#hireDate').val();
                var email = $('#email').val();
                var salary = $('#salary').val();
                salary = salary.trim() === '' ? undefined : salary;
                var departmentId = $('#departmentId').val();
                var departmentName = $('#departmentId option:selected').text();

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
            };

            $.validator.addMethod("datePattern", function (value, element) {
                return (/^([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))$/.test(value)
                );
            }, "Please enter date in format (yyyy-mm-dd)");

            $.validator.addMethod("onlyDigits", function (value, element) {
                return (/^\d+$/.test(value)
                );
            }, "Please enter only digits");

            $.validator.addMethod("onlyChars", function (value, element) {
                var unicodeWord = new RegExp("^[A-Za-z\xAA\xB5\xBA\xC0-\xD6\xD8-\xF6\xF8-\u02C1\u02C6-\u02D1\u02E0-\u02E4\u02EC\u02EE\u0370-\u0374\u0376\u0377\u037A-\u037D\u037F\u0386\u0388-\u038A\u038C\u038E-\u03A1\u03A3-\u03F5\u03F7-\u0481\u048A-\u052F\u0531-\u0556\u0559\u0561-\u0587\u05D0-\u05EA\u05F0-\u05F2\u0620-\u064A\u066E\u066F\u0671-\u06D3\u06D5\u06E5\u06E6\u06EE\u06EF\u06FA-\u06FC\u06FF\u0710\u0712-\u072F\u074D-\u07A5\u07B1\u07CA-\u07EA\u07F4\u07F5\u07FA\u0800-\u0815\u081A\u0824\u0828\u0840-\u0858\u08A0-\u08B4\u0904-\u0939\u093D\u0950\u0958-\u0961\u0971-\u0980\u0985-\u098C\u098F\u0990\u0993-\u09A8\u09AA-\u09B0\u09B2\u09B6-\u09B9\u09BD\u09CE\u09DC\u09DD\u09DF-\u09E1\u09F0\u09F1\u0A05-\u0A0A\u0A0F\u0A10\u0A13-\u0A28\u0A2A-\u0A30\u0A32\u0A33\u0A35\u0A36\u0A38\u0A39\u0A59-\u0A5C\u0A5E\u0A72-\u0A74\u0A85-\u0A8D\u0A8F-\u0A91\u0A93-\u0AA8\u0AAA-\u0AB0\u0AB2\u0AB3\u0AB5-\u0AB9\u0ABD\u0AD0\u0AE0\u0AE1\u0AF9\u0B05-\u0B0C\u0B0F\u0B10\u0B13-\u0B28\u0B2A-\u0B30\u0B32\u0B33\u0B35-\u0B39\u0B3D\u0B5C\u0B5D\u0B5F-\u0B61\u0B71\u0B83\u0B85-\u0B8A\u0B8E-\u0B90\u0B92-\u0B95\u0B99\u0B9A\u0B9C\u0B9E\u0B9F\u0BA3\u0BA4\u0BA8-\u0BAA\u0BAE-\u0BB9\u0BD0\u0C05-\u0C0C\u0C0E-\u0C10\u0C12-\u0C28\u0C2A-\u0C39\u0C3D\u0C58-\u0C5A\u0C60\u0C61\u0C85-\u0C8C\u0C8E-\u0C90\u0C92-\u0CA8\u0CAA-\u0CB3\u0CB5-\u0CB9\u0CBD\u0CDE\u0CE0\u0CE1\u0CF1\u0CF2\u0D05-\u0D0C\u0D0E-\u0D10\u0D12-\u0D3A\u0D3D\u0D4E\u0D5F-\u0D61\u0D7A-\u0D7F\u0D85-\u0D96\u0D9A-\u0DB1\u0DB3-\u0DBB\u0DBD\u0DC0-\u0DC6\u0E01-\u0E30\u0E32\u0E33\u0E40-\u0E46\u0E81\u0E82\u0E84\u0E87\u0E88\u0E8A\u0E8D\u0E94-\u0E97\u0E99-\u0E9F\u0EA1-\u0EA3\u0EA5\u0EA7\u0EAA\u0EAB\u0EAD-\u0EB0\u0EB2\u0EB3\u0EBD\u0EC0-\u0EC4\u0EC6\u0EDC-\u0EDF\u0F00\u0F40-\u0F47\u0F49-\u0F6C\u0F88-\u0F8C\u1000-\u102A\u103F\u1050-\u1055\u105A-\u105D\u1061\u1065\u1066\u106E-\u1070\u1075-\u1081\u108E\u10A0-\u10C5\u10C7\u10CD\u10D0-\u10FA\u10FC-\u1248\u124A-\u124D\u1250-\u1256\u1258\u125A-\u125D\u1260-\u1288\u128A-\u128D\u1290-\u12B0\u12B2-\u12B5\u12B8-\u12BE\u12C0\u12C2-\u12C5\u12C8-\u12D6\u12D8-\u1310\u1312-\u1315\u1318-\u135A\u1380-\u138F\u13A0-\u13F5\u13F8-\u13FD\u1401-\u166C\u166F-\u167F\u1681-\u169A\u16A0-\u16EA\u16F1-\u16F8\u1700-\u170C\u170E-\u1711\u1720-\u1731\u1740-\u1751\u1760-\u176C\u176E-\u1770\u1780-\u17B3\u17D7\u17DC\u1820-\u1877\u1880-\u18A8\u18AA\u18B0-\u18F5\u1900-\u191E\u1950-\u196D\u1970-\u1974\u1980-\u19AB\u19B0-\u19C9\u1A00-\u1A16\u1A20-\u1A54\u1AA7\u1B05-\u1B33\u1B45-\u1B4B\u1B83-\u1BA0\u1BAE\u1BAF\u1BBA-\u1BE5\u1C00-\u1C23\u1C4D-\u1C4F\u1C5A-\u1C7D\u1CE9-\u1CEC\u1CEE-\u1CF1\u1CF5\u1CF6\u1D00-\u1DBF\u1E00-\u1F15\u1F18-\u1F1D\u1F20-\u1F45\u1F48-\u1F4D\u1F50-\u1F57\u1F59\u1F5B\u1F5D\u1F5F-\u1F7D\u1F80-\u1FB4\u1FB6-\u1FBC\u1FBE\u1FC2-\u1FC4\u1FC6-\u1FCC\u1FD0-\u1FD3\u1FD6-\u1FDB\u1FE0-\u1FEC\u1FF2-\u1FF4\u1FF6-\u1FFC\u2071\u207F\u2090-\u209C\u2102\u2107\u210A-\u2113\u2115\u2119-\u211D\u2124\u2126\u2128\u212A-\u212D\u212F-\u2139\u213C-\u213F\u2145-\u2149\u214E\u2183\u2184\u2C00-\u2C2E\u2C30-\u2C5E\u2C60-\u2CE4\u2CEB-\u2CEE\u2CF2\u2CF3\u2D00-\u2D25\u2D27\u2D2D\u2D30-\u2D67\u2D6F\u2D80-\u2D96\u2DA0-\u2DA6\u2DA8-\u2DAE\u2DB0-\u2DB6\u2DB8-\u2DBE\u2DC0-\u2DC6\u2DC8-\u2DCE\u2DD0-\u2DD6\u2DD8-\u2DDE\u2E2F\u3005\u3006\u3031-\u3035\u303B\u303C\u3041-\u3096\u309D-\u309F\u30A1-\u30FA\u30FC-\u30FF\u3105-\u312D\u3131-\u318E\u31A0-\u31BA\u31F0-\u31FF\u3400-\u4DB5\u4E00-\u9FD5\uA000-\uA48C\uA4D0-\uA4FD\uA500-\uA60C\uA610-\uA61F\uA62A\uA62B\uA640-\uA66E\uA67F-\uA69D\uA6A0-\uA6E5\uA717-\uA71F\uA722-\uA788\uA78B-\uA7AD\uA7B0-\uA7B7\uA7F7-\uA801\uA803-\uA805\uA807-\uA80A\uA80C-\uA822\uA840-\uA873\uA882-\uA8B3\uA8F2-\uA8F7\uA8FB\uA8FD\uA90A-\uA925\uA930-\uA946\uA960-\uA97C\uA984-\uA9B2\uA9CF\uA9E0-\uA9E4\uA9E6-\uA9EF\uA9FA-\uA9FE\uAA00-\uAA28\uAA40-\uAA42\uAA44-\uAA4B\uAA60-\uAA76\uAA7A\uAA7E-\uAAAF\uAAB1\uAAB5\uAAB6\uAAB9-\uAABD\uAAC0\uAAC2\uAADB-\uAADD\uAAE0-\uAAEA\uAAF2-\uAAF4\uAB01-\uAB06\uAB09-\uAB0E\uAB11-\uAB16\uAB20-\uAB26\uAB28-\uAB2E\uAB30-\uAB5A\uAB5C-\uAB65\uAB70-\uABE2\uAC00-\uD7A3\uD7B0-\uD7C6\uD7CB-\uD7FB\uF900-\uFA6D\uFA70-\uFAD9\uFB00-\uFB06\uFB13-\uFB17\uFB1D\uFB1F-\uFB28\uFB2A-\uFB36\uFB38-\uFB3C\uFB3E\uFB40\uFB41\uFB43\uFB44\uFB46-\uFBB1\uFBD3-\uFD3D\uFD50-\uFD8F\uFD92-\uFDC7\uFDF0-\uFDFB\uFE70-\uFE74\uFE76-\uFEFC\uFF21-\uFF3A\uFF41-\uFF5A\uFF66-\uFFBE\uFFC2-\uFFC7\uFFCA-\uFFCF\uFFD2-\uFFD7\uFFDA-\uFFDC]+$");
                return unicodeWord.test(value);
            }, "Please use only chars");

            $.validator.addMethod("checkEmail", function (value, element) {
                var regex = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
                return regex.test(value);
            }, "Please enter email");

            $.validator.addMethod("checkUniqueEmail", function (value, element) {
                var isUniqueEmail = void 0;
                $.ajax({
                    async: false,
                    type: "POST",
                    url: "employee/uniqueEmail",
                    data: JSON.stringify(getEmployeeJson().employee),
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: function success(result) {
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
                submitHandler: function submitHandler() {

                    var options = getAjaxOptions("POST", "employee/save", getEmployeeJson());
                    doAjax(options).then(function (result) {
                        department.controller.doAction("employee/viewEmployeesByDepartment", JSON.parse(departmentJson));
                    }).catch(function (error) {
                        console.error("EmployeeService -> save: " + error);
                        $('#wrapper').empty();
                        $('#wrapper').append($('<h1></h1>').attr({ class: "text-center" }).text("404 Not found"));
                    });
                }
            });
        }
    }, {
        key: "delete",
        value: function _delete() {
            var employeeJson = arguments[0];
            var options = _get(EmployeeService.prototype.__proto__ || Object.getPrototypeOf(EmployeeService.prototype), "getAjaxOptions", this).call(this, "POST", "employee/delete", employeeJson);
            _get(EmployeeService.prototype.__proto__ || Object.getPrototypeOf(EmployeeService.prototype), "doAjax", this).call(this, options).then(function (result) {
                department.controller.doAction("employee/viewEmployeesByDepartment", departmentJson);
            }).catch(function (error) {
                return console.error("EmployeeService -> delete: " + error);
            });
        }
    }]);

    return EmployeeService;
}(_mainService2.default);

exports.default = EmployeeService;

/***/ }),
/* 6 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var departmentToSave = void 0;

var Validation = function () {
    function Validation() {
        _classCallCheck(this, Validation);

        this.onlyChars();
        this.checkUniqueName();
    }

    _createClass(Validation, [{
        key: 'getDepartmentToSave',
        value: function getDepartmentToSave() {
            return departmentToSave;
        }
    }, {
        key: 'departmentValidate',
        value: function departmentValidate(saveDepartment, formId) {
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
                submitHandler: function submitHandler() {
                    saveDepartment();
                }
            });
        }
    }, {
        key: 'onlyChars',
        value: function onlyChars() {
            return $.validator.addMethod("onlyChars", function (value, element) {
                var unicodeWord = new RegExp('^[A-Za-z\xAA\xB5\xBA\xC0-\xD6\xD8-\xF6\xF8-\u02C1\u02C6-\u02D1\u02E0-\u02E4\u02EC\u02EE\u0370-\u0374\u0376\u0377\u037A-\u037D\u037F\u0386\u0388-\u038A\u038C\u038E-\u03A1\u03A3-\u03F5\u03F7-\u0481\u048A-\u052F\u0531-\u0556\u0559\u0561-\u0587\u05D0-\u05EA\u05F0-\u05F2\u0620-\u064A\u066E\u066F\u0671-\u06D3\u06D5\u06E5\u06E6\u06EE\u06EF\u06FA-\u06FC\u06FF\u0710\u0712-\u072F\u074D-\u07A5\u07B1\u07CA-\u07EA\u07F4\u07F5\u07FA\u0800-\u0815\u081A\u0824\u0828\u0840-\u0858\u08A0-\u08B4\u0904-\u0939\u093D\u0950\u0958-\u0961\u0971-\u0980\u0985-\u098C\u098F\u0990\u0993-\u09A8\u09AA-\u09B0\u09B2\u09B6-\u09B9\u09BD\u09CE\u09DC\u09DD\u09DF-\u09E1\u09F0\u09F1\u0A05-\u0A0A\u0A0F\u0A10\u0A13-\u0A28\u0A2A-\u0A30\u0A32\u0A33\u0A35\u0A36\u0A38\u0A39\u0A59-\u0A5C\u0A5E\u0A72-\u0A74\u0A85-\u0A8D\u0A8F-\u0A91\u0A93-\u0AA8\u0AAA-\u0AB0\u0AB2\u0AB3\u0AB5-\u0AB9\u0ABD\u0AD0\u0AE0\u0AE1\u0AF9\u0B05-\u0B0C\u0B0F\u0B10\u0B13-\u0B28\u0B2A-\u0B30\u0B32\u0B33\u0B35-\u0B39\u0B3D\u0B5C\u0B5D\u0B5F-\u0B61\u0B71\u0B83\u0B85-\u0B8A\u0B8E-\u0B90\u0B92-\u0B95\u0B99\u0B9A\u0B9C\u0B9E\u0B9F\u0BA3\u0BA4\u0BA8-\u0BAA\u0BAE-\u0BB9\u0BD0\u0C05-\u0C0C\u0C0E-\u0C10\u0C12-\u0C28\u0C2A-\u0C39\u0C3D\u0C58-\u0C5A\u0C60\u0C61\u0C85-\u0C8C\u0C8E-\u0C90\u0C92-\u0CA8\u0CAA-\u0CB3\u0CB5-\u0CB9\u0CBD\u0CDE\u0CE0\u0CE1\u0CF1\u0CF2\u0D05-\u0D0C\u0D0E-\u0D10\u0D12-\u0D3A\u0D3D\u0D4E\u0D5F-\u0D61\u0D7A-\u0D7F\u0D85-\u0D96\u0D9A-\u0DB1\u0DB3-\u0DBB\u0DBD\u0DC0-\u0DC6\u0E01-\u0E30\u0E32\u0E33\u0E40-\u0E46\u0E81\u0E82\u0E84\u0E87\u0E88\u0E8A\u0E8D\u0E94-\u0E97\u0E99-\u0E9F\u0EA1-\u0EA3\u0EA5\u0EA7\u0EAA\u0EAB\u0EAD-\u0EB0\u0EB2\u0EB3\u0EBD\u0EC0-\u0EC4\u0EC6\u0EDC-\u0EDF\u0F00\u0F40-\u0F47\u0F49-\u0F6C\u0F88-\u0F8C\u1000-\u102A\u103F\u1050-\u1055\u105A-\u105D\u1061\u1065\u1066\u106E-\u1070\u1075-\u1081\u108E\u10A0-\u10C5\u10C7\u10CD\u10D0-\u10FA\u10FC-\u1248\u124A-\u124D\u1250-\u1256\u1258\u125A-\u125D\u1260-\u1288\u128A-\u128D\u1290-\u12B0\u12B2-\u12B5\u12B8-\u12BE\u12C0\u12C2-\u12C5\u12C8-\u12D6\u12D8-\u1310\u1312-\u1315\u1318-\u135A\u1380-\u138F\u13A0-\u13F5\u13F8-\u13FD\u1401-\u166C\u166F-\u167F\u1681-\u169A\u16A0-\u16EA\u16F1-\u16F8\u1700-\u170C\u170E-\u1711\u1720-\u1731\u1740-\u1751\u1760-\u176C\u176E-\u1770\u1780-\u17B3\u17D7\u17DC\u1820-\u1877\u1880-\u18A8\u18AA\u18B0-\u18F5\u1900-\u191E\u1950-\u196D\u1970-\u1974\u1980-\u19AB\u19B0-\u19C9\u1A00-\u1A16\u1A20-\u1A54\u1AA7\u1B05-\u1B33\u1B45-\u1B4B\u1B83-\u1BA0\u1BAE\u1BAF\u1BBA-\u1BE5\u1C00-\u1C23\u1C4D-\u1C4F\u1C5A-\u1C7D\u1CE9-\u1CEC\u1CEE-\u1CF1\u1CF5\u1CF6\u1D00-\u1DBF\u1E00-\u1F15\u1F18-\u1F1D\u1F20-\u1F45\u1F48-\u1F4D\u1F50-\u1F57\u1F59\u1F5B\u1F5D\u1F5F-\u1F7D\u1F80-\u1FB4\u1FB6-\u1FBC\u1FBE\u1FC2-\u1FC4\u1FC6-\u1FCC\u1FD0-\u1FD3\u1FD6-\u1FDB\u1FE0-\u1FEC\u1FF2-\u1FF4\u1FF6-\u1FFC\u2071\u207F\u2090-\u209C\u2102\u2107\u210A-\u2113\u2115\u2119-\u211D\u2124\u2126\u2128\u212A-\u212D\u212F-\u2139\u213C-\u213F\u2145-\u2149\u214E\u2183\u2184\u2C00-\u2C2E\u2C30-\u2C5E\u2C60-\u2CE4\u2CEB-\u2CEE\u2CF2\u2CF3\u2D00-\u2D25\u2D27\u2D2D\u2D30-\u2D67\u2D6F\u2D80-\u2D96\u2DA0-\u2DA6\u2DA8-\u2DAE\u2DB0-\u2DB6\u2DB8-\u2DBE\u2DC0-\u2DC6\u2DC8-\u2DCE\u2DD0-\u2DD6\u2DD8-\u2DDE\u2E2F\u3005\u3006\u3031-\u3035\u303B\u303C\u3041-\u3096\u309D-\u309F\u30A1-\u30FA\u30FC-\u30FF\u3105-\u312D\u3131-\u318E\u31A0-\u31BA\u31F0-\u31FF\u3400-\u4DB5\u4E00-\u9FD5\uA000-\uA48C\uA4D0-\uA4FD\uA500-\uA60C\uA610-\uA61F\uA62A\uA62B\uA640-\uA66E\uA67F-\uA69D\uA6A0-\uA6E5\uA717-\uA71F\uA722-\uA788\uA78B-\uA7AD\uA7B0-\uA7B7\uA7F7-\uA801\uA803-\uA805\uA807-\uA80A\uA80C-\uA822\uA840-\uA873\uA882-\uA8B3\uA8F2-\uA8F7\uA8FB\uA8FD\uA90A-\uA925\uA930-\uA946\uA960-\uA97C\uA984-\uA9B2\uA9CF\uA9E0-\uA9E4\uA9E6-\uA9EF\uA9FA-\uA9FE\uAA00-\uAA28\uAA40-\uAA42\uAA44-\uAA4B\uAA60-\uAA76\uAA7A\uAA7E-\uAAAF\uAAB1\uAAB5\uAAB6\uAAB9-\uAABD\uAAC0\uAAC2\uAADB-\uAADD\uAAE0-\uAAEA\uAAF2-\uAAF4\uAB01-\uAB06\uAB09-\uAB0E\uAB11-\uAB16\uAB20-\uAB26\uAB28-\uAB2E\uAB30-\uAB5A\uAB5C-\uAB65\uAB70-\uABE2\uAC00-\uD7A3\uD7B0-\uD7C6\uD7CB-\uD7FB\uF900-\uFA6D\uFA70-\uFAD9\uFB00-\uFB06\uFB13-\uFB17\uFB1D\uFB1F-\uFB28\uFB2A-\uFB36\uFB38-\uFB3C\uFB3E\uFB40\uFB41\uFB43\uFB44\uFB46-\uFBB1\uFBD3-\uFD3D\uFD50-\uFD8F\uFD92-\uFDC7\uFDF0-\uFDFB\uFE70-\uFE74\uFE76-\uFEFC\uFF21-\uFF3A\uFF41-\uFF5A\uFF66-\uFFBE\uFFC2-\uFFC7\uFFCA-\uFFCF\uFFD2-\uFFD7\uFFDA-\uFFDC]+$');
                return unicodeWord.test(value);
            }, "Please enter only chars");
        }
    }, {
        key: 'checkUniqueName',
        value: function checkUniqueName() {
            var t = this.departmentToSave;

            return $.validator.addMethod("checkUniqueName", function (value, element) {
                var isUniqueName = void 0;

                departmentJson = function departmentJson() {
                    var departmentId = $('#departmentId').val();
                    departmentId = departmentId != undefined && departmentId.trim() == "" ? undefined : departmentId;
                    var departmentName = $('#departmentName').val();
                    var departmentJson = {
                        "departmentId": +departmentId,
                        "departmentName": departmentName
                    };
                    return departmentJson;
                };

                departmentToSave = departmentJson();
                $.ajax({
                    async: false,
                    type: "POST",
                    url: "department/uniqueName",
                    data: JSON.stringify(departmentToSave),
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: function success(result) {
                        isUniqueName = result;
                    }
                });

                return isUniqueName;
            }, "This name has already exist");
        }
    }]);

    return Validation;
}();

exports.default = Validation;

/***/ })
/******/ ]);