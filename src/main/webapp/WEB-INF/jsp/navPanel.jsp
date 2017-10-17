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
                            <li><a href="#" onclick="department.controller.doAction('department/viewSaveForm')"> New
                                Department</a></li>
                            <li><a href="#" onclick="department.controller.doAction('employee/viewSaveForm')"> New
                                Employee</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>






