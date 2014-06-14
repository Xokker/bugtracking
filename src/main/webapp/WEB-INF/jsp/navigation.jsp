<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul>
    <li><a href="/bugs">Bugs list</a></li>
    <li><a href="/users">Manage users</a></li>
</ul>
<form name='logout'
      action="<c:url value='/logout'/>" method='POST'>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Log Out"/>
</form>
