<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Friends</title>
</head>
<body>
<jsp:include page="_my-menu.jsp"/>
<h1>${user.firstName} ${user.lastName}</h1>
<c:choose>
    <c:when test="${empty user.friends}">
        <p>No friends yet</p>
    </c:when>
    <c:otherwise>
        <ul>
            <c:forEach var="friend" items="${user.friends}">
                <li>
                    <my:friendWithButton currentUser="${currentUser}" user="${friend}"/>
                </li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>
</body>
</html>
