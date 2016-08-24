<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit profile</title>
</head>
<body>
<jsp:include page="mymenu.jsp"/>
<h1>${user.firstName} ${user.lastName}</h1>
<div>
    <h3>Hobbies</h3>

    <div>
        <ul>
            <c:forEach var="hobby" items="${user.hobbies }">
                <li>
                    <a href="<c:url value="/find-friends-by-hobby?id=${hobby.id}"/>">${hobby.title}</a>
                    <a href="<c:url value="/delete-hobby?id=${hobby.id}"/>">delete</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div>
        <form action="<c:url value='/create-hobby'/>" method="post">
            <div>
                <input type="text" name="title" placeholder="Title"/>
            </div>
            <div>
                <button type="submit">Create hobby</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>