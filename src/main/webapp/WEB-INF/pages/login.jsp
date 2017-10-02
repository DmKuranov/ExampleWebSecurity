<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tag:baseLayout>

    <div class="jumbotron">
        <h1><spring:message code="login_caption"/></h1>
        <c:if test="${! empty formModel.messageKey}">
            <c:choose>
                <c:when test="${!formModel.authenticationSuccessful}">
                    <c:set var="alertClass" value="alert-warning"/>
                </c:when>
                <c:otherwise>
                    <c:set var="alertClass" value="alert-success"/>
                </c:otherwise>
            </c:choose>
            <div class="alert ${alertClass}" role="alert">
                <spring:message code="${formModel.messageKey}"/>
            </div>
        </c:if>
        <c:choose>
            <c:when test="${!formModel.authenticationSuccessful}">
                <form:form method="POST" modelAttribute="formModel" cssClass="form-horizontal">
                    <div class="input-group form-group">
                        <spring:message code="login_prompt" var="login_prompt"/>
                        <form:input path="login" cssClass="form-control" id="loginInput" placeholder="${login_prompt}"
                                    required="true"/>
                    </div>
                    <div class="input-group form-group">
                        <spring:message code="password" var="password"/>
                        <form:password path="password" cssClass="form-control" id="passwordInput"
                                       placeholder="${password}" required="true"/>
                    </div>


                    <div class="input-group form-group">
                        <input type="submit" class="btn btn-lg btn-success" href="" role="button"
                               value="<spring:message code="login"/>"/>
                    </div>
                </form:form>
            </c:when>
            <c:otherwise>
                <h1><spring:message code="welcome"/> <c:out value="${formModel.login}"/></h1>
                <a href="<c:url value="/balance"/>">
                    <div class="btn btn-lg">
                        <spring:message code="check_balance"/>
                    </div>
                </a>
            </c:otherwise>
        </c:choose>
    </div>
</tag:baseLayout>
