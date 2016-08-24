<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>Find friends</title>
</head>
<body>
<jsp:include page="_my-menu.jsp"/>
<div>
    <c:choose>
        <c:when test="${type == 'HOBBIES'}">
            <h3>People with hobby ${hobbyTitle}</h3>
        </c:when>
        <c:when test="${type == 'PLACES'}">
            <%--todo add title for places case--%>
        </c:when>
        <c:otherwise>
            <h3>Recommended people</h3>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${empty users}">
            <span>No more people found</span>
        </c:when>
        <c:otherwise>
            <ul>
                <c:forEach var="user" items="${users}">
                    <li>
                        <my:friendWithButton currentUser="${currentUser}" user="${user}"/>
                    </li>
                </c:forEach>
            </ul>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
