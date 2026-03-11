<%-- 
    Document   : login
    Created on : Mar 1, 2026, 1:14:03 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login - TechStore</title>
    </head>
    <body>
        <h1>TechStore Login</h1>
        
        <c:if test="${not empty requestScope.ERROR_MSG}">
            <h3 style="color: red;">${requestScope.ERROR_MSG}</h3>
        </c:if>

        <form action="MainController" method="POST">
            Username: <input type="text" name="txtUsername" required/><br/>
            Password: <input type="password" name="txtPassword" required/><br/>
            <input type="submit" name="action" value="Login"/>
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>