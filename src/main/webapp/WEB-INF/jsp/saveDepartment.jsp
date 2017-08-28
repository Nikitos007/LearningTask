<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<portlet:actionURL var="saveDepartment">
    <portlet:param name="uri" value="/controller/saveDepartment"/>
</portlet:actionURL>

<html>
<head>
    <title>SaveDepartment</title>
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
</head>
<body>

<c:import url="/WEB-INF/jsp/navPanel.jsp"/>

<div class="container">
    <h1 class="text-center">Create or Update Department</h1>


    <form id="defaultForm" method="post" class="form-horizontal col-lg-offset-2" action="${saveDepartment}">
        <input type="hidden" name="<portlet:namespace/>departmentId"
               value="<c:out value="${param.departmentId == null ? department.id : param.departmentId}"/>">

        <div class="form-group">
            <label class="col-lg-3 control-label">Name</label>
            <div class="col-lg-5">
                <input type="text" class="form-control" name="<portlet:namespace/>departmentName"
                       value="<c:out value="${param.departmentName == null ? department.name : param.departmentName}"/>"/>
                <span name="name" class="text-danger">${errorMessageMap.name}</span>
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