<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="_head-with-menu.jsp">
    <jsp:param name="title" value="Profile"/>
</jsp:include>

<div class="row">
    <div class="col-xs-6">
        <h3>${user.firstName} ${user.lastName}</h3>
        <h5>Profile</h5>
    </div>
    <div class="col-xs-2">
        <my:friendButton currentUser="${currentUser}" user="${user}"/>
    </div>
    <div class="col-xs-2">
        <c:if test="${user.id!=currentUser.id}">
        <a href="<c:url value='/messages?id=${user.id}'/>" class="btn btn-default btn-block">Message</a>
        </c:if>
    </div>
    <div class="col-xs-2">
        <a href="<c:url value='/friends?id=${user.id}'/>" class="btn btn-default btn-block">Friends</a>
    </div>
</div>

<hr/>

<form class="form-horizontal" role="form">
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


    <c:if test="${not empty user.hobbies}">
    <div class="form-group">
        <label class="col-xs-2 control-label">Hobbies</label>
        <div class="col-xs-10">
            <p class="form-control-static">
                <c:forEach var="hobby" items="${user.hobbies}" varStatus="loop">
                <span>
                    <a href="<c:url value='/find-friends-by-hobby?id=${hobby.id}'/>">${hobby.title}</a><c:if test="${not loop.last}">, </c:if>
                </span>
                </c:forEach>
            </p>
        </div>
    </div>
    </c:if>


    <c:if test="${not empty user.places}">
        <div class="form-group">
            <label class="col-xs-2 control-label">Places</label>
            <div class="col-xs-10">
                <p class="form-control-static">
                    <c:forEach var="place" items="${user.places}" varStatus="loop">
                <span>
                    <a href="<c:url value='/find-friends-by-place?id=${place.id}'/>">${place.title}</a><c:if test="${not loop.last}">, </c:if>
                </span>
                    </c:forEach>
                </p>
            </div>
        </div>
    </c:if>
</form>

<c:if test="${currentUser.id == user.id || not empty user.posts}">
    <hr/>
</c:if>

<c:if test="${currentUser.id == user.id}">
    <jsp:include page="_create-post.jsp"/>
</c:if>

<c:if test="${not empty postsReverse}">
    <div class="row">
        <div class="col-xs-12">
        <c:forEach var = "post" items="${postsReverse}">
            <div class = "panel panel-default">
                <div class = "panel-heading">
                    <div class="row">
                        <div class="col-xs-10">
                            <h3 class = "panel-title">${user.firstName} ${user.lastName}</h3>
                            <span><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${post.timeCreated}"/></span>
                        </div>
                        <div class="col-xs-2">
                            <c:if test="${currentUser.id == user.id}">
                                <a href="<c:url value='/delete-post?id=${post.id}'/>" class="btn btn-block btn-default btn-sm">Delete</a>
                            </c:if>
                        </div>
                    </div>
                </div>

                <div class = "panel-body">
                    <pre class="post-content">${post.content}</pre>
                    <div class="row">
                        <div class="col-xs-2">
                            <c:set var="currentUserLikedPost" value="false" />
                            <c:set var="usersLikedString" value="" />
                            <c:forEach var="userLiked" items="${post.usersLiked}" varStatus="loop">
                                <c:set var="usersLikedString"
                                       value="${usersLikedString}${loop.first ? '' : ', '}${userLiked.firstName} ${userLiked.lastName}" />
                                <c:if test="${userLiked.id == currentUser.id}">
                                    <c:set var="currentUserLikedPost" value="true" />
                                </c:if>
                            </c:forEach>
                            <a href="<c:url value='/like-post?postId=${post.id}&postOwnerId=${user.id}'/>"
                               class="btn ${currentUserLikedPost ? 'btn-primary' : 'btn-default'}"
                               title="${usersLikedString}">
                                Like <span class="badge">${fn:length(post.usersLiked)}</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        </div>
    </div>
</c:if>

<jsp:include page="_foot.jsp"/>