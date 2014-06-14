<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <title>Bug Tracking</title>
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
                        <li><a href="#">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<table style="margin:1.5%; width:97%">
    <tr style="height:10px; overflow:scroll">
        <td style="width:50%;">
<div style="margin-right:5%; height:275px" class="panel panel-primary">
    <div class="panel-heading">Projects</div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Issues</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><a href="#">Bugtracker</a></td>
            <td>Создание багтрекера</td>
            <td>6</td>
        </tr>
        <tr>
            <td><a href="#">LPF</a></td>
            <td>Поездка на ЛПФ</td>
            <td>5</td>
        </tr>
        <tr>
            <td><a href="#">Exams</a></td>
            <td>Подготовка к сессии</td>
            <td>5</td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>
    </td>
     <td style="width:50%;">
         <div style="margin-left:5%" class="panel panel-primary">
         <div class="panel-heading">Latest Issues</div>
         <table class="table table-hover">
             <thead>
             <tr>
                 <th>Name</th>
                 <th>Project</th>
                 <th>Description</th>
                 <th>Priority</th>
                 <th>Deadline</th>
             </tr>
             </thead>
             <tbody>
             <tr>
                 <td><a href="#">LPF-5</a></td>
                 <td><a href="#">LPF</a></td>
                 <td>Водку не купили</td>
                 <td>Critical</td>
                 <td>26.06.2014</td>
             </tr>
             <tr>
                 <td><a href="#">Exams-5</a></td>
                 <td><a href="#">Exams</a></td>
                 <td>Не готов к Гостеву</td>
                 <td>Major</td>
                 <td>25.06.2014</td>
             </tr>
             <tr>
                 <td><a href="#">LPF-4</a></td>
                 <td><a href="#">LPF</a></td>
                 <td>Потеряли Эльдара и его гитару</td>
                 <td>Major</td>
                 <td>26.06.2014</td>
             </tr>
             <tr>
                 <td><a href="#">Btr-6</a></td>
                 <td><a href="#">Bugtracker</a></td>
                 <td>Добавить таблицу с проектами </td>
                 <td>Major</td>
                 <td>15.06.2014</td>
             </tr>
             <tr>
                 <td><a href="#">Exams-4</a></td>
                 <td><a href="#">Exams</a></td>
                 <td>Сдать Бреймана</td>
                 <td>Minor</td>
                 <td>23.06.2014</td>
             </tr>
             </tbody>
         </table>
     </div>
     </td>
    </tr>
    <tr>
     <td colspan="2">
         <div class="panel panel-primary">
             <div class="panel-heading">Comments</div>
             <table class="table table-hover">
                 <thead>
                 <tr>
                     <th>Author</th>
                     <th>Project</th>
                     <th>Bug</th>
                     <th>My role</th>
                     <th>Comment</th>
                 </tr>
                 </thead>
                 <tbody>
                 <tr>
                     <td>deliseev</td>
                     <td><a href="#">LPF</a></td>
                     <td><a href="#">LPF-5</a></td>
                     <td>Assigned on me</td>
                     <td>Берем Зеленую Марку!</td>
                 </tr>
                 <tr>
                     <td>gkozhukhantev</td>
                     <td><a href="#">Exams</a></td>
                     <td><a href="#">Exams-5</a></td>
                     <td>Watched by me</td>
                     <td>А я готов! Лалки!</td>
                 </tr>
                 <tr>
                     <td>agalaev</td>
                     <td><a href="#">LPF</a></td>
                     <td><a href="#">LPF-5</a></td>
                     <td>Assigned on me</td>
                     <td>Какую брать будем?</td>
                 </tr>
                 <tr>
                     <td>esadykov</td>
                     <td><a href="#">Bugtracker</a></td>
                     <td><a href="#">Btr-6</a></td>
                     <td>Created by me</td>
                     <td>Какие поля будут в таблице?</td>
                 </tr>
                 </tbody>
             </table>
         </div>
     </td>
    </tr>
</table>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
