<%-- 
    Document   : verifications-sent
    Created on : Mar 11, 2026, 10:37:23 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Authentication System</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/verification-sent_style.css"/>
    </head>
    <body>
        <div class="container">
            <div class="icon"> ... /div>
            <h1>Check Your Email</h1>
            
            <c:if test="${not empty requestScope.SUCCESS_MESSAGE} ">
                <div class="message">${requestScope.SUCCESS_MESSAGE} </div>
            </c:if>

            <c:if test="${not empty requestScope.WARNING_MESSAGE} ">
                <div class="message">${requestScope.WARNING_MESSAGE}</div>
            </c:if>

            <c:if test="${not empty requestScope.ERROR_MESSAGE} ">
                <div class="message">${requestScope.ERROR_MESSAGE}</div>
            </c:if>
            
            <div class="info-box">
                <p>We've seen</p>
                <div class="email-display">${requestScope.USER_EMAIL}</div>
                <p>Please enter the 6-digit code below to verify your account.</p>
            </div>
        </div>
    </body>
</html>
