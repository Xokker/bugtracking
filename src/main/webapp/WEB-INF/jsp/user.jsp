<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <title>User ${user.username}.</title>
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
    <div class="input-group" style="margin-bottom: 10px;width:100%;">
        <div style="width:7%;text-align: left;" class="input-group-addon">Full name</div>   <input type="text" class="form-control" name="fullName" value="${user.fullName}"/>
    </div>
    <div class="input-group" style="margin-bottom: 10px;width:100%;">
        <div style="width:7%;text-align: left;" class="input-group-addon">E-mail</div>   <input type="text" class="form-control" name="email" value="${user.email}"/>
    </div>
    <div class="input-group" style="margin-bottom: 10px;width:100%;">
        <div style="width:7%;text-align: left;" class="input-group-addon">Password</div>   <input class="form-control" type="password" name="password" value="${user.password}"/>
    </div>
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
