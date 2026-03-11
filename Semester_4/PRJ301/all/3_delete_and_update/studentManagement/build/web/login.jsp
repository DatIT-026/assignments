<%-- 
    Document   : login
    Created on : Jan 14, 2026, 7:33:56 AM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login - EduPortal</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="container" style="max-width: 400px; margin-top: 100px;">
            <div class="card">
                <h2>Login</h2>
                <form action="DispatchServlet" method="POST">
                    <div class="form-group">
                        <label>Username</label>
                        <input type="text" name="txtUsername" value="" placeholder="Enter username">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="txtPassword" value="" placeholder="Enter password">
                    </div>
                    <input type="submit" value="Login" name="btAction" id="buttonn" />
                </form>
                <p style="text-align: center; margin-top: 15px; font-size: 0.9rem;">
                    <a href="index.jsp" style="color: var(--secondary-color);">Forgot password?</a>
                </p>
                
                <c:set var="invalid" value="${requestScope.INVALID}"/>
                <c:if test="${not empty invalid}">
                        <h2 style="color: red; font-size: 20px">${invalid}</h2>
                </c:if>
            </div>
        </div>
    </body>
</html>