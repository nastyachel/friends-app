<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="_head-with-title.jsp">
    <jsp:param name="title" value="Login"/>
</jsp:include>

<div class="row">
    <div class="col-xs-12">
        <c:if test="${error}">
        <div class="alert alert-danger">
            <strong>Error!</strong> User with this username and password is not found
        </div>
        </c:if>
    </div>
</div>

<form class="form-horizontal" role="form" action="<c:url value='/submit-login'/>" method="post">
    <div class="form-group">
        <label for="username" class="col-xs-2 control-label">Username</label>
        <div class="col-xs-10">
            <input id="username" name="username" class="form-control" type="text" placeholder="Username"/>
        </div>
    </div>

    <div class="form-group">
        <label for="password" class="col-xs-2 control-label">Password</label>
        <div class="col-xs-10">
            <input id="password" name="password" class="form-control" type="password" placeholder="Password"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-10">
            <button type="submit" class="btn btn-primary btn-block">Login</button>
        </div>
    </div>
</form>

<div class="row">
    <div class="col-xs-offset-2 col-xs-10">
        <a class="btn btn-default btn-block" href="<c:url value='/sign-up'/>">Sign Up</a>
    </div>
</div>

<jsp:include page="_foot.jsp"/>