<%-- 
    Document   : home
    Created on : Mar 16, 2026, 7:39:15 PM
    Author     : Miniks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ELECTRONIC COMPONENT MANAGEMENT SYSTEM</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp" />
        </c:if>
        
        <h1>
            Welcome ${sessionScope.USER_INFO.fullName} (${sessionScope.USER_INFO.roleID})
        </h1>
        
        <a href="DispatchSerlet?action=Logout">Logout</a> |
        <a href="componentList.jsp">Search Page</a>
    </body>

</html>
