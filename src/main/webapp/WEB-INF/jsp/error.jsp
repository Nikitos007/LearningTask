<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Departments</title>
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
</head>
<body>
<c:import url="navPanel.jsp"/>
<div>
    <div><c:out value="${errorMessage}"/></div>
    <img width="100%" src="<c:url value="/img/pizza.jpg"/>">
</div>

</body>
</html>
