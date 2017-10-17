<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.js"></script>
    <script src="/js/bundle.js"></script>
</head>

<body onload="department.controller.doAction('department/viewAll')">

<c:import url="navPanel.jsp"/>

<div class="container" id="wrapper"></div>

</body>
</html>

