<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="<c:url value='/create-post'/>" method="post">
    <div><textarea name="content" placeholder="What's on your mind?"></textarea></div>
    <div> <button type="submit">Create post</button></div>
</form>