<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<tag:baseLayout hideHeader="true">

    <div class="jumbotron">
        <h1><spring:message code="application.name"/></h1>
        <p class="lead">Позволяет производить регистрацию новых пользователей. При регистрации пользователя, ему выделяется баланс 0,00.</p>
        <sec:authorize access="!isAuthenticated()">
            <p><a class="btn btn-lg btn-success" href="<c:url value="/signup"/>" role="button"><spring:message
                    code="signup"/></a></p>
        </sec:authorize>
    <a href="<c:url value="/balance"/>">
        <div class="btn btn-lg btn-success">
            <spring:message code="check_balance"/>
        </div>
    </a>
    </div>

    <div class="row marketing">
        <div class="col-lg-6">
            <h4>Хранение данных</h4>
            <p>Конфигурация по умолчанию хранит данные в in-memory экземпляре h2(теряются при перезапуске приложения).</p>

            <h4>Настройка хранения данных</h4>
            <p>Требуется изменение свойств бина dbPool в spring-data.xml, адаптация диалекта в скриптах DDL в папке src/main/resources/db_schema </p>

            <h4>Subheading</h4>
            <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>

        <div class="col-lg-6">
            <h4>Subheading</h4>
            <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

            <h4>Subheading</h4>
            <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet
                fermentum.</p>

            <h4>Subheading</h4>
            <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>
    </div>

</tag:baseLayout>
