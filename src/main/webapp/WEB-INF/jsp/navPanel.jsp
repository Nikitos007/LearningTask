<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="portlet" uri="http://liferay.com/tld/portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<portlet:renderURL var="departments">
    <portlet:param name="action" value="department/viewDepartments"/>
</portlet:renderURL>

<portlet:renderURL var="newDepartment">
    <portlet:param name="action" value="department/viewDepartmentSaveForm"/>
</portlet:renderURL>

<portlet:renderURL var="newEmployee">
    <portlet:param name="action" value="employee/viewEmployeeSaveForm"/>
</portlet:renderURL>

<div class="container-fluid">
    <div class="row">
        <div class="navbar navbar-inverse">
            <div class="container">
                <div class="col-lg-6 col-lg-offset-3">
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="${departments}"> Departments </a></li>
                            <li><a href="${newDepartment}"> New Department</a>
                            </li>
                            <li><a href="${newEmployee}"> New Employee</a></li>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>






