<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="_head-with-menu.jsp">
    <jsp:param name="title" value="Dialogs"/>
</jsp:include>

<h3>${currentUser.firstName} ${currentUser.lastName}</h3>
<h5>Dialogs</h5>
<hr/>

<div class="row">
    <div class="col-xs-12">
        <c:choose>
            <c:when test="${empty dialogs}">
                <p>No dialogs yet</p>
            </c:when>
            <c:otherwise>
                <ul>
                    <c:forEach var="message" items="${dialogs}">
                        <c:choose>
                            <c:when test="${message.userFrom.id == currentUser.id}">
                                <c:set var="friend" value="${message.userTo}"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="friend" value="${message.userFrom}"/>
                            </c:otherwise>
                        </c:choose>
                        <li>
                            <a href="<c:url value='/messages?id=${friend.id}'/>" class="btn btn-block btn-default dialog">
                                <div class="row">
                                    <div class="col-xs-9">
                                        <strong>${friend.firstName} ${friend.lastName}</strong><br/>
                                        <span>${message.content}</span>
                                    </div>
                                    <div class="col-xs-3 messages-date-container messages-date-container-right-align">
                                        <span class="messages-date">
                                            <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${message.timeSent}"/>
                                        </span>
                                    </div>
                                </div>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<jsp:include page="_foot.jsp"/>