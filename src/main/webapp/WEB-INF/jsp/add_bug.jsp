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

<form style="width:50%" action="${requestScope['javax.servlet.forward.request_uri']}/add" method="post">
    <div class="input-group">
        <label>
            Status:
            <select name="issue_type">
                <c:forEach items="${types}" var="type">
                    <option value="${type}">${type}</option>
                </c:forEach>
            </select>
        </label>
    </div>

    <div class="input-group" style="margin-bottom:10px;">
        <span class="input-group-addon">Title</span>
        <input type="text" class="form-control" name="title" value="">
    </div>

    <div class="input-group" style="margin-bottom:10px;">
        <span class="input-group-addon">Description</span>
        <input type="textarea" value="" name="description" class="form-control">
    </div>

    <div class="input-group" style="margin-bottom:10px;">
        <span class="input-group-addon">Status</span>
            <select class="form-control" name="status">
                <c:forEach items="${statuses}" var="status">
                    <option value="${status}">${status}</option>
                </c:forEach>
            </select>
    </div>
    <div class="input-group" style="margin-bottom:10px;">
        <span class="input-group-addon">Priority</span>
            <select name="priority">
                <c:forEach items="${priorities}" var="priority">
                    <option value="${priority}">${priority}</option>
                </c:forEach>
            </select>
    </div>
    <div class="input-group" style="margin-bottom:10px;">
        <span class="input-group-addon">Assignee</span>
            <select name="responsible_id">
                <c:forEach items="${users}" var="assignee">
                    <option value="${assignee.id}">${assignee.name}</option>
                </c:forEach>
                <option value="0">&lt;&lt;me&gt;&gt;</option>
            </select>
        </label>
    </div>
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
