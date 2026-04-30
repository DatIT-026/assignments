<%-- 
    Document   : welcome
    Created on : Apr 26, 2025, 8:58:34 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body>
        <!--your code here-->
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp" />
        </c:if>
        
        <h1>Welcome to ${sessionScope.USER_INFO.fullName}!</h1>
        
        <ul>
            <li><a href="search.jsp">Search by name</a></li>
            <li><a href="MainController?action=Logout">Logout</a></li>
        </ul>

    </body>
</html>
