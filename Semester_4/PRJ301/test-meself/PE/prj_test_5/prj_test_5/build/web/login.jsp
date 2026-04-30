<%-- 
    Document   : login
    Created on : Mar 16, 2026, 7:40:37 PM
    Author     : Miniks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ELECTRONIC COMPONENT MANAGEMENT SYSTEM</title>
    </head>
    <body>
        <h1>Login Page</h1>

        <form action="MainController" method="POST">
            <label>User ID:</label>
            <input type="text" name="txtUserID" value=""  /><br/>
            <label>Password:</label>
            <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />
        </form>

        <br/>

        <c:set var="invalid" value="${requestScope.INVALID}"/>
        <c:if test="${not empty invalid}">
            <p style="color: red">${invalid}</p>
        </c:if>
    </body>
</html>
