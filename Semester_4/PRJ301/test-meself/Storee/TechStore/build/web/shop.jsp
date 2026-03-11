<%-- 
    Document   : shop
    Created on : Mar 1, 2026, 1:14:35 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop</title>
        <style>
            .error {
                color: red;
                font-weight: bold;
            }
            .success {
                color: green;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div style="text-align: right;">
            Welcome, ${sessionScope.USER_INFO.username} | 
            <a href="MainController?action=View Cart">View Cart</a> | 
            <a href="MainController?action=Logout">Logout</a>
        </div>
        <hr/>

        <c:if test="${not empty sessionScope.ADD_MESS}">
            <h4 class="success">${sessionScope.ADD_MESS}</h4>
            <c:remove var="ADD_MESS" scope="session"/>
        </c:if>
        <c:if test="${not empty sessionScope.ERROR_MESS}">
            <h4 class="error">${sessionScope.ERROR_MESS}</h4>
            <c:remove var="ERROR_MESS" scope="session"/>
        </c:if>

        <form action="MainController" method="POST">
            Search: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}"/>
            <input type="submit" name="action" value="Search"/>
        </form>

        <c:if test="${empty requestScope.SEARCH_RESULT and not empty param.txtSearchValue}">
            <h3>No products found for '${param.txtSearchValue}'. Check out our other items below.</h3>
        </c:if>

        <c:if test="${not empty requestScope.SEARCH_RESULT}">
            <table border="1" cellpadding="5">
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="p" items="${requestScope.SEARCH_RESULT}">
                    <tr>
                        <td>${p.productName}</td>
                        <td>${p.price}</td>
                        <td>
                            <c:if test="${p.stockQuantity > 0}">
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="txtProductID" value="${p.productID}"/>
                                    <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}"/>
                                    <input type="submit" name="action" value="Add to Cart"/>
                                </form>
                            </c:if>

                            <c:if test="${p.stockQuantity <= 0}">
                                <span class="error">Sold Out</span>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>