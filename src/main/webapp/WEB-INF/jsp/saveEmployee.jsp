<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<portlet:actionURL var="saveEmployee">
    <portlet:param name="uri" value="/controller/saveEmployee"/>
</portlet:actionURL>

<html>
<head>
    <title>SaveEmployee</title>
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
</head>
<body>

<c:import url="/WEB-INF/jsp/navPanel.jsp"/>

<div class="container">

    <h1 class="text-center">Create or Update Employee</h1>

    <form id="defaultForm" method="post" class="form-horizontal col-lg-offset-2" action="${saveEmployee}">
        <input type="hidden" name="<portlet:namespace/>employeeId"
               value="<c:out value="${employee.id}"/>">
        <div class="form-group">
            <label class="col-lg-3 control-label">Name</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" name="<portlet:namespace/>employeeName"
                       value="<c:out value="${employee.name}"/>">
                <span name="employeeName" class="text-danger">${errorMessageMap.name}</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-3 control-label">Surname</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" name="<portlet:namespace/>employeeSurname"
                       value="<c:out value="${employee.surname}"/>">
                <span name="employeeSurname" class="text-danger">${errorMessageMap.surname}</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-3 control-label">Hire date</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" name="<portlet:namespace/>employeeHireDate"
                       value="<c:out value="${param.hireDate}" default="${employee.hireDate}"/>">
                <span name="employeeHireDate" class="text-danger">${errorMessageMap.hireDate}</span>
            </div>
        </div>


        <div class="form-group">
            <label class="col-lg-3 control-label">Email</label>
            <div class="col-lg-5">
                <input type="email" class="form-control" name="<portlet:namespace/>employeeEmail"
                       value="<c:out value="${employee.email}"/>">
                <span name="employeeEmail" class="text-danger">${errorMessageMap.email}</span>
            </div>
        </div>


        <div class="form-group">
            <label class="col-lg-3 control-label">Salary</label>
            <div class="col-lg-5">
                <input type="number" class="form-control" name="<portlet:namespace/>employeeSalary"
                       value="<c:out value="${employee.salary}"/>">
                <span name="employeeSalary" class="text-danger">${errorMessageMap.salary}</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-3 control-label">Department</label>
            <div class="col-lg-5">
                <select class="form-control" name="<portlet:namespace/>employeeDepartmentId">
                    <c:forEach items="${departmentList}" var="department">
                        <option
                                <c:if test="${department.id == param.employeeDepartmentId or department.id == employee.department.id}">
                                    selected
                                </c:if>
                                value="${department.id}">
                                ${department.getName()}
                        </option>
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