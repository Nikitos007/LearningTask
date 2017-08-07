<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>About Department</title>
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
</head>
<body>

<c:import url="navPanel.jsp"/>

<div class="container">
    <h1 class="text-center">About Department</h1>
    <div class="panel panel-info">
        <div class="panel-body">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <table class="table table table-hover">
                        <tbody>
                        <thead>
                        <tr>
                            <td>#</td>
                            <td>Name</td>
                            <td>Surname</td>
                            <td>Hire Date</td>
                            <td>Email</td>
                            <td>Salary</td>
                            <td>Update</td>
                            <td>Delete</td>
                        </tr>
                        </thead>
                        <c:if test="${employeeList != null}" var="i=1">
                            <c:forEach items="${employeeList}" var="employee">
                                <tr>
                                    <td>${i = i + 1}</td>
                                    <td>${employee.getName()}</td>
                                    <td>${employee.getSurname()}</td>
                                    <td>${employee.getHireDate()}</td>
                                    <td>${employee.getEmail()}</td>
                                    <td>${employee.getSalary()}</td>
                                    <td>
                                        <a href="/controller/viewRegistrationEmployeeForm?employeeId=${employee.getId()}">Update</a>
                                    </td>
                                    <td>
                                        <a href="/controller/deleteEmployee?employeeId=${employee.getId()}">Delete</a>
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
