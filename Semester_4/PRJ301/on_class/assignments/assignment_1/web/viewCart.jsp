<%-- 
    Document   : viewCart
    Created on : Mar 11, 2026, 7:15:07 AM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <div>
            <h1>Shopping Cart of ${sessionScope.USER_INFO.fullName}</h1>
            <a href="listDevice.jsp">Back to Shop</a> | 
            <a href="MainController?btAction=Logout">Logout</a>
            <hr/>
        </div>

        <div>
            <c:if test="${empty sessionScope.CART}">
                <h3 style="color: red;">Your cart is currently empty!</h3>
            </c:if>

            <c:if test="${not empty sessionScope.CART}">
                <c:set var="totalAmount" value="0" />
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Mobile ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Subtotal</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="entry" items="${sessionScope.CART}" varStatus="counter">
                            <c:set var="mobile" value="${entry.value}" />
                        <form action="MainController" method="POST">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    ${mobile.mobileId}
                                    <input type="hidden" name="mobileId" value="${mobile.mobileId}" />
                                </td>
                                <td>${mobile.mobileName}</td>
                                <td>${mobile.price}</td>
                                <td>
                                    <input type="number" name="quantity" value="${mobile.quantity}" min="1" required />
                                </td>
                                <td>
                                    <fmt:formatNumber value="${mobile.price * mobile.quantity}" pattern="0.00"/>
                                    <c:set var="totalAmount" value="${totalAmount + (mobile.price * mobile.quantity)}" />
                                </td>
                                <td>
                                    <input type="submit" name="btAction" value="Update Cart" />
                                    <input type="submit" name="btAction" value="Remove Cart" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>

                <c:if test="${not empty requestScope.ERROR}">
                    <p style="color: red;">${requestScope.ERROR}</p>
                </c:if>
                        
                <h2 style="color: green;">
                    Total Amount: <fmt:formatNumber value="${totalAmount}" pattern="0.00"/>
                </h2>
            </c:if>
        </div>
    </body>
</html>
