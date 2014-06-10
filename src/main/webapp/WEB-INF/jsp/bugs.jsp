<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Bugs</title>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/navigation.jsp"/>

    <c:if test="${not empty message}">
        <p><strong>${message}</strong></p>
    </c:if>

    <h1>Bug List</h1>

    <c:forEach items="${bugs}" var="bug">
        <p>
            <a href="/bug/${bug.id}">Bug #${bug.id}</a>. ${bug.title}. Priority: ${bug.priority}
            Added: <fmt:formatDate type="both"
                                   dateStyle="short"
                                   pattern="dd.MM.yyyy HH:mm"
                                   value="${bug.created}"/>
        </p>
    </c:forEach>
    <hr/>
    <h2>Add bug</h2>
    <form name="input" action="/bugs/add" enctype="application/x-www-form-urlencoded; charset=utf-8" method="post">
        Responsible: <select name="responsible_id">
            <c:forEach items="${users}" var="user">
                <option value="${user.id}">${user.username}</option>
            </c:forEach>
        </select> <br/>
        Title:        <input type="text" name="title"></input>     <br/>
        Description:  <textarea rows="5" cols="50" name="description"></textarea> <br/>
        Priority: <input type="text" name="priority" placeholder="100"></input>
        <input type="submit" value="Submit bug"/>
    </form>
</body>
</html>
