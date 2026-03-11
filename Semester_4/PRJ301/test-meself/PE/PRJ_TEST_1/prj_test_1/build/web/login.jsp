<%-- 
    Document   : login.jsp
    Created on : Jan 13, 2026, 10:11:30 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="main_controller" method="POST">
            userID <input type="text" name="txtUsername" value="" /><br/>
            password <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />
        </form><br/>

    <c:set var="invalid" value="${requestScope.INVALID}"/>
    <c:if test="${not empty invalid}">
        <h2 style="color: red">${invalid}</h2>
    </c:if>
</body>
</html>
