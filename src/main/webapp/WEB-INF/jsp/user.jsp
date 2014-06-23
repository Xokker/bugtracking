<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/>" rel="stylesheet">
    <title>User ${user.username}.</title>
    <style type="text/css">
        .edit-field {
            width: 7%;
            text-align: left;
        }
        .ig {
            margin-bottom: 10px;
            width: 100%;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/navigation.jsp"/>
<div style="width:98%;margin:1%">
<h2><span class="label label-default">User ${user.username}.</span></h2>
<form name="input" action="${requestScope['javax.servlet.forward.request_uri']}" method="post">
    <div class="input-group"  style="margin-top: 20px;margin-bottom: 10px;width:100%;">
        <div style="width:7%;text-align: left;" class="input-group-addon">Username</div>
        <input type="text" class="form-control" name="username" value="${user.username}" disabled/>
    </div>
    <div class="input-group ig">
        <div class="input-group-addon edit-field">Full name</div>   <input type="text" class="form-control" name="fullName" value="${user.fullName}"/>
    </div>
    <div class="input-group ig">
        <div class="input-group-addon edit-field">E-mail</div>   <input type="text" class="form-control" name="email" value="${user.email}"/>
    </div>
    <div class="input-group ig">
        <div class="input-group-addon edit-field">Password</div>   <input class="form-control" type="password" name="password" value="${user.password}"/>
    </div>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="input-group ig">
            <div class="input-group-addon edit-field">Role</div>
            <select name="admin">
                <option value="false">User</option>
                <option value="true" <c:if test="${user.admin}">selected="true"</c:if>>Admin</option>
            </select>
        </div>
    </sec:authorize>
        <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <input type="hidden" name="backUrl" value="${backUrl}"/>
    <input type="submit" class="btn btn-default" value="Submit"/>
</form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
