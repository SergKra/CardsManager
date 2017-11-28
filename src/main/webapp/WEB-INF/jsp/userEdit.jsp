<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<jsp:include page="fragments/bodyHeaderLogin.jsp"/>
<jsp:useBean id="user" type="ua.cardsmanager.model.User" scope="request"/>
<%--<form:form modelAttribute="user" method="post" action="${pageContext.request.contextPath}/userEdit">
    <p align="center" style="font-size: x-large"><spring:message code="app.login"/></p>
    <table cellpadding="5" cellspacing="0" align="center">
        <thead>
        <tr>
            <td><input type="hidden" name="id" value="${user.id}"></td>
        </tr>
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
            <td> <input type= "text" value="${user.password}" size=40 name="password" id="password">
            </td>
            <td > <form:errors path = "password" cssClass="error" /></td>

        </tr>
        <tr>
            <td>Role:</td>
            <td>
                <form:radiobuttons path = "roles" items = "${roles}"/>
            </td>

        </tr>
        <tr>
            <td align="right"><button type="submit">Save</button></td>
        </tr>
        </thead>
    </table>
</form:form>--%>

<div class="container">
    <form:form class="form-horizontal" modelAttribute="user" method="post"
               action="${pageContext.request.contextPath}/userEdit">
        <input type="hidden" name="id" value="${user.id}">
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" value="${user.name}" name="name" id="name"
                       placeholder="Enter name">
                <form:errors path="name" cssClass="error"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">Email:</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" value="${user.email}">
                <form:errors path="email" cssClass="error"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="pwd">Password:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="pwd" placeholder="Enter password" name="password"
                       value="${user.password}">
                <form:errors path="password" cssClass="error"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="enabled">Enabled:</label>
            <div class="col-sm-10">
                <label class="radio-inline"><form:radiobutton path="enabled" value="true" checked="checked" label="TRUE" id="enabled"/></label>
                <label class="radio-inline"><form:radiobutton path="enabled" value="false" label="FALSE" id="enabled"/></label>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="roles">Role:</label>
                <div class="col-sm-10">
        <c:forEach items="${roles}" var="role">
              <%--<small><form:radiobuttons path="roles" items="${roles}"/></small>--%>
                    <%--<label class="radio-inline"><input type="radio" id="roles" name="roles">${role}</label>--%>
            <label class="radio-inline"><form:radiobutton path="roles" value="${role.toString()}" label="${role.toString()}" id="roles"/></label>
                </c:forEach>
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


