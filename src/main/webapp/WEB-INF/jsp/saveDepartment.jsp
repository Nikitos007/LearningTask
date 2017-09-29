<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>SaveDepartment</title>
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
</head>
<body>

<c:import url="navPanel.jsp"/>

<div class="container">
    <h1 class="text-center">Create or Update Department</h1>


    <form id="defaultForm" method="post" class="form-horizontal col-lg-offset-2" action="/department/save">
        <input type="hidden" name="departmentId"
               value="<c:out value="${param.departmentId == null ? department.departmentId : param.departmentId}"/>">

        <div class="form-group">
            <label class="col-lg-3 control-label">Name</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" name="departmentName"
                       value="<c:out value="${param.departmentName == null ? department.departmentName : param.departmentName}"/>"/>
                <span name="departmentName" class="text-danger">${errorMessageMap.departmentName}</span>
            </div>
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