<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<portlet:actionURL var="saveEmployee">
    <portlet:param name="action" value="employee/saveEmployee"/>
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

    <form id="defaultForm" method="post" class="form-horizontal col-lg-offset-2" action="${saveEmployee}"
          modelAttribute="employee" name="employee">

        <input type="hidden" name="<portlet:namespace/>id"
               value="<c:out value="${employee.id}"/>">

        <div class="form-group">
            <label class="col-lg-3 control-label">Name</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" name="<portlet:namespace/>name"
                       value="<c:out value="${employee.name}"/>">
                <span name="name" class="text-danger">${errorMessageMap.name}</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-3 control-label">Surname</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" name="<portlet:namespace/>surname"
                       value="<c:out value="${employee.surname}"/>">
                <span name="surname" class="text-danger">${errorMessageMap.surname}</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-3 control-label">Hire date</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" name="<portlet:namespace/>hireDate"
                       value="<c:out value="${param.hireDate}" default="${employee.hireDate}"/>">
                <span name="hireDate" class="text-danger">${errorMessageMap.hireDate}</span>
            </div>
        </div>


        <div class="form-group">
            <label class="col-lg-3 control-label">Email</label>
            <div class="col-lg-5">
                <input type="email" class="form-control" name="<portlet:namespace/>email"
                       value="<c:out value="${employee.email}"/>">
                <span name="email" class="text-danger">${errorMessageMap.email}</span>
            </div>
        </div>


        <div class="form-group">
            <label class="col-lg-3 control-label">Salary</label>
            <div class="col-lg-5">
                <input type="number" class="form-control" name="<portlet:namespace/>salary"
                       value="<c:out value="${employee.salary}"/>">
                <span name="salary" class="text-danger">${errorMessageMap.salary}</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-3 control-label">Department</label>
            <div class="col-lg-5">
                <select class="form-control" name="<portlet:namespace/>department.id">
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
            <span name="department.id" class="text-danger">${errorMessageMap.departmantId}</span>
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