<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--<header><a href="${pageContext.request.contextPath}/"><spring:message code="app.home"/></a>&nbsp;|&nbsp;</header>--%>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/"><spring:message code="app.home"/></a>
        </div>
        <ul class="nav navbar-nav">
            <%--<li class="active"><a href="#">Home</a></li>--%>
            <li><a href="${pageContext.request.contextPath}/category/categoryList"><spring:message code="user.Category"/></a></li>
                <li><a href="${pageContext.request.contextPath}/category/add"><spring:message code="user.addCategory"/></a></li>
            <li><a href="${pageContext.request.contextPath}/cards/add"><spring:message code="user.addCard"/></a></li>
                <li><a href="${pageContext.request.contextPath}/cards/training"><spring:message code="user.trainingDto"/></a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    </ul>
    </div>
</nav>

