<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>SaveEmployee</title>
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
</head>
<body>

<c:import url="navPanel.jsp"/>

<div class="container">

    <h1 class="text-center">Create or Update Employee</h1>

    <form id="defaultForm" method="post" class="form-horizontal col-lg-offset-2" action="/employee/save">
        <input type="hidden" name="employeeId"
               value="<c:out value="${employee.employeeId}"/>">

        <div class="form-group">
            <label class="col-lg-3 control-label">Name</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" name="name"
                       value="<c:out value="${employee.name}"/>">
                <span name="name" class="text-danger">${errorMessageMap.name}</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-3 control-label">Surname</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" name="surname"
                       value="<c:out value="${employee.surname}"/>">
                <span name="surname" class="text-danger">${errorMessageMap.surname}</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-3 control-label">Hire date</label>
            <div class="col-lg-5">
                <input type="date" class="form-control" name="hireDate"
                       value="<c:out value="${param.hireDate}" default="${employee.hireDate}"/>">
                    <span name="hireDate" class="text-danger">${errorMessageMap.hireDate}</span>
            </div>
        </div>


        <div class="form-group">
            <label class="col-lg-3 control-label">Email</label>
            <div class="col-lg-5">
                <input type="email" class="form-control" name="email"
                       value="<c:out value="${employee.email}"/>">
                <span name="email" class="text-danger">${errorMessageMap.email}</span>
            </div>
        </div>


        <div class="form-group">
            <label class="col-lg-3 control-label">Salary</label>
            <div class="col-lg-5">
                <input type="number" class="form-control" name="salary"
                       value="<c:out value="${employee.salary}"/>">
                <span name="salary" class="text-danger">${errorMessageMap.salary}</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-3 control-label">Department</label>
            <div class="col-lg-5">
                <select class="form-control" name="departmentId">
                    <c:forEach items="${departmentList}" var="department">
                        <option
                                <c:if test="${department.departmentId == param.departmentId or department.departmentId == employee.department.departmentId}">
                                    selected
                                </c:if>
                                value="${department.departmentId}">
                                ${department.departmentName}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <span name="departmentId" class="text-danger">${errorMessageMap.departmentId}</span>
        </div>

        <div class="form-group">
            <div class="col-lg-9 col-lg-offset-3">
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </div>
    </form>
</div>


</body>
</html>