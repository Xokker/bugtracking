<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/>" rel="stylesheet">
</head>
<title>Projects</title>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<div class="panel panel-primary" style="margin:1%">
    <div class="panel-heading">Projects</div>
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
    <c:forEach items="${projects}" var="project">
    <form action="<c:url value='/projects/delete'/>" method="post">
    <tr>
        <td><a href="<c:url value='/bugs'> <c:param name="project_id" value="${project.id}"/> </c:url>">${project.name}</a></td>
        <td>${project.description}</td>
        <td>${project.managerId ne null?project.manager.username:''}</td>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" value="${project.name}" name="name"/>
        <td><button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-remove"/></button></td>
    </tr>
    </form>
    </c:forEach>
    </tbody>
</table>
    </div>

<h3 style="margin: 40px 1% 1%;text-align:center;"><span class ="label label-default">Add project</span></h3>
<div style="width:50%;margin-left:25%">
<form name="input" action="/projects/add" method="post">
    <div style="margin-bottom:10px;width:100%" class="input-group">
        <span style="width:16%;text-align: left;" class="input-group-addon">Name</span>
        <input type="text" name="name" class="form-control">
    </div>
    <div style="margin-bottom:10px;width:100%" class="input-group">
        <span style="width:16%;text-align: left;vertical-align: top" class="input-group-addon">Description</span>
        <textarea rows="5" name="description" class="form-control"></textarea>
    </div>
    <div style="margin-bottom:10px;width:100%" class="input-group">
        <span style="width:16%;text-align: left;" class="input-group-addon">Manager</span>
        <select class="form-control" name="manager_id">
        <c:forEach items="${users}" var="user">
            <option value="${user.id}">${user.username}</option>
        </c:forEach>
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
</body>
</html>
