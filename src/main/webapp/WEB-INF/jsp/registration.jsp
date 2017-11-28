<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<jsp:include page="fragments/bodyHeader.jsp"/>
<jsp:useBean id="user" type="ua.cardsmanager.model.User" scope="request"/>

<%--<div class="jumbotron">
    <div class="container" align="center">

        <h4><spring:message code="app.registration"/></h4>
        <div class="container">
            <table cellpadding="0" cellspacing="0" align="center" style="margin-top: 50px">
        <form:form modelAttribute="user" method="post" action="${pageContext.request.contextPath}/users/registration">
        <thead>
        <tr>
            <td>Name:</td>
            <td> <input type="text" value="${user.name}" size=40 name="name"></td>
            <td> <form:errors path = "name" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td> <input type="text" value="${user.email}" size=40 name="email"></td>
            <td> <form:errors path = "email" cssClass="error" /></td>
        </tr>

        <tr>
            <td>Password:</td>
            <td> <input type= "password" value="${user.password}" size=40 name="password" id="password">
            </td>
            <td > <form:errors path = "password" cssClass="error" /></td>

        </tr>
        <tr>
            <td align="left"><input type="checkbox" onchange="document.getElementById('password').type = this.checked ? 'text' : 'password'"> Show password
            </td>
            <td align="right"><button type="submit">Registration</button></></td>
        </tr>
        </thead>
    </table>
</form:form>--%>

<div class="container">
    <h2><spring:message code="app.registration"/></h2>
    <form:form class="form-horizontal" modelAttribute="user" method="post" action="${pageContext.request.contextPath}/users/registration">
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" value="${user.name}" name="name" id="name" placeholder="Enter name">
                <form:errors path = "name" cssClass="error" />
                <c:if test="${not empty error}">
                <p class="error"><spring:message code="user.error.email"/></p>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">Email:</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="email" value="${user.email}" name="email" placeholder="Enter email" >
                <form:errors path = "email" cssClass="error" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="pwd">Password:</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="pwd" name="password" value="${user.password}" placeholder="Enter password" >
                <form:errors path = "password" cssClass="error" />
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label><input type="checkbox" onchange="document.getElementById('pwd').type = this.checked ? 'text' : 'password'"> Show password</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">Registration</button>
            </div>
        </div>
    </form:form>
</div>

</body>
</html>


