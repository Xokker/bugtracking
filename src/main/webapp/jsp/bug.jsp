<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bug #${bug.id}. ${bug.title}</title>
</head>
<body>
    <h1>Bug #${bug.id}. ${bug.title}</h1>
    <p>${bug.description}</p>
    <p>
        <strong>Created:</strong> <fmt:formatDate type="both"
                                dateStyle="short"
                                pattern="dd.MM.yyyy HH:mm"
                                value="${bug.created}"/>
    </p>
    <p>
         <strong>Responsible:</strong> ${bug.responsible.username}
    </p>
    <hr>
    <hr>
    <h2>Comments</h2>
    <c:forEach items="${comments}" var="comment">
        <p>
            <strong>Author id:</strong> ${comment.author_id}.
            Added: <fmt:formatDate type="both"
                                   dateStyle="short"
                                   pattern="dd.MM.yyyy HH:mm"
                                   value="${bug.created}"/>
        </p>
        <p><c:out value="${comment.body}"/></p>
        <hr>
    </c:forEach>
</body>
</html>
