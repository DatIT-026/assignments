<%-- 
    Document   : success
    Created on : Feb 28, 2026, 9:55:48 PM
    Author     : C00kies
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Out Page</title>
    </head>
    <body>
        <h1 style="color: darkorange">Success Page</h1>
        
        <c:set var="checkout_MSG" value="${sessionScope.CHECKOUT_MESS}"/>
        <c:if test="${not empty checkout_MSG}">
            <h2 style="color: red">${checkout_MSG}</h2>
        </c:if>
    </body>
</html>
