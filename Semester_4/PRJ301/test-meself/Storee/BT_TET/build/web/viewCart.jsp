<%-- 
    Document   : cart
    Created on : Feb 28, 2026, 1:09:12 PM
    Author     : C00kies
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
    </head>
    <body>-->
<h1 style="color: darkorange">Cart 🛒</h1>

<c:set var="cart" value="${sessionScope.CART}"/>
<c:set var="total" value="0"/>

<c:if test="${not empty cart and not empty cart.products}">
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Num</th>
                <th>Category</th>
                <th>Sub Total</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="dto" items="${cart.products.values()}">
                <tr>
                    <td>${dto.productID}</td>
                    <td>${dto.productName}</td>
                    <td>${dto.price}</td>
                    <td>${dto.stockQuantity}</td>
                    <td>${dto.category}</td>
                    <td>${dto.stockQuantity * dto.price}</td>
                    <td style="color: darkmagenta">
                        <c:url var="removeOne" value="DispatchServlet">
                            <c:param name="btAction" value="UpdateCart"/>
                            <c:param name="txtProductID" value="${dto.productID}"/>
                            <c:param name="method" value="decrease"/>
                            <c:param name="showCart" value="true"/>
                            <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                        </c:url>
                        <a href="${removeOne}" style="text-decoration: none; color: darkmagenta">➖</a>

                        <c:url var="addOne" value="DispatchServlet">
                            <c:param name="btAction" value="UpdateCart"/>
                            <c:param name="txtProductID" value="${dto.productID}"/>
                            <c:param name="method" value="increase"/>
                            <c:param name="showCart" value="true"/>
                            <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                        </c:url>
                        <a href="${addOne}" style="text-decoration: none; color: darkmagenta">➕</a>
                    </td>
                </tr>
                <c:set var="total" value="${total + (dto.stockQuantity * dto.price)}"/>

            </c:forEach>
        </tbody>
    </table>

    <h3>Total: <span style="color: red">${total}</span></h3>

    <c:url var="checkout" value="DispatchServlet">
        <c:param name="btAction" value="Checkout"/>
    </c:url>
    <a href="${checkout}" 
       style="text-decoration: none; background-color: lightcoral; border: 1px solid red; padding: 10px; margin-top: 10px; color: black"
       onclick="return confirm('Are you sure to buy?')">
        CHECKOUT
    </a>
</c:if>

<c:if test="${empty cart or empty cart.products}">
    <span style="color: red">Your Cart is empty!</span>
</c:if>
<!--    </body>
</html>-->
