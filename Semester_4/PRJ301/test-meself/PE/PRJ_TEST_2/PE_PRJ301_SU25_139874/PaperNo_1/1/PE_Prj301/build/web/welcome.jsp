<%-- 
    Document   : welcome
    Created on : Apr 26, 2025, 8:58:34 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body>
        <!--your code here-->
        <font>
            Welcome, [${sessionScope.USER_INFO.fullName}]!
        </font><br/>
        <font style="color: blue">
        <c:url var="logoutLink" value="MainController" >
            <c:param name="btAction" value="Logout" />
        </c:url>
        <a href="${logoutLink}">Logout</a>
        </font>
        
        <a href="search.jsp">Click here to search</a>
    </body>
</html>
