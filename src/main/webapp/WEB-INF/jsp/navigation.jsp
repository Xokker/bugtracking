<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <form style="margin-left: 45%" class="navbar-form navbar-left"  role="search">
                <div class="form-group">
                    <input style="height:28px;" type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
            </form>
            <form class="navbar-form navbar-right" name='logout'
                  action="<c:url value='/logout'/>" method='POST'>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <input style="height:28px;" class="btn btn-default" type="submit" value="Log Out"/></form>
            <ul class="nav navbar-nav navbar-right">
                <li><span style="margin-top:15px; margin-left:30px" class="glyphicon glyphicon-user"></span></li>
                <li><a href="/users/update/">${pageContext.request.userPrincipal.name}</a></li>
            </ul>
            </form>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
