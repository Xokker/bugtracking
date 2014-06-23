<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

    <title>Bugs Bunny</title>
    <style>
        .error {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }

        .msg {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #31708f;
            background-color: #d9edf7;
            border-color: #bce8f1;
        }

    </style>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/>" rel="stylesheet">
</head>
<body style=" overflow-y:visible;margin-left:37%;margin-top:10%" onload='document.loginForm.username.focus();'>

<h3><span class="label label-default">Please, Login BugsBunny.  </span></h3>
<c:if test="${not empty error}">
    <div style="width:346px;" class="error">${error}</div>
</c:if>
<c:if test="${not empty msg}">
    <div style="width:346px;" class="msg">${msg}</div>
</c:if>
<div style="width:346px; height:307px;background-image: url('/resources/img/logo.png') " class="panel panel-default">
    <div class="panel-body">




    <form name='loginForm'
          action="<c:url value='j_spring_security_check'/>" method='POST'>


        <div style="margin-top:-5px;" class="input-group">
            <span class="input-group-addon">Username:</span>
            <input type="text" name='username' value='' class="form-control" placeholder="Username">
        </div>
        <div style="margin-top:10px" class="input-group">
            <span class="input-group-addon">Password:</span>
            <input type="password" name='password' value='' class="form-control" placeholder="Password">
        </div>
        <input class="btn btn-default"  style=" margin-top:170px ;margin-left:40%" name="submit" type="submit" value="submit"/>

        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
        </form>
</div>
</div>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>