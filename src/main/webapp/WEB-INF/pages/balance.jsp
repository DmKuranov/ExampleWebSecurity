<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tag:baseLayout>
    <div class="jumbotron">
        <h1><spring:message code="balance"/></h1>
        <h2><c:out value="${balance}"/></h2>
    </div>
</tag:baseLayout>
