<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Departments</title>
    <link rel="stylesheet" text="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" text="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
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
                                    <td>${department.name}</td>
                                    <td>
                                        <a href="/controller/viewRegistrationDepartmentForm?departmentId=${department.id}">Update</a>
                                    </td>
                                    <td><a href="/controller/deleteDepartment?departmentId=${department.id}">Delete</a>
                                    </td>
                                    <td><a href="/controller/viewDepartment?departmentId=${department.id}">Employees</a>
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
