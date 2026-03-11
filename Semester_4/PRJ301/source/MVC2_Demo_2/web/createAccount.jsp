<%-- 
    Document   : createAccount
    Created on : Jan 7, 2026, 10:48:51 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h2 style="color: red">Create Account</h2>
        
        <form action="DispatchServlet" method="POST">
            <c:set var="err" value="${requestScope.CREATE_ERROR}" />
            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" required /> (2-20 chars) <br />
            <c:if test="${not empty err.userNameLongErr}">
                <font color="red">${err.userNameLongErr}</font><br />
            </c:if>
                
            <c:if test="${not empty err.duplicatedUsername}">
                <font color="red">${err.duplicatedUsername}</font><br />
            </c:if>
            
            Last Name* <input type="text" name="txtLastName" 
                              value="${param.txtLastName}" required /> (2-50 chars) <br />
                
            Password* <input type="password" name="txtPassword" value="" required /> (2-30 chars) <br />
            <c:if test="${not empty err.passwordLongErr}">
                <font color="red">${err.passwordLongErr}</font><br />
            </c:if>
                
            Confirm* <input type="password" name="txtConfirm" value="" required /> <br />
            <c:if test="${not empty err.confirmNotMatch}">
                <font color="red">${err.confirmNotMatch}</font><br />
            </c:if>
            
            <input type="submit" value="Create" name="btAction" />
            
            <br />
            
            <a href="login.jsp">Go Back</a>
    </body>
</html>
