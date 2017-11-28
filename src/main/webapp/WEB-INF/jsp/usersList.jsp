<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<table border="1" cellpadding="8" cellspacing="0" class="table table-bordered table-hover">
    <thead>
    <tr>
        <th class="info"><spring:message code="user.name"/></th>
        <th class="info"><spring:message code="user.email"/></th>
        <th class="info"><spring:message code="user.active"/></th>
        <th class="info"><spring:message code="user.registered"/></th>
        <th class="info"><spring:message code="card.actions"/></th>
    </tr>
    </thead>
    <c:forEach items="${usersList}" var="user">
        <jsp:useBean id="user" scope="page" type="ua.cardsmanager.model.User"/>
        <tr>
            <td><c:out value="${user.name}"/></td>
            <td><a href="mailto:${user.email}">${user.email}</a></td>
            <td><%=user.isEnabled()%>
            </td>
            <td><fmt:formatDate value="${user.dateRegistered}" pattern="dd-MMMM-yyyy"/></td>
            <td><div id="div"><a href="${pageContext.request.contextPath}/userEdit/${user.id}"><span class="glyphicon glyphicon-pencil"></span></a>
            <a href="${pageContext.request.contextPath}/userDelete/${user.id}"><span class="glyphicon glyphicon-remove"></span></a></div></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>