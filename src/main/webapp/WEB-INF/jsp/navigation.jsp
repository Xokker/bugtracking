<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            <a href="<c:url value='/index'/>">
                <img src="/resources/img/logo.png" width="50" height="50"/>
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/projects"/>">Projects</a></li>
                <li><a href="<c:url value="/bugs"/>">Issues</a></li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="<c:url value="/users"/>">Users</a></li>
                </sec:authorize>
            </ul>
            <form class="navbar-form navbar-right" name='logout'
                  action="<c:url value='/logout'/>" method='POST'>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <input style="height:28px;line-height: 1.1" class="btn btn-default" type="submit" value="Log Out"/></form>
            <ul class="nav navbar-nav navbar-right">
                <li><span style="margin-top:13px; margin-left:30px" class="glyphicon glyphicon-user"></span></li>
                <li><a style="margin-top:-2px;" href="<c:url value="/me">
                                <c:param name="backUrl" value="${requestScope['javax.servlet.forward.request_uri']}"/>
                             </c:url>">
                        ${pageContext.request.userPrincipal.name}
                    </a>
                </li>
            </ul>
            </form>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
