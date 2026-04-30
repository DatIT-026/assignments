<%-- 
    Document   : home
    Created on : Mar 16, 2026, 7:39:15 PM
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
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp"/>
        </c:if>
        
        <h1>
            Welcome ${sessionScope.USER_INFO.fullName} 
            (${sessionScope.USER_INFO.roleID == 'AD' ? 'ADMIN' : 'CUSTOMER'})
        </h1>

        <a href="componentList.jsp">Search Page</a> |
        <c:if test="${sessionScope.USER_INFO.roleID == 'AD'}">
            <a href="delete.jsp">Delete</a> |
        </c:if>
        <a href="MainController?btAction=Logout">Logout</a>
    </body>
</html>
