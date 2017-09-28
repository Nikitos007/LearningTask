<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Departments</title>
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
</head>
<body>

<c:import url="navPanel.jsp"/>

<div class="container">
    <h1 class="text-center">Departments</h1>
    <div class="panel panel-info">
        <div class="panel-body">
            <div class="row">
                <div class="col-lg-12 text-center">

                    <table class="table table table-hover">
                        <tbody>
                        <thead>
                        <tr>
                            <td>#</td>
                            <td>Department</td>
                            <td>Update</td>
                            <td>Delete</td>
                            <td>List of Employees</td>
                        </tr>
                        </thead>
                        <c:if test="${departmentList != null}" var="i=1">
                            <c:forEach items="${departmentList}" var="department">
                                <tr>
                                    <td>${i = i + 1}</td>
                                    <td>${department.departmentName}</td>
                                    <td>
                                        <a href="/department/viewForm?departmentId=${department.departmentId}">Update</a>
                                    </td>
                                    <td><a href="/department/delete?departmentId=${department.departmentId}">Delete</a>
                                    </td>
                                    <td><a href="/employee/viewEmployee?departmentId=${department.departmentId}">Employees</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
