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
            <a href="<c:url value='/'/>">Unfriend</a>
          </c:when>
          <c:otherwise>
              <a href="<c:url value='/addFriend?userId=${currentId}&friendId=${user.id}'/>">Add friend</a>
          </c:otherwise>
      </c:choose>
    </c:if>
</body>
</html>