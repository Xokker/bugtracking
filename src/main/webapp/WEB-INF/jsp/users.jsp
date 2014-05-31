<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/navigation.jsp"/>

    <c:if test="${not empty message}">
        <p><strong>${message}</strong></p>
    </c:if>

    <h1>User List</h1>

    <c:forEach items="${users}" var="user">
        <p>
            <form action="/users/delete" method="post">
                <strong>${user.username}</strong>.
                <c:if test="${not empty user.email}">
                    [${user.email}]
                </c:if>
                <c:if test="${not empty user.fullName}">
                    ${user.fullName}
                </c:if>
                <input type="hidden" value="${user.id}" name="user_id"></input>
                <input type="submit" value="Delete user"></input>
            </form>
        </p>
    </c:forEach>

    <hr/>
    <h2>Add user</h2>
    <form name="input" action="/users/add" method="post">
        Username:   <input type="text" name="username"/>     <br/>
        Full name:  <input type="text" name="full_name">     <br/>
        Email:      <input type="text" name="email">         <br/>
        <input type="submit" value="Create user"/>
    </form>

</body>
</html>
