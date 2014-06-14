<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <title>Bug Tracking</title>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<table style="margin:1.5%; width:97%">
    <tr style="overflow:visible">
        <td style="width:50%;">
<div style="margin-right:5%; height:280px; overflow-y:auto" class="panel panel-primary">
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
         <div style="margin-left:5%; height:280px; overflow-y:auto" class="panel panel-primary">
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
