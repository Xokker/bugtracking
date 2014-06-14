<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <title>Bug Tracking</title>
</head>
<title>Issues</title>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <img  src="resources/img/logo.png" width="50" height="50"/>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/projects">Projects</a></li>
                <li><a href="/bugs">Issues</a></li>
            </ul>
            <form class="navbar-form navbar-right"  role="search">
                <div class="form-group">
                    <input style="height:28px;" type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li><span style="margin-top:15px;" class="glyphicon glyphicon-user"></span></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Username <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Profile</a></li>
                        <li class="divider"></li>
                        <li><form name='logout'
                                  action="<c:url value='/logout'/>" method='POST'>
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                            <input type="submit" value="Log Out"/></form></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<table style="width:98%; margin:1%">
    <tr>
        <td></td>
        <td >
            <input style="margin-left:2%" type="checkbox" name="Show closed" title="Show closed"> Show closed</input>
            <div style="margin-left:25%;" class="btn-group">
            <button  type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    Sort by:
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#">Newest</a></li>
                    <li><a href="#">Priority</a></li>
                    <li><a href="#">Status</a></li>
                </ul>
                </div>
                <button style="margin-left:30%;" type="button" class="btn btn-default">New issue</button>
        </td>
    </tr>
    <tr>
        <td >
            <div class="panel panel-primary">
            <!-- Default panel contents -->
            <div class="panel-heading">Browse issues</div>

            <!-- List group -->
            <ul class="list-group">
                <li class="list-group-item"><a href="#">Everyone's Issues</a></li>
                <li class="list-group-item"><a href="#">Assigned to me</a></li>
                <li class="list-group-item"><a href="#">Created by me</a></li>
                <li class="list-group-item"><a href="#">Viewed by me</a></li>
            </ul>
        </div>
        </td>
        <td rowspan="2">
        <div style="margin-left:2%" class="panel panel-primary">
            <div class="panel-heading">Issues</div>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Project</th>
                    <th>Description</th>
                    <th>Priority</th>
                    <th>Status</th>
                    <th>Deadline</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><a href="#">LPF-5</a></td>
                    <td><a href="#">LPF</a></td>
                    <td>Водку не купили</td>
                    <td>Critical</td>
                    <td>Open</td>
                    <td>26.06.2014</td>
                </tr>
                <tr>
                    <td><a href="#">Exams-5</a></td>
                    <td><a href="#">Exams</a></td>
                    <td>Не готов к Гостеву</td>
                    <td>Major</td>
                    <td>In progress</td>
                    <td>25.06.2014</td>
                </tr>
                <tr>
                    <td><a href="#">LPF-4</a></td>
                    <td><a href="#">LPF</a></td>
                    <td>Потеряли Эльдара и его гитару</td>
                    <td>Major</td>
                    <td>Resolved</td>
                    <td>26.06.2014</td>
                </tr>
                <tr>
                    <td><a href="#">Btr-6</a></td>
                    <td><a href="#">Bugtracker</a></td>
                    <td>Добавить таблицу с проектами </td>
                    <td>Major</td>
                    <td>In progress</td>
                    <td>15.06.2014</td>
                </tr>
                <tr>
                    <td><a href="#">Exams-4</a></td>
                    <td><a href="#">Exams</a></td>
                    <td>Сдать Бреймана</td>
                    <td>Minor</td>
                    <td>In progress</td>
                    <td>23.06.2014</td>
                </tr>
                </tbody>
            </table>
        </div>
</td>
    </tr>
    <tr>
        <td>
            <div class="panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading">Projects</div>

                <!-- List group -->
                <ul class="list-group">
                    <li class="list-group-item"><a href="#">LPF</a></li>
                    <li class="list-group-item"><a href="#">Exams</a></li>
                    <li class="list-group-item"><a href="#">Bugtracker</a></li>
                </ul>
            </div>
        </td>
    </tr>
</table>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
