<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Projects</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/navigation.jsp"/>

<c:if test="${not empty message}">
    <p><strong>${message}</strong></p>
</c:if>

<h1>Projects List</h1>

<c:forEach items="${projects}" var="project">
    <p>
    <form action="/projects/delete" method="post">
        <a href="/bugs/${project.id}">${project.name}</a>.
        <c:if test="${not empty project.description}">
            ${project.description}
        </c:if>
        <c:if test="${not empty project.managerId}">
            ${project.manager.username}
        </c:if>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" value="${project.name}" name="name"/>
        <input type="submit" value="Delete"/>
    </form>
    </p>
</c:forEach>

<hr/>
<h2>Add project</h2>
<form name="input" action="/projects/add" method="post">
    Name:   <input type="text" name="name"/>     <br/>
    Description:  <input type="text" name="description">     <br/>
    Manager:     <select name="manager_id">
    <c:forEach items="${users}" var="user">
        <option value="${user.id}">${user.username}</option>
    </c:forEach>
</select> <br/>
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
    <input type="submit" value="Add"/>
</form>

</body>
</html>
