<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: j
  Date: 06.11.2017
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeaderLogin.jsp"/>

<div class="container">
    <h2><spring:message code="user.editCard"/></h2>
    <form:form class="form-horizontal" modelAttribute="card" method="post" action="${pageContext.request.contextPath}/cards/edit">
        <jsp:useBean id="card" type="ua.cardsmanager.model.Card" scope="request"/>
        <input type="hidden" name="id" value="${card.id}">
        <input type="hidden" name="done" value="${card.done}">
        <input type="hidden" name="progress" value="${card.progress}">
        <div class="form-group">
            <label class="control-label col-sm-2" for="engName">English Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" value="${card.engName}" name="engName" id="engName" placeholder="Enter English name">
                <form:errors path = "engName" cssClass="error" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="rusName">Russian Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" value="${card.rusName}" name="rusName" id="rusName" placeholder="Enter Russian name">
                <form:errors path = "rusName" cssClass="error" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="category">Category:</label>
            <div class="col-sm-10">
                <form:select path="category" id="category" cssClass="selectpicker" title="Category">
                    <option selected value="${card.category.id}"> ${card.category.name}</option>
                    <c:forEach items="${categoryList}" var="category">
                        <option value="${category.id}"> ${category.name}</option>
                    </c:forEach>
                </form:select>
                <form:errors path = "category" cssClass="error" />
<%--                    <form:select path = "category" id = "category">
                        <form:options items = "${categoryList}" />
                    </form:select>--%>
                    <%--<form:errors path = "category" cssClass="error" />--%>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </div>
    </form:form>
</div>

</body>
</html>
