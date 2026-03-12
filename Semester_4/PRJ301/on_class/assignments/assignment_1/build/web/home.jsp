<%-- 
    Document   : home
    Created on : Mar 11, 2026, 7:14:23 AM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <h1>Welcome ${sessionScope.USER_INFO.fullName} (${sessionScope.USER_INFO.userId})</h1>

        <a href="MainController?btAction=Logout">Logout</a> | 
        <a href="listDevice.jsp">Search Page</a>
    </body>
</html>