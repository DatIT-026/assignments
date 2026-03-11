<%-- 
    Document   : viewCart
    Created on : Mar 11, 2026, 7:15:07 AM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>View Cart</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <h1>Shopping Cart of ${sessionScope.USER_INFO.fullName}</h1>
        <a href="listDevice.jsp">Back to Shop</a> | 
        <a href="MainController?btAction=Logout">Logout</a>
        <hr/>

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
                    <c:set var="count" value="1" />
                    <c:forEach var="entry" items="${sessionScope.CART}">
                        <c:set var="mobile" value="${entry.value}" />
                        <form action="MainController" method="POST">
                            <tr>
                                <td>${count}</td>
                                <td>
                                    ${mobile.mobileId}
                                    <input type="hidden" name="mobileId" value="${mobile.mobileId}" />
                                </td>
                                <td>${mobile.mobileName}</td>
                                <td>${mobile.price}</td>
                                <td>
                                    <input type="number" name="quantity" value="${mobile.quantity}" min="1" required style="width: 60px;"/>
                                </td>
                                <td>
                                    ${mobile.price * mobile.quantity}
                                    <c:set var="totalAmount" value="${totalAmount + (mobile.price * mobile.quantity)}" />
                                </td>
                                <td>
                                    <input type="submit" name="btAction" value="Update Cart" />
                                    <input type="submit" name="btAction" value="Remove Cart" 
                                           onclick="return confirm('Remove ${mobile.mobileName} from cart?');" />
                                </td>
                            </tr>
                        </form>
                        <c:set var="count" value="${count + 1}" />
                    </c:forEach>
                </tbody>
            </table>
            
            <h2 style="color: green;">Total Amount: ${totalAmount}</h2>
        </c:if>
    </body>
</html>
