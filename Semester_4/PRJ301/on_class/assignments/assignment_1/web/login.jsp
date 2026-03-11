<%-- 
    Document   : login
    Created on : Mar 11, 2026, 7:14:30 AM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style>            
            .form-group {
                display: flex;
                flex-wrap: wrap;
                margin-bottom: 7px;
                margin-right: 5px;
                align-items: center;
            }
            .form-group label {
                width: 70px;
            }
        </style>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="MainController" method="POST">
            <div class="form-group">
            <label>User ID:</label>
            <input type="text" name="txtUserID" value=""  /><br/>
            </div>
            
            <div class="form-group">
            <label>Password:</label>
            <input type="password" name="txtPassword" value="" /><br/>
            </div>
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />
        </form><br/>
        <c:set var="invalid" value="${requestScope.INVALID}"/>
        <c:if test="${not empty invalid}">
            <p style="color: red">${invalid}</p>
        </c:if>
    </body>
</html>