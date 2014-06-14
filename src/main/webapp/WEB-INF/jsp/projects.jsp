<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <title>Bug Tracking</title>
</head>
<title>Issues</title>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<form name="input" action="/projects/add" enctype="application/x-www-form-urlencoded; charset=utf-8" method="post">
<table class="table table-hover" style="width:98%; margin:1%">
    <thead>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Manager</th>
        <th>Operation</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><a href="#">Bugtracker</a></td>
        <td>Создание багтрекера</td>
        <td>esadykov</td>
        <td><button type="button" class="btn btn-default"><span class="glyphicon glyphicon-delete"/></button></td>
    </tr>
    <tr>
        <td><a href="#">LPF</a></td>
        <td>Поездка на ЛПФ</td>
        <td>gkozhukhantsev</td>
        <td><button type="button" class="btn btn-default"><span class="glyphicon glyphicon-delete"/></button></td>
    </tr>
    <tr>
        <td><a href="#">Exams</a></td>
        <td>Подготовка к сессии</td>
        <td>agalaev</td>
        <td><button type="button" class="btn btn-default"><span class="glyphicon glyphicon-delete"/></button></td>
    </tr>
    <tr>
        <td>
            <input type="text" class="form-control" placeholder="Name">
        </td>
        <td>
            <input type="text" class="form-control" placeholder="Description">
        </td>
        <td>
                <select name="responsible_id">
                    <c:forEach items="${users}" var="user">
                        <option value="${user.id}">${user.username}</option>
                    </c:forEach>
                </select>
        </td>
        <td><button type="button" class="btn btn-default"><span class="glyphicon glyphicon-add"/></button></td>
    </tr>
    </tbody>
</table>
    </form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
