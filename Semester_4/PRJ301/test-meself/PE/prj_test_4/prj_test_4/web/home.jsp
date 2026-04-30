<%-- 
    Document   : home
    Created on : Mar 13, 2026, 6:16:24 PM
    Author     : Miniks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ARTWORK MANAGEMENT SYSTEM</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <h1>
            Welcome ${sessionScope.USER_INFO.fullName} 
            (${sessionScope.USER_INFO.roleID == 'AD' ? 'ADMIN' : 'CUSTOMER'})
        </h1>

        <a href="paintingList.jsp">Search Page</a> |
        <c:if test="${sessionScope.USER_INFO.roleID == 'AD'}">
            <a href="updatePainting.jsp">Update</a> |
        </c:if>
        <a href="MainController?btAction=Logout">Logout</a>
    </body>
</html>
