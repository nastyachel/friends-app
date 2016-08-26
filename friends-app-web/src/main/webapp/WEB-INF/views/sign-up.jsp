<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="_head-with-title.jsp">
    <jsp:param name="title" value="Sign Up"/>
</jsp:include>

<div class="row">
    <div class="col-xs-12">
        <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger">
                <strong>Error!</strong> ${errorMsg}
            </div>
        </c:if>
    </div>
</div>

<form class="form-horizontal" role="form" action="<c:url value='/submit-sign-up'/>" method="post">
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
        <label for="firstName" class="col-xs-2 control-label">First name</label>
        <div class="col-xs-10">
            <input id="firstName" name="firstName" class="form-control" type="text" placeholder="First name"/>
        </div>
    </div>
    <div class="form-group">
        <label for="lastName" class="col-xs-2 control-label">Last name</label>
        <div class="col-xs-10">
            <input id="lastName" name="lastName" class="form-control" type="text" placeholder="Last name"/>
        </div>
    </div>
    <div class="form-group">
        <label for="birthday" class="col-xs-2 control-label">Birthday</label>
        <div class="col-xs-10">
            <input id="birthday" name="birthday" class="form-control" type="text" placeholder="Birthday"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-10">
            <button type="submit" class="btn btn-primary btn-block">Sign Up</button>
        </div>
    </div>
</form>

<jsp:include page="_foot.jsp"/>