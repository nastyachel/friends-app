<%@ attribute name="currentUser" required="true" type="com.cheliadina.domain.User" %>
<%@ attribute name="user" required="true" type="com.cheliadina.domain.User" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<a href="<c:url value ='/profile?id=${user.id}'/>">${user.firstName} ${user.lastName}</a>
<my:friendButton currentUser="${currentUser}" user="${user}"/>


