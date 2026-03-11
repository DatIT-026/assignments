<%-- 
    Document   : login
    Created on : Dec 14, 2025, 11:32:43 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1 style="color: red">Login Page</h1>
        <form action="DispatchServlet" method="POST">
            Username <input type="text" name="txtUsername" value="" /><br/>
            Password <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />
        </form><br/>
        
        <a href="createAccount.html">Create Account</a>
        
        <c:set var="invalid" value="${requestScope.INVALID}"/>
        <c:if test="${not empty invalid}">
            <h2 style="color: red">${invalid}</h2>
        </c:if>
    </body>
</html>
