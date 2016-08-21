<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Profile</title>
</head>
<body>
<jsp:include page="mymenu.jsp"/>
<h1>${user.firstName} ${user.lastName}</h1>
<a href="<c:url value='/friends?id=${user.id}'/>">Friends</a>
<c:choose>
    <c:when test="${currentId == user.id}">
        <jsp:include page="create-post.jsp"/>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${isFriend}">
                <a href="<c:url value='/removeFriend?id=${currentId}&friendId=${user.id}'/>">Unfriend</a>
            </c:when>
            <c:otherwise>
                <a href="<c:url value='/addFriend?id=${currentId}&friendId=${user.id}'/>">Add friend</a>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
<div>
    <ul>
        <c:forEach var = "post" items="${user.posts}">
            <li>
                <div>
                    <h4>${user.firstName} ${user.lastName}</h4>

                    <span><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${post.timeCreated}"/></span>
                    <p>${post.content}</p>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>