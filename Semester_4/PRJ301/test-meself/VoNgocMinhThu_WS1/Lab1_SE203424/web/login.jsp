<%-- 
    Document   : login
    Created on : Feb 24, 2026, 3:19:40 AM
    Author     : ThongNT2025
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1 style="color: darkorange">Login Page</h1>
        <form action="mainController" method="POST">
            <p>
                UserID <input type="text" name="txtUserID" value="" size="20%" required="" /> <br/>
                Password <input type="password" name="txtPassword" value="" size="20%" required="" />
            </p>
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
        
        <c:set var="invalid" value="${requestScope.INVALID}"/>
        <c:if test="${not empty invalid}">
            <h2 style="color: red">${invalid}</h2>
        </c:if>
    </body>
</html>
