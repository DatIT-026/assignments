<%-- 
    Document   : login
    Created on : Mar 13, 2026, 6:15:45 PM
    Author     : Miniks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ARTWORK MANAGEMENT SYSTEM</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="MainController" method="POST">
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
