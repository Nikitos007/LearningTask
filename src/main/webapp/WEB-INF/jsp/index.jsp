<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <%--<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>--%>

    <%--<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>--%>
    <%--<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/additional-methods.min.js"></script>--%>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.js"></script>


    <script src="/js/main.js"></script>
</head>

<body onload="controller.doAction('department/viewAll')">

<c:import url="navPanel.jsp"/>

<div class="container" id="wrapper"></div>

</body>
</html>

