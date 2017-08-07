<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>SaveEmployee</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>

<c:import url="navPanel.jsp"/>

<div class="container">

    <h1 class="text-center">New Employee</h1>

    <form id="defaultForm" method="post" class="form-horizontal col-lg-offset-2" action="/controller/saveEmployee">
        <input type="hidden" name="employeeId"
               value="<c:out value="${param.employeeId == null ? employee.id : param.employeeId}"/>">
        <div class="form-group">
            <label class="col-lg-3 control-label">Name</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" name="employeeName"
                       value="<c:out value="${param.employeeName == null ? employee.name : param.employeeName}"/>">
                <span name="employeeName" class="text-danger">${errorMessageMap.name}</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-3 control-label">Surname</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" name="employeeSurname"
                       value="<c:out value="${param.employeeSurname == null ? employee.surname : param.employeeSurname}"/>">
                <span name="employeeSurname" class="text-danger">${errorMessageMap.surname}</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-3 control-label">Hire date</label>
            <div class="col-lg-5">
                <input type="date" class="form-control" name="employeeHireDate"
                       value="<c:out value="${param.employeeHireDate == null ? employee.hireDate : param.employeeHireDate}" />">
                <span name="employeeHireDate" class="text-danger">${errorMessageMap.hireDate}</span>
            </div>
        </div>


        <div class="form-group">
            <label class="col-lg-3 control-label">Email</label>
            <div class="col-lg-5">
                <input type="email" class="form-control" name="employeeEmail"
                       value="<c:out value="${param.employeeEmail == null ? employee.email : param.employeeEmail}"/>">
                <span name="employeeEmail" class="text-danger">${errorMessageMap.email}</span>
            </div>
        </div>


        <div class="form-group">
            <label class="col-lg-3 control-label">Salary</label>
            <div class="col-lg-5">
                <input type="number" class="form-control" name="employeeSalary"
                       value="<c:out value="${param.employeeSalary == null ? employee.salary : param.employeeSalary}"/>">
                <span name="employeeSalary" class="text-danger">${errorMessageMap.salary}</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-3 control-label">Department</label>
            <div class="col-lg-5">
                <select class="form-control" name="employeeDepartmentId">
                    <c:forEach items="${departmentList}" var="department">
                        <c:choose>
                            <c:when test="${department.id == param.employeeDepartmentId}">
                                <option selected
                                        value="<c:out value="${param.employeeDepartmentId}"/>">${department.getName()}</option>
                            </c:when>
                            <c:when test="${department.id == employeeDepartment.id}">
                                <option selected
                                        value="<c:out value="${department.id}"/>">${department.getName()}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="<c:out value="${department.id}"/>">${department.getName()}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <span name="employeeDepartmentId" class="text-danger">${errorMessageMap.departmantId}</span>
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