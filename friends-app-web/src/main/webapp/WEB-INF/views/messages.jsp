<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="_head-with-menu.jsp">
    <jsp:param name="title" value="Messages"/>
</jsp:include>

<h3>${friend.firstName} ${friend.lastName}</h3>
<h5>Messages</h5>

<hr/>

<div class="row">
    <div class="col-xs-12">

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="messages-container pre-scrollable">
                    <c:choose>
                        <c:when test="${empty messages}">
                            <p>No messages yet</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var = "message" items="${messages}">
                                <div class="row">
                                <c:choose>
                                    <c:when test="${message.userFrom.id == friend.id}">
                                        <div class="col-xs-9">
                                            <div class = "panel panel-default message-panel">
                                                <div class = "panel-body message-body">
                                                    <pre>${message.content}</pre>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-3 messages-date-container">
                                            <span class="messages-date"><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${message.timeSent}"/></span>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="col-xs-3 messages-date-container messages-date-container-right-align">
                                            <span class="messages-date"><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${message.timeSent}"/></span>
                                        </div>
                                        <div class="col-xs-9">
                                            <div class = "panel panel-default message-panel message-sent">
                                                <div class = "panel-body message-body">
                                                    <pre>${message.content}</pre>
                                                </div>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

    </div>
</div>


<form action="<c:url value='/create-message'/>" method="post" class="form-horizontal" role="form">
    <div class="form-group">
        <div class="col-xs-10">
            <textarea name="content" placeholder="Your message" class="form-control" rows="1"></textarea>
            <input type="hidden" name="id" value="${friend.id}"/>
        </div>
        <div class="col-xs-2">
            <button type="submit" class="btn btn-primary btn-block">Send</button>
        </div>
    </div>
</form>

<script>
    $(function() {
        var messagesContainer = $('.messages-container');
        messagesContainer.scrollTop(messagesContainer.prop("scrollHeight"));
    });
</script>

<jsp:include page="_foot.jsp"/>