<%@ attribute name="currentUser" required="true" type="com.cheliadina.domain.User" %>
<%@ attribute name="user" required="true" type="com.cheliadina.domain.User" %>
<%@ attribute name="cssClass" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${currentUser.id != user.id}">
    <c:set var="hasFriend" value="false" />
    <c:forEach var="currentUserFriend" items="${currentUser.friends}">
        <c:if test="${currentUserFriend.id == user.id}">
            <c:set var="hasFriend" value="true" />
        </c:if>
    </c:forEach>

    <c:choose>
        <c:when test="${hasFriend}">
            <a href="<c:url value ='/delete-friend?friendId=${user.id}'/>" class="${cssClass}">Unfriend</a>
        </c:when>
        <c:otherwise>
            <a href="<c:url value ='/add-friend?friendId=${user.id}'/>" class="${cssClass}">Add friend</a>
        </c:otherwise>
    </c:choose>
</c:if>
