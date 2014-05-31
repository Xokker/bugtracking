<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Bugs</title>
</head>
<body>
    <h1>Bug List</h1>

    <c:forEach items="${bugs}" var="bug">
        <p>
            <a href="/bug/${bug.id}">Bug #${bug.id}</a>. ${bug.title}.
            Added: <fmt:formatDate type="both"
                                   dateStyle="short"
                                   pattern="dd.MM.yyyy HH:mm"
                                   value="${bug.created}"/>
        </p>
    </c:forEach>
</body>
</html>
