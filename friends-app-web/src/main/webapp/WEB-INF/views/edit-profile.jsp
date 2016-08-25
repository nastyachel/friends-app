<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="_head-with-menu.jsp">
    <jsp:param name="title" value="Profile"/>
</jsp:include>

<h3>${user.firstName} ${user.lastName}</h3>
<h5>Edit profile</h5>

<hr/>

<form class="form-horizontal" role="form" action="<c:url value='/create-hobby'/>" method="post">
    <c:if test="${not empty user.birthday}">
        <div class="form-group">
            <label class="col-xs-2 control-label">Birthday</label>
            <div class="col-xs-10">
                <p class="form-control-static">
                    <fmt:formatDate pattern="dd-MM-yyyy" value="${user.birthday}"/>
                </p>
            </div>
        </div>
    </c:if>

    <div class="form-group">
        <label class="col-xs-2 control-label">Hobbies</label>
        <div class="col-xs-10">
            <c:if test="${not empty user.hobbies}">
            <ul class="items-list">
                <c:forEach var="hobby" items="${user.hobbies}" varStatus="loop">
                <li>
                    <a href="<c:url value="/find-friends-by-hobby?id=${hobby.id}"/>">${hobby.title}</a>
                    <a href="<c:url value="/delete-hobby?id=${hobby.id}"/>" class="btn btn-default btn-sm">Delete</a>
                </li>
                </c:forEach>
            </ul>
            </c:if>
            <div class="row">
                <div class="col-xs-9">
                    <input type="text" name="title" placeholder="Title" class="form-control input-sm"/>
                </div>
                <div class="col-xs-3">
                    <button type="submit" class="btn btn-primary btn-block btn-sm">Add hobby</button>
                </div>
            </div>
        </div>
    </div>
</form>

<jsp:include page="_foot.jsp"/>