<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FRIENDS APP</title>

</head>
<body>
<div>
    <c:if test="${error}">
        <div>
            Error! User with this username and password is not found
        </div>
    </c:if>
    <form action="<c:url value='/submit-login'/>" method="post">
        <input name="username" type="text" placeholder="username"/>
        <input name="password" type="password" placeholder="password"/>
        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>