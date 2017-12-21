<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeaderLogin.jsp"/>

<div class="container" style="margin-top:30px ">

    <form:form class="form-horizontal" modelAttribute="card" method="post" action="${pageContext.request.contextPath}/cards/editStatus">
        <jsp:useBean id="card" type="ua.cardsmanager.model.Card" scope="request"/>
    <input type="hidden" name="id" value="${card.id}">
    <input type="hidden" name="done" value="${card.done}">
    <input type="hidden" name="progress" value="${card.progress}">
        <input type="hidden" name="category" value="${card.category.id}">
        <div class="form-group">
            <label class="control-label col-sm-2" for="engName">English Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" value="${card.engName}" name="engName" id="engName" readonly>
                <form:errors path = "engName" cssClass="error" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="rusName">Russian Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" value="${card.rusName}" name="rusName" id="rusName" readonly>
                <form:errors path = "rusName" cssClass="error" />
            </div>
        </div>

        <h3 style="margin: 50px 0px 20px 50px"><spring:message code="training.editStatus"/></h3>
        <c:forEach items="${card.statusList}" var="status" varStatus="st">
        <div class="form-group">
            <label class="control-label col-sm-2">Training ${status.training.name}:</label>
            <div class="col-sm-10">
                <label class="radio-inline"><input type="radio" name="statusList[${st.index}].done" value="false" ${status.done==false?'checked="checked"':''}>No</label>
                <label class="radio-inline"><input type="radio" name="statusList[${st.index}].done" value="true" ${status.done==true?'checked="checked"':''}>Yes</label>
                    <input type="hidden" name="statusList" value="${status.id}">

            </div>
        </div>
        </c:forEach>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </div>
    </form:form>
</div>

</body>
</html>
