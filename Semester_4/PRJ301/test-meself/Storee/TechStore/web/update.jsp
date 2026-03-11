<%-- 
    Document   : update
    Created on : Mar 1, 2026, 1:15:24 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Update Product</title>
    </head>
    <body>
        <h1>Update Product Details</h1>

        <c:if test="${not empty requestScope.ERROR_MSG}">
            <h3 style="color: red;">${requestScope.ERROR_MSG}</h3>
        </c:if>

        <form action="MainController" method="POST">
            <input type="hidden" name="txtProductID" value="${requestScope.PRODUCT.productID}" />
            <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" />

            Product Name: <b>${requestScope.PRODUCT.productName}</b> <br/><br/>

            New Price: 
            <input type="number" step="0.01" name="txtPrice" value="${requestScope.PRODUCT.price}" required/><br/><br/>

            New Stock Quantity: 
            <input type="number" name="txtStockQuantity" value="${requestScope.PRODUCT.stockQuantity}" required/><br/><br/>

            <input type="submit" name="action" value="Confirm Update"/>
            <a href="MainController?action=Search&txtSearchValue=${param.txtSearchValue}">Cancel</a>
        </form>
    </body>
</html>