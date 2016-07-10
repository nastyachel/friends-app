<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>FRIENDS APP</title>
</head>
<body>
<c:choose>
    <c:when test="${logged}">
        <a href="<c:url value='/logout'/>">Logout</a>
    </c:when>
    <c:otherwise>
        <a href="<c:url value='/login'/>">Login</a>
    </c:otherwise>
</c:choose>

</body>
</html>