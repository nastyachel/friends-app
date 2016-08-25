<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="<c:url value='/create-post'/>" method="post" class="form-horizontal" role="form">
    <div class="form-group">
        <div class="col-xs-10">
            <textarea name="content" placeholder="What's on your mind?" class="form-control" rows="1"></textarea>
        </div>
        <div class="col-xs-2">
            <button type="submit" class="btn btn-primary btn-block">Post</button>
        </div>
    </div>
</form>