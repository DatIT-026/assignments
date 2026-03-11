<%-- 
    Document   : login.jsp
    Created on : Feb 25, 2026, 8:07:47 PM
    Author     : C00kies
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1 style="color: darkorange">Login Page</h1>
        <form action="DispatchServlet" method="POST">
            <p>
                userID: <input type="text" name="txtUserID" value="${param.txtUserID}" size="20%" />
                <br/>
                password: <input type="password" name="txtPassword" value="" size="20%" />
            </p>
            <p>
                <input type="submit" value="Login" name="btAction" />
                <input type="reset" value="Reset" />
            </p>
        </form>

        <c:set var="invalid" value="${requestScope.ERROR_MSG}"/>
        <c:if test="${not empty invalid}">
            <h2 style="color: red">${invalid}</h2>
        </c:if>
            
    </body>
</html>

