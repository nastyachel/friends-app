<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Friends</title>
</head>
<body>
<jsp:include page="mymenu.jsp"/>
<h1>${user.firstName} ${user.lastName}</h1>
<c:choose>
    <c:when test="${empty user.friends}">
        <p>No friends yet</p>
    </c:when>
    <c:otherwise>
        <ul>
            <c:forEach var="friend" items="${user.friends}">
                <li><a href="<c:url value ='/profile?id=${friend.id}'/>">${friend.firstName} ${friend.lastName}</a></li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>
</body>
</html>