<%-- 
    Document   : success
    Created on : Mar 1, 2026, 1:16:13 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <!DOCTYPE html>
<html>
    <head>
        <title>Order Success</title>
        <style>
            .btn { padding: 10px 20px; text-decoration: none; background: #ddd; color: black; border: 1px solid #999; }
            .btn:hover { background: #ccc; }
        </style>
    </head>
    <body>
        <c:remove var="CHECKOUT_MESS" scope="session"/>
        <c:remove var="ERROR_MESS" scope="session"/>
        <c:remove var="ADD_MESS" scope="session"/>

        <h1 style="color: green;">Order Placed Successfully!</h1>
        <p>Thank you for shopping with TechStore.</p>
        
        <br/>
        <a href="MainController?action=Search&txtSearchValue=" class="btn">Continue Shopping</a>
        
        <br/><br/>
        <a href="MainController?action=Logout">Logout</a>
    </body>
</html>