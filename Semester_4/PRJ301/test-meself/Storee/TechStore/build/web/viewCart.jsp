<%-- 
    Document   : viewCart
    Created on : Mar 1, 2026, 1:14:53 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Your Cart</title>
        <style>
            .btn-qty { width: 30px; font-weight: bold; cursor: pointer; }
            .qty-input { width: 40px; text-align: center; }
        </style>
    </head>
    <body>
        <h1>Your Shopping Cart</h1>
        <a href="MainController?action=Search&txtSearchValue=">Continue Shopping</a>
        
        <c:if test="${not empty sessionScope.CHECKOUT_MESS}">
            <h4 style="color: red;">${sessionScope.CHECKOUT_MESS}</h4>
            <c:remove var="CHECKOUT_MESS" scope="session"/>
        </c:if>
        <c:if test="${not empty sessionScope.ERROR_MESS}">
            <h4 style="color: red;">${sessionScope.ERROR_MESS}</h4>
            <c:remove var="ERROR_MESS" scope="session"/>
        </c:if>

        <c:if test="${empty sessionScope.CART.items}">
            <h3>Your cart is empty!</h3>
        </c:if>

        <c:if test="${not empty sessionScope.CART.items}">
            <br/>
            <table border="1" cellpadding="5">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="count" value="0"/>
                    <c:set var="totalPrice" value="0"/>
                    
                    <c:forEach var="entry" items="${sessionScope.CART.items}">
                        <c:set var="count" value="${count + 1}"/>
                        <c:set var="item" value="${entry.value}"/>
                        <c:set var="totalPrice" value="${totalPrice + (item.product.price * item.quantity)}"/>
                        <tr>
                            <td>${count}</td>
                            <td>${item.product.productName}</td>
                            <td>${item.product.price}</td>
                            <td>
                                <form action="MainController" method="POST" style="display: flex; align-items: center;">
                                    <input type="hidden" name="action" value="Update Cart"/>
                                    <input type="hidden" name="txtProductID" value="${item.product.productID}"/>
                                    
                                    <button type="submit" name="method" value="decrease" class="btn-qty">-</button>
                                    
                                    <input type="text" value="${item.quantity}" readonly class="qty-input"/>
                                    
                                    <button type="submit" name="method" value="increase" class="btn-qty">+</button>
                                </form>
                            </td>
                            <td>${item.product.price * item.quantity}</td>
                            <td>
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="txtProductID" value="${item.product.productID}"/>
                                    <input type="hidden" name="action" value="Remove"/>
                                    <input type="submit" value="Remove" onclick="return confirm('Are you sure?');"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4" style="text-align: right; font-weight: bold;">Total Order:</td>
                        <td colspan="2" style="font-weight: bold; color: blue;">${totalPrice}</td>
                    </tr>
                </tbody>
            </table>
            
            <br/>
            <form action="MainController" method="POST">
                <input type="submit" name="action" value="Checkout" style="padding: 10px 20px; font-weight: bold; color: green;"/>
            </form>
        </c:if>
    </body>
</html>