<%-- 
    Document   : home
    Created on : Mar 6, 2026, 9:57:45 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <h1>Welcome ${sessionScope.USER_INFO.fullName} (${sessionScope.USER_INFO.userID})</h1>

        <a href="MainController?btAction=Logout">Logout</a> | 
        <a href="petList.jsp">Search Page</a>
    </body>
</html>