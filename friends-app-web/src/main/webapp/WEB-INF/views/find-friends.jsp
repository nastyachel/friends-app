<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Find friends</title>
</head>
<body>
<jsp:include page="mymenu.jsp"/>
<div>
    <ul>
    <c:forEach var="user" items="${users}">
        <li>
            <a href="/profile?id=${user.id}">${user.firstName} ${user.lastName}</a>
            <a href="/add-friend?friendId=${user.id}">Add friend</a>
        </li>
    </c:forEach>
    </ul>
</div>
</body>
</html>
