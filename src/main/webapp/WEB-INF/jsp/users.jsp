<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <h1>User List</h1>

    <c:forEach items="${users}" var="user">
        <p>${user}</p>
    </c:forEach>

</body>
</html>
