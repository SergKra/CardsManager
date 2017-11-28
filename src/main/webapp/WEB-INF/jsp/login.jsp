<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
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

<div class="jumbotron">
    <div class="container" align="center">

        <%--<h4><spring:message code="app.login"/></h4>--%>
        <div class="container">
            <table cellpadding="0" cellspacing="0" align="center" style="margin-top: 50px">
                <form:form method="post" action="${pageContext.request.contextPath}/users/login">
                    <tr>
                        <td>
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="email" class="form-control" id="email" placeholder="Enter email"
                                       name="email">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="form-group">
                                <label for="pwd">Password:</label>
                                <input type="password" class="form-control" id="pwd" placeholder="Enter password"
                                       name="pwd">
                            </div>
                        </td>
                    </tr>
                    <tr>

                                <td>
                                    <div class="form-group">

                                    <input type="checkbox"
                                           onchange="document.getElementById('pwd').type = this.checked ? 'text' : 'password'">
                                        <small>Show password</small>
                                    </div>
                                    </td>
</tr>
                    <tr>
                        <td>


                                    <button type="submit" class="btn btn-primary form-control">Login</button>

                        </td>
                    </tr>
                </form:form>
            </table>
        </div>
    </div>
</div>
</body>
</html>

