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
    <c:when test="${currentUserId == user.id}">
        <jsp:include page="create-post.jsp"/>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${isFriend}">
                <a href="<c:url value='/delete-friend?friendId=${user.id}'/>">Unfriend</a>
            </c:when>
            <c:otherwise>
                <a href="<c:url value='/add-friend?friendId=${user.id}'/>">Add friend</a>
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
                    <c:if test="${currentUserId == user.id}">
                        <a href="<c:url value='/delete-post?id=${post.id}'/>">delete</a>
                    </c:if>
                    <span><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${post.timeCreated}"/></span>
                    <p>${post.content}</p>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>