<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <title>Bug Tracking</title>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<table style="margin:1.5%; width:97%">
    <tr style="overflow:visible">
        <td style="width:50%;">
<div style="margin-right:5%; height:285px; overflow-y:auto" class="panel panel-primary">
    <div class="panel-heading">Open Projects</div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Issues</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${projects}" var="project">
        <tr>
            <td><a href="<c:url value='/bugs'><c:param name="project_id" value="${project.id}"/> </c:url>">${project.name}</a></td>
            <td>${project.description}</td>
            <td>${project.bugCount}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
    </td>
     <td style="width:50%;">
         <div style="margin-left:5%; height:285px; overflow-y:auto" class="panel panel-primary">
         <div class="panel-heading">Latest Issues</div>
         <table class="table table-hover">
             <thead>
             <tr>
                 <th>Name</th>
                 <th>Project</th>
                 <th>Priority</th>
                 <th>Type</th>
                 <th>Assignee</th>
             </tr>
             </thead>
             <tbody>
             <c:forEach items="${bugs}" var="bug">
             <tr>
                 <td><a href="../bug/${bug.id}">${bug.title}</a></td>
                 <td>${bug.project.name}</td>
                 <td>${bug.priority}</td>
                 <td>${bug.issueType}</td>
                 <td>${bug.responsible.username}</td>
             </tr>
             </c:forEach>
             </tbody>
         </table>
     </div>
     </td>
    </tr>
    <tr>
     <td colspan="2">
         <div class="panel panel-primary">
             <div class="panel-heading">Comments</div>
             <table class="table table-hover">
                 <thead>
                 <tr>
                     <th>Author</th>
                     <th>Bug</th>
                     <th>Date</th>
                     <th>Comment</th>
                 </tr>
                 </thead>
                 <tbody>
                 <c:forEach items="${comments}" var="comment">
                 <tr>
                     <td>${comment.author.username}</td>
                     <td><a href="../bug/${comment.bug.id}">${comment.bug.title}</a></td>
                     <td><fmt:formatDate type="both" dateStyle="short"
                                                     pattern="dd.MM.yyyy HH:mm"
                                                     value="${comment.bug.created}"/></td>
                     <td>${comment.body}</td>
                 </tr>
                 </c:forEach>
                 </tbody>
             </table>
         </div>
     </td>
    </tr>
</table>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
