<%-- 
    Document   : ViewDetailProduct
    Created on : Jan 29, 2026, 10:01:33 AM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>View Detail Product - Page</title>
        <style>
            p {
                margin: 0 0 0 30px;
            }
        </style>
    </head>

    <body>
        <h2>Product</h2>
        <c:set var="dto" value="${requestScope.PRODUCT_DETAIL}" />
        <c:if test="${not empty dto}">
                ProductID
                <p>${dto.productID}</p>
                ProductName
                <p>${dto.productName}</p>
                UnitPrice
                <p>${dto.unitPrice}</p>
                Quantity
                <p>${dto.quantity}</p>
        </c:if>
        <a href="DispatchServlet">Back to List</a>
    </body>
</html>
