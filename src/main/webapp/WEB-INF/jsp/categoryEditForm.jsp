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
    <h2><spring:message code="user.editCategory"/></h2>
    <form:form class="form-horizontal" modelAttribute="category" method="post" action="${pageContext.request.contextPath}/category/edit">
        <jsp:useBean id="category" type="ua.cardsmanager.model.Category" scope="request"/>
        <input type="hidden" name="id" value="${category.id}">
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" value="${category.name}" name="name" id="name" placeholder="Enter name">
                <form:errors path = "name" cssClass="error" />
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
