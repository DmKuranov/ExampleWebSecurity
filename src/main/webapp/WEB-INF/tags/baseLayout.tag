<%@tag pageEncoding="utf-8" %>
<%@ attribute name="hideHeader" required="false" type="java.lang.Boolean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title><spring:message code="application.name"/></title>
    <meta http-equiv="content-language" content="ru">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">

    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation"><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home"></span></a>
                </li>
                <sec:authorize access="isAuthenticated()" var="isAuthenticated"/>
                <c:choose>
                    <c:when test="${isAuthenticated}">
                        <li role="presentation"><a href="<c:url value="/logout"/>"><spring:message code="logout"/></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li role="presentation"><a href="<c:url value="/login"/>"><spring:message code="login"/></a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
        <c:if test="${hideHeader!=true}">
            <h4 class="text-muted"><spring:message code="application.name"/></h4>
        </c:if>
    </div>
    <jsp:doBody/>

    <footer class="footer">
        <c:if test="${isAuthenticated}">
            <spring:message code="greeting"/>
            <c:out value="${securityHelper.currentUserDetails.username}"/>
        </c:if>
        <span class="pull-right">
            2017
        </span>
    </footer>

</div>