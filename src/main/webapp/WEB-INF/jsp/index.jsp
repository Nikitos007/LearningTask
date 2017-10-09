<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="/js/main.js"></script>
</head>

<body onload="controller.doAction('department/viewAll')">

<c:import url="navPanel.jsp"/>

<div class="container" id="wrapper"></div>

</body>
</html>
