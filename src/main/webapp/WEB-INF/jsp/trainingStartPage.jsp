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

    <form:form class="form-horizontal" modelAttribute="training" method="post" action="${pageContext.request.contextPath}/cards/training">

        <c:if test="${training.training==null}">
            <h2><spring:message code="trainingDto.chooseTraining"/></h2>
            <div class="form-group" style="margin-top: 50px">
                    <%--<label class="control-label col-sm-2" for="language">Language:</label>--%>
                <div class="col-sm-10">
                    <label class="radio-inline"><form:radiobutton path="training" value="english" checked="checked" label="English-Russian" id="language"/></label>
                    <label class="radio-inline"><form:radiobutton path="training" value="russian" label="Russian-English" id="language"/></label>
                </div>
            </div>

        </c:if>
        <c:if test="${training.training!=null}">
            <h2><spring:message code="trainingDto.select"/></h2>
            <input type="hidden" name="training" value="${training.training}">
            <c:forEach items="${categoryList}" var="category">

    <div class="form-group">
        <div class="col-sm-10">
                        <div class="checkbox">
                            <label><input type="checkbox" name="categories" value="${category.key.id}"><c:out value="${category.key} (${category.value})"/></label>
                        </div>
        </div>
        </div>
            </c:forEach>
 <%--   <div class="form-group">
        <div class="col-sm-10">
            <label class="radio-inline"><form:radiobutton path="training" value="english" checked="checked" label="English" id="language"/></label>
            <label class="radio-inline"><form:radiobutton path="training" value="russian" label="Russian" id="language"/></label>
        </div>
    </div>--%>
        </c:if>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">Start</button>
            </div>
        </div>
    </form:form>
    <c:if test="${not empty error}">
        <p class="error"><spring:message code="training.card.empty"/></p>
    </c:if>
</div>

</body>
</html>
