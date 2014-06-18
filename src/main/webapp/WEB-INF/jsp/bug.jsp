<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <title>Bug ${bug.title}</title>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/navigation.jsp"/>
<div style="width:98%;margin:1%">
<table>
    <tr>
        <td style="vertical-align: top;"><h3><span class="label label-default">#${bug.title}</span></h3></td>
        <td style="vertical-align: bottom;"><form action="bug/${bug.id}/observer" method="post">
            <input type="hidden" name="is_add" value="${not is_current_user_observer}"/>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <button  style="margin-top:15px; width:35px;height:29px;" type="submit" class=" label label-default btn"><span class="glyphicon glyphicon-eye-${is_current_user_observer?"close":"open"}"></span></button>
        </form></td>
    </tr>
</table>


    <form action="${requestScope['javax.servlet.forward.request_uri']}/edit" method="post">

        <div class="input-group"style="margin-bottom: 10px">
            <span class="input-group-addon">Type</span>
            <input type="text" disabled class="form-control" value="${bug.issueType}">
        </div>

    <div class="input-group" style="margin-bottom: 10px">
        <span class="input-group-addon">Name</span>
        <input type="text" disabled class="form-control" value="${bug.title}">
    </div>

    <div class="input-group" style="margin-bottom: 10px">
        <span class="input-group-addon">Description</span>
        <input type="textarea" disabled value="${bug.description}" class="form-control">
    </div>

        <div class="input-group" style="margin-bottom: 10px">
            <span class="input-group-addon">Project</span>
            <input type="text" disabled value="${bug.project.name}" class="form-control">
        </div>

        <div class="input-group" style="margin-bottom: 10px">
            <span class="input-group-addon">Status</span>
                <select class="form-control" name="status">
                    <c:forEach items="${statuses}" var="status">
                        <option ${status.id eq bug.status.id?"selected":""} value="${status}">${status}</option>
                    </c:forEach>
                </select>
        </div>
        <div class="input-group" style="margin-bottom: 10px">
            <span class="input-group-addon">Priority</span>
                <select class="form-control" name="priority">
                    <c:forEach items="${priorities}" var="priority">
                        <option ${priority.id eq bug.priority.id?"selected":""} value="${priority}">${priority}</option>
                    </c:forEach>
                </select>
        </div>
        <div class="input-group" style="margin-bottom: 10px">
            <span class="input-group-addon">Assignee</span>
                <select class="form-control" name="responsible_id">
                    <c:forEach items="${users}" var="user">
                        <option ${user.id eq bug.responsibleId?"selected":""} value="${user.id}">${user.username}</option>
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
    <c:if test="${bug.status ne 'NEW' }">
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
    <hr>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" value="${bug.id}" name="id"/>
        <input type="submit" class="btn btn-default" value="Save changes"/>
    </form>

    <hr>
    <div class="">
    <h2><span class="label label-default">Comments</span></h2>
        <div class="list-group">
           <c:forEach items="${comments}" var="comment">
            <div class="list-group-item">
                <h4 class="list-group-item-heading">${comment.author.username} -- <fmt:formatDate type="both"
                                                                                                  dateStyle="short"
                                                                                                  pattern="dd.MM.yyyy HH:mm"
                                                                                                  value="${comment.created}"/></h4>
                <p class="list-group-item-text">${comment.body}</p>
            </div>
           </c:forEach>
            <c:if test="${empty comments}">
              <h5><br/>No comments yet.</h5>
            </c:if>
        </div>
        <div style="margin-top: 10px;">
    <h3><span class="label label-default">Add comment</span></h3>
    <form name="input" action="/bug/${bug.id}" method="post">
        <div class="input-group">
            <span class="input-group-addon">Comment</span>
            <textarea rows="5" cols="50" name="body" class="form-control" placeholder="Enter your comment"></textarea>
        </div>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <input style="margin-top:5px;" class="btn btn-default" type="submit" value="Submit"/>
    </form>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/resources/js/bootstrap.min.js"></script>
</div>
</body>
</html>
