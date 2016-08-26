<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="_head-with-menu.jsp">
    <jsp:param name="title" value="Friends"/>
</jsp:include>

    <c:choose>
        <c:when test="${type == 'HOBBIES'}">
            <h4>People with hobby ${hobbyTitle}</h4>
        </c:when>
        <c:when test="${type == 'PLACES'}">
            <h4>People with place ${placeTitle}</h4>
        </c:when>
        <c:otherwise>
            <h4>Recommended people</h4>
        </c:otherwise>
    </c:choose>
    <hr/>
<div class="row">
    <div class="col-xs-12">
    <c:choose>
        <c:when test="${empty users}">
            <p>No more people found</p>
        </c:when>
        <c:otherwise>
            <ul class="list-group">
                <c:forEach var="user" items="${users}">
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-xs-10">
                                <a href="<c:url value='/profile?id=${user.id}'/>">${user.firstName} ${user.lastName}</a>
                                <br/>
                                <span><fmt:formatDate pattern="dd-MM-yyyy" value="${user.birthday}"/></span>
                            </div>
                            <div class="col-xs-2">
                                <my:friendButton currentUser="${currentUser}" user="${user}"/>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:otherwise>
    </c:choose>
        </div>
    </div>
</body>
</html>
