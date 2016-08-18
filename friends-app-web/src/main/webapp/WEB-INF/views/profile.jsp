<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Profile</title>
</head>
<body>
    <jsp:include page="mymenu.jsp"/>
    <h1>${user.firstName} ${user.lastName}</h1>
    <c:if test="${currentId != user.id}">
      <c:choose>
          <c:when test="${isFriend}">
            <a href="<c:url value='/removeFriend?id=${currentId}&friendId=${user.id}'/>">Unfriend</a>
          </c:when>
          <c:otherwise>
              <a href="<c:url value='/addFriend?id=${currentId}&friendId=${user.id}'/>">Add friend</a>
          </c:otherwise>
      </c:choose>
    </c:if>
    <a href="<c:url value='/friends?id=${user.id}'/>">Friends</a>
</body>
</html>