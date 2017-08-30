<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="portlet" uri="http://liferay.com/tld/portlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>


<html>
<head>
    <title>Departments</title>
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
</head>
<body>

<c:import url="/WEB-INF/jsp/navPanel.jsp"/>

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
                            <td>Department</td>
                            <td>Update</td>
                            <td>Delete</td>
                            <td>List of Employees</td>
                        </tr>
                        </thead>
                        <c:if test="${departmentList != null}">
                            <c:forEach items="${departmentList}" var="department">

                                <portlet:renderURL var="updateDepartment">
                                    <portlet:param name="uri"
                                                   value="/controller/viewRegistrationDepartmentForm"></portlet:param>
                                    <portlet:param name="departmentId" value="${department.id}"/>
                                </portlet:renderURL>

                                <portlet:actionURL var="deleteDepartment">
                                    <portlet:param name="uri" value="/controller/deleteDepartment"></portlet:param>
                                    <portlet:param name="departmentId" value="${department.id}"/>
                                </portlet:actionURL>

                                <portlet:renderURL var="viewEmployee">
                                    <portlet:param name="uri" value="/controller/viewDepartment"></portlet:param>
                                    <portlet:param name="departmentId" value="${department.id}"/>
                                </portlet:renderURL>

                                <tr>
                                    <td>${department.name}</td>
                                    <td><a href="${updateDepartment}">Update</a></td>
                                    <td><a href="${deleteDepartment}">Delete</a></td>
                                    <td><a href="${viewEmployee}">Employees</a></td>
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
