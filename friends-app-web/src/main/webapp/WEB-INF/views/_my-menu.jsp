<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="list-inline my-menu">
    <li><a href="<c:url value='/profile'/>">My profile</a></li>
    <li><a href="<c:url value='/dialogs'/>">Dialogs</a></li>
    <li><a href="<c:url value='/find-friends'/>">Find friends</a></li>
    <li><a href="<c:url value='/edit-profile'/>">Edit profile</a></li>
    <li><a href="<c:url value='/logout'/>">Logout</a></li>
</ul>