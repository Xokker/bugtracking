<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User ${user.username}.</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/navigation.jsp"/>

<h1>User ${user.username}.</h1>
<form name="input" action="${requestScope['javax.servlet.forward.request_uri']}" method="post">
    Username:   <input type="text" name="username" value="${user.username}" disabled/>     <br/>
    Full name:   <input type="text" name="fullName" value="${user.fullName}"/>     <br/>
    E-mail:   <input type="text" name="email" value="${user.email}"/>     <br/>
    Password:   <input type="password" name="password" value="${user.password}"/>     <br/>
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
