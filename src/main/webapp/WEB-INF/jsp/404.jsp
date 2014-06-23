<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Bugs Bunny</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/>" rel="stylesheet">
    <style>
        .panel-default {
            overflow-y:visible;
            margin-left:37%;
            margin-top:10%;
            width:346px;
            height:307px;
            background-image: url('/resources/img/logo.png');
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/navigation.jsp"/>
<div class="panel panel-default">
    <div class="panel-body">
       <h3><span class="label label-default" style="margin-left: 6%">Sorry, the page does not exist.</span></h3>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>