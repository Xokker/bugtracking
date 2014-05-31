<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/navigation.jsp"/>

    <c:if test="${not empty success}">
        <c:if test="${success}">
            <strong>Connection was successfully configured</strong>
        </c:if>
        <c:if test="${not success}">
            <strong>Something went wrong during connection configuration</strong>
        </c:if>
    </c:if>
    <form name="input" action="/connection" method="post">
        Host:        <input type="text" name="host"/>     <br/>
        Port:       <input type="text" name="port"/>    <br/>
        User:       <input type="text" name="user"/>    <br/>
        Password:   <input type="text" name="password"/><br/>
        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
