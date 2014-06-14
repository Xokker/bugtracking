<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bug #${bug.id}. ${bug.title}</title>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/navigation.jsp"/>

    <h1>Bug #${bug.id}. ${bug.title}</h1>
    <p> <strong>Status:</strong> ${bug.status}</p>

    <p>${bug.description}</p>
    <p>
        <strong>Created:</strong> <fmt:formatDate type="both"
                                dateStyle="short"
                                pattern="dd.MM.yyyy HH:mm"
                                value="${bug.created}"/>
    </p>
    <p>
        <strong>Created by: ${bug.creator.username}</strong>
    </p>
    <p>
         <strong>Responsible:</strong> ${bug.responsible.username}
    </p>
    <hr>
    <form action="${requestScope['javax.servlet.forward.request_uri']}/close" method="post">
        <input type="hidden" value="${bug.id}" name="id"></input>
        <input type="submit" value="Close bug"></input>
    </form>

    <hr>
    <h2>Comments</h2>
    <c:forEach items="${comments}" var="comment">
        <p>
            <%-- TODO: authorId -> username --%>
            <strong>Author id:</strong> ${comment.authorId}.
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
        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
