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
    <%--<h2><spring:message code="trainingDto.select"/></h2>--%>
<%--<form:form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/cards/training/${cardsList.get(0).id}">--%>
        <%--<input type="hidden" name="training" value="${training.training}">
        <c:forEach items="${training.categories}" var="category">
        <input type="hidden" name="categories" value="${category}">
        </c:forEach>--%>
        <%--<input type="hidden" name="cardId" value="${cardsList.get(0).id}">--%>

        <table border="0" cellpadding="8" cellspacing="0" align="center" style="margin-top: 50px">
            <thead>
            <tr>
                <th width="100"><spring:message code="card.word"/></th>
                <th width="100"><spring:message code="card.translate"/></th>

            </tr>
            </thead>
    <tr>
    <td style="padding: 5px"><c:out value="${trainingName.equals('english')?cardsList.get(0).engName: cardsList.get(0).rusName}"/></td>
    <td style="padding: 5px">
    <button type="button" class="btn btn-info btn-sm" data-toggle="collapse" data-target="#demo">Show me</button>
    <div id="demo" class="collapse">
    <c:out value="${trainingName.equals('english')?cardsList.get(0).rusName: cardsList.get(0).engName}"/>
    </div>
    </td>
    </tr>
</table>
        <table width="300" border="0" cellpadding="8" cellspacing="0" align="center" style="margin-top: 100px">
<tr>
            <td align="left">
                <a href="${pageContext.request.contextPath}/cards/training/${cardsList.get(0).id}?status=true" class="btn btn-info" role="button">Я знаю</a>
            </td>
    <td align="right">
        <a href="${pageContext.request.contextPath}/cards/training/${cardsList.get(0).id}?status=false" class="btn btn-info" role="button">Я не знаю</a>
    </td>
        </tr>
            </table>
    </div>
    <%--<div class="form-group">
    <div class="col-sm-10">--%>
   <%-- <label class="radio-inline"><input type="radio" name="status" value="true" checked="checked">Я не знаю</label>
    <label class="radio-inline"><input type="radio" name="status" value="false" >Я знаю</label>--%>
    <%--</div>
    </div>--%>
<form class="form-horizontal">
    <div class="form-group">
        <label class="control-label col-sm-2" for="cards">Cards left:</label>
        <div class="col-sm-10">
            <p class="form-control-static" id="cards">${cardsList.size()}</p>
        </div>
    </div>
</form>
</body>
</html>
