<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>FRIENDS APP</title>

    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
    <script src="<c:url value="/resources/js/bootstrap.js" />"></script>
</head>
<body>

<div class="row">
    <div class="container col-md-2 col-md-offset-5">
        <c:if test="${error}">
            <div class="alert alert-danger">
                <strong>Error!</strong> User with this username and password is not found
            </div>
        </c:if>
        <form action="<c:url value='/submit-login'/>" method="post">
            <div class="form-group">
                <label for="username" class="text-center">Username</label>
                <input id="username" name="username" type="text" placeholder="username"/>
            </div>
            <div class="form-group">
                <label for="password" class="text-center">Password</label>
                <input id="password" name="password" type="password" placeholder="password"/>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block">Login</button>
            </div>
        </form>

    </div>
</div>
</body>
</html>