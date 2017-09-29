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
                            <li><a href="#" onclick="controller.doAction('department/viewDepartments')">
                                Departments </a></li>
                            <li><a href="#" onclick="controller.doAction('department/viewDepartmentSaveForm')"> New
                                Department</a></li>
                            <li><a href="#" onclick="controller.doAction('department/viewEmployeeSaveForm')"> New
                                Employee</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>






