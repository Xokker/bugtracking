<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <title>Add bug</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/navigation.jsp"/>

<form action="${requestScope['javax.servlet.forward.request_uri']}/add" method="post">
    <div class="input-group">
        <label>
            Status:
            <select name="type_id">
                <c:forEach items="${types}" var="type">
                    <option selected="false" value="${type.name}">${type.name}</option>
                </c:forEach>
            </select>
        </label>
    </div>

    <div class="input-group">
        <span class="input-group-addon">Name</span>
        <input type="text" class="form-control" value="">
    </div>

    <div class="input-group">
        <span class="input-group-addon">Description</span>
        <input type="textarea" value="" class="form-control">
    </div>

    <div class="input-group">
        <label>
            Status:
            <select name="status_id">
                <c:forEach items="${statuses}" var="status">
                    <option value="${status.name}">${status.name}</option>
                </c:forEach>
            </select>
        </label>
    </div>
    <div class="input-group">
        <label>
            Priority:
            <select name="priority_id">
                <c:forEach items="${priorities}" var="priority">
                    <option value="${priority.name}">${priority.name}</option>
                </c:forEach>
            </select>
        </label>
    </div>
    <div class="input-group">
        <label>
            Assignee:
            <select name="priority_id">
                <c:forEach items="${users}" var="assignee">
                    <option value="${assignee.id}">${assignee.name}</option>
                </c:forEach>
                <option value="${user.id}">Assign on me</option>
            </select>
        </label>
    </div>
    <p>
        <!--TODO: current time get--><strong>Created:</strong> <fmt:formatDate type="both"
                                                  dateStyle="short"
                                                  pattern="dd.MM.yyyy HH:mm"
                                                  value="${bug.created}"/>
    </p>
    <p>
        <strong>Created by:</strong> ${user.username}
    </p>
    <hr>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" value="${bug.id}" name="id"/>
    <input type="submit" class="btn btn-default" value="Add bug"/>
</form>

</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
