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
    <table border="1" cellpadding="8" cellspacing="0" class="table table-bordered table-hover">
        <thead>
        <tr>
            <th class="info"><spring:message code="card.ruName"/></th>
            <th class="info"><spring:message code="card.engName"/></th>
            <th class="info"><spring:message code="card.category"/></th>
            <th class="info"><spring:message code="card.status"/></th>
            <th class="info"><spring:message code="card.registered"/></th>
            <th class="info"><spring:message code="card.actions"/></th>
        </tr>
        </thead>
        <c:forEach items="${cardsList}" var="card">
            <jsp:useBean id="card" scope="page" type="ua.cardsmanager.model.Card"/>
            <tr>
                <td><c:out value="${card.rusName}"/></td>
                <td><c:out value="${card.engName}"/></td>
                <td><c:out value="${card.category.name}"/></td>
                <td><div id="link"><a href="/cards/updateStatus/${card.id}?status=${card.done}<%--&&category=${card.category.id}--%>">
                    <%--<%=card.isDone()%>--%>
                    <c:if test="${card.done}">
                        <span class="glyphicon glyphicon-ok"></span>
                    </c:if>
                    <c:if test="${!card.done}">
                        <span class="glyphicon glyphicon-remove">
                    </c:if>

                </a>
                </td>
                <td><fmt:formatDate value="${card.date}" pattern="dd-MMMM-yyyy"/></td>
                <td><div id="div"><a href="${pageContext.request.contextPath}/cards/edit/${card.id}"><span class="glyphicon glyphicon-pencil"></span></a>
                <a href="${pageContext.request.contextPath}/cards/delete/${card.id}"><span class="glyphicon glyphicon-remove"></span></a></div></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>