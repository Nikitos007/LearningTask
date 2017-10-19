<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<div class="container-fluid">
    <div class="row">
        <div class="navbar navbar-inverse">
            <div class="container">
                <div class="col-lg-6 col-lg-offset-3">
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="#" onclick="department.controller.doAction('department/viewAll')">
                                Departments </a></li>
                            <li id="newDepartment"></li>
                            <li id="newEmployee"></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script>
    var departmentJson = JSON.stringify({
        "departmentId": null,
        "departmentName": null
    });
    var employeeJson = JSON.stringify({
        "employeeId": null,
        "name": null,
        "surname": null,
        "hireDate": null,
        "email": null,
        "salary": null
    });
    var newDepartment = $('<a href="#">New Department</a>').attr('onclick', 'department.controller.doAction("department/viewSaveForm", ' + departmentJson + ')');
    var newEmployee = $('<a href="#">New Employee</a>').attr('onclick', 'department.controller.doAction("employee/viewSaveForm", ' + employeeJson + ')');
    $('#newDepartment').append(newDepartment);
    $('#newEmployee').append(newEmployee);
</script>



