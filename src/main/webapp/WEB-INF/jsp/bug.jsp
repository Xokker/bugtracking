<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <title>Bug #${bug.id}. ${bug.title}</title>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/navigation.jsp"/>

    <h3><span class="label label-default">#${bug.title}</span></h3>
    <form action="${requestScope['javax.servlet.forward.request_uri']}/edit" method="post">
    <div class="input-group">
        <span class="input-group-addon">Type</span>
        <input type="text" disabled class="form-control" value="${bug.type}">
    </div>

    <div class="input-group">
        <span class="input-group-addon">Name</span>
        <input type="text" disabled class="form-control" value="${bug.name}">
    </div>

    <div class="input-group">
        <span class="input-group-addon">Description</span>
        <input type="textarea" disabled value="${bug.description}" class="form-control">
    </div>

        <div class="input-group">
            <label>
                Status:
                <select name="status_id">
                    <c:forEach items="${statuses}" var="status">
                        <option selected="${status.id==bug.status}" value="${status.id}">${status.name}</option>
                    </c:forEach>
                </select>
            </label>
        </div>
        <div class="input-group">
            <label>
                Priority:
                <select name="priority_id">
                    <c:forEach items="${priorities}" var="priority">
                        <option selected="${priority.id==bug.priority}" value="${priority.id}">${priority.name}</option>
                    </c:forEach>
                </select>
            </label>
        </div>
        <div class="input-group">
            <label>
                Related bugs:
                <select name="bugs" size="5">
                    <c:forEach items="${bugs}" var="dep_bug">
                        <option value="${dep_bug.id}">${dep_bug.name}</option>
                    </c:forEach>
                </select>
            </label>

        </div>
    <p>
        <strong>Created:</strong> <fmt:formatDate type="both"
                                dateStyle="short"
                                pattern="dd.MM.yyyy HH:mm"
                                value="${bug.created}"/>
    </p>
    <c:if test="${bug.status ne 'NEW'}">
        <p>
            <strong>Closed:</strong> <fmt:formatDate type="both"
                                                      dateStyle="short"
                                                      pattern="dd.MM.yyyy HH:mm"
                                                      value="${bug.closed}"/>
        </p>
    </c:if>
    <p>
        <strong>Created by:</strong> ${bug.creator.username}
    </p>
    <p>
         <strong>Responsible:</strong> ${bug.responsible.username}
    </p>
    <hr>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" value="${bug.id}" name="id"/>
        <input type="submit" value="Edit bug"/>
    </form>

    <hr>
    <h2>Comments</h2>
    <c:forEach items="${comments}" var="comment">
        <p>
            <strong>Author id:</strong> ${comment.author.username}.
            Added: <fmt:formatDate type="both"
                                   dateStyle="short"
                                   pattern="dd.MM.yyyy HH:mm"
                                   value="${comment.created}"/>
        </p>
        <p><c:out value="${comment.body}" escapeXml="false"/></p>
        <hr>
    </c:forEach>

    <h3>Add comment</h3>
    <form name="input" action="${requestScope['javax.servlet.forward.request_uri']}" method="post">
        Username:   <input type="text" name="username"/>     <br/>
        Text:       <textarea rows="5" cols="50" name="body" placeholder="Enter your comment"></textarea>    <br/>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <input type="submit" value="Submit"/>
    </form>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
