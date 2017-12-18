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
            <th class="info"><spring:message code="category.name"/></th>
            <th class="info"><spring:message code="category.registered"/></th>
            <th class="info"><spring:message code="category.size"/></th>
            <th class="info"><spring:message code="category.status"/></th>
            <th class="info"><spring:message code="card.actions"/></th>
        </tr>
        </thead>
        <c:forEach items="${categoryList.keySet()}" var="category">
            <jsp:useBean id="category" scope="page" type="ua.cardsmanager.model.Category"/>
            <tr>
                <td><c:out value="${category.name}"/></td>
                <td><fmt:formatDate value="${category.date}" pattern="dd-MMMM-yyyy"/></td>
                <td><a href="/cards/cardsList/${category.id}"><c:out value="${category.cardList.size()}"/></a></td>
                <td><a href="/cards/cardsList/${category.id}?status=false"><c:out value="${categoryList.get(category)}"/></a></td>
                <c:if test="${category.name.compareTo('general')!=0}">
                    <td><div id="div"><a href="${pageContext.request.contextPath}/category/edit/${category.id}"><span class="glyphicon glyphicon-pencil"></span></a>
                    <a href="${pageContext.request.contextPath}/category/delete/${category.id}"><span class="glyphicon glyphicon-remove"></span></a></div></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
