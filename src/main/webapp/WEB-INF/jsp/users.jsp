<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/>" rel="stylesheet">
    <title>Users</title>
    <style>
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
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/navigation.jsp"/>
<div style="width:98%;margin:1%">
    <c:if test="${not empty message}">
        <div style="width:346px;" class="msg">${message}</div>
    </c:if>


    <div class="panel panel-primary">
        <div class="panel-heading">Users</div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Username</th>
            <th>Full name</th>
            <th>E-mail</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <form action="<c:url value='/users/delete'/>" method="post">
                <tr>
                    <td><a href="<c:url value='/users/update/${user.id}'></c:url>">${user.username}</a></td>
                    <td>${user.fullName}</td>
                    <td>${user.email}</td>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" value="${user.username}" name="username"/>
                    <td><button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-remove"/></button></td>
                </tr>
            </form>
        </c:forEach>
        </tbody>
    </table>
</div>
    <hr/>
        <h3 style="margin: 40px 1% 1%;text-align:center;"><span class ="label label-default">Add User</span></h3>
        <div style="width:50%;margin-left:25%">
            <form name="input" action="/users/add" method="post">
                <div style="margin-bottom:10px;width:100%" class="input-group">
                    <span style="width:16%;text-align: left;" class="input-group-addon">Username</span>
                    <input type="text" autocomplete="false" name="username" class="form-control">
                </div>
                <div style="margin-bottom:10px;width:100%" class="input-group">
                    <span style="width:16%;text-align: left;vertical-align: top" class="input-group-addon">Full name</span>
                    <input type="text" autocomplete="false" name="full_name" class="form-control">
                </div>
                <div style="margin-bottom:10px;width:100%" class="input-group">
                    <span style="width:16%;text-align: left;" class="input-group-addon">E-mail</span>
                    <input type="email" autocomplete="false" name="email" class="form-control">
                </div>
                <div style="margin-bottom:10px;width:100%" class="input-group">
                    <span style="width:16%;text-align: left;" class="input-group-addon">Password</span>
                    <input type="password" autocomplete="false" name="password" class="form-control">
                </div>
                <div style="margin-bottom:10px;width:100%" class="input-group">
                    <span style="width:16%;text-align: left;" class="input-group-addon">Role</span>
                    <select name="admin" class="form-control">
                        <option value="true">Admin</option>
                        <option value="false">User</option>
                    </select>
                </div>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
                <input style="margin-left:45%" type="submit" class="btn btn-default" value="Add"/>
            </form>
        </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/resources/js/bootstrap.min.js"></script>
        </div>
</body>
</html>
