<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="_head-with-menu.jsp">
    <jsp:param name="title" value="Friends"/>
</jsp:include>

<h3>${user.firstName} ${user.lastName}</h3>
<hr/>
<div class="row">
    <div class="col-xs-12">
    <c:choose>
        <c:when test="${empty user.friends}">
            <p>No friends yet</p>
        </c:when>
        <c:otherwise>
            <ul class="list-group">
                <c:forEach var="friend" items="${user.friends}">
                    <li class="list-group-item">
                        <div class="row list-item">
                            <div class="col-xs-10">
                                <a href="<c:url value='/profile?id=${friend.id}'/>">
                                    ${friend.firstName} ${friend.lastName}
                                </a>
                                <br/>
                                <span><fmt:formatDate pattern="dd-MM-yyyy" value="${friend.birthday}"/></span>
                            </div>
                            <div class="col-xs-2">
                                <my:friendButton currentUser="${currentUser}" user="${friend}"/>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:otherwise>
    </c:choose>
    </div>
</div>

<jsp:include page="_foot.jsp"/>