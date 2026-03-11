<%-- 
    Document   : shoppingStore
    Created on : Feb 26, 2026, 3:48:22 PM
    Author     : C00kies
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Page</title>
    </head>
    <body style="display: flex; margin: 0; padding: 0;">
        <div style="flex: 6; padding: 20px; border-right: 2px solid #ccc; height: 100vh; overflow-y: auto;">
            <h1 style="color: darkorange">Shopping Store Page</h1>
            <c:set var="welcome" value="${sessionScope.USER_INFO.username}"/>
            <c:if test="${not empty welcome}">
                Welcome, user <span style="color: red">${welcome}</span>!!
            </c:if>

            <p>
            <form action="DispatchServlet">
                Search: <input type="text" name="txtSearchValue" 
                               value="${param.txtSearchValue}" size="20%" />
                <input type="submit" value="Search" name="btAction"/>
                <input type="hidden" name="showCart" value="${param.showCart}" />
            </form>
        </p>

        <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>

        <p>
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:set var="totalProducts" value="0"/>
            
            <c:if test="${not empty cart and not empty cart.products}">
                <c:forEach var="entry" items="${cart.products}">
                    <c:set var="totalProducts" value="${totalProducts + entry.value.stockQuantity}"/>
                </c:forEach>
            </c:if>
            

            <c:url var="viewCart" value="DispatchServlet">
                <c:param name="btAction" value="Search"/>
                <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                <c:param name="showCart" value="true"/>
            </c:url>
            
            <a href="${viewCart}" style="text-decoration: none; background-color: lightblue; border: 1px solid blue; padding: 2px">
                View your cart 🛒
                <c:if test="${totalProducts > 0}">
                    <b style="color: red">(${totalProducts})</b>
                </c:if>
            </a>
        </p>

        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Stock Quantity</th>
                        <th>Category</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}">
                        <c:if test="${dto.status}">
                            <tr>
                                <td>${dto.productID}</td>
                                <td>${dto.productName}</td>
                                <td>${dto.price}</td>
                                <td>${dto.stockQuantity}</td>
                                <td>${dto.category}</td>
                                <td style="color: darkmagenta">
                                    <c:if test="${dto.stockQuantity <= 0}" >SOLD OUT</c:if>
                                    <c:if test="${dto.stockQuantity > 0}">
                                        <c:url var="addCart" value="DispatchServlet">
                                            <c:param name="btAction" value="AddToCart"/>
                                            <c:param name="txtProductID" value="${dto.productID}"/>
                                            <c:param name="txtProductName" value="${dto.productName}"/>
                                            <c:param name="txtPrice" value="${dto.price}"/>
                                            <c:param name="txtStockQuantity" value="${dto.stockQuantity}"/>
                                            <c:param name="txtCategory" value="${dto.category}"/>

                                            <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                                            <c:param name="showCart" value="true"/>
                                        </c:url>
                                        <a href="${addCart}" style="text-decoration: none; color: darkmagenta">+🛒</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${not empty sessionScope.ADD_MESS}">
            <h2>Added product <span style="color: red">ID ${sessionScope.ADD_MESS}</span> into cart!</h2>
            <c:remove var="ADD_MESS" scope="session"/>
        </c:if>
            
            <c:set var="error" value="${sessionScope.ERROR_MESS}"/>
            <c:if test="${not empty error}">
                <h2 style="color: red">Insufficient stock for product ID ${error}!!</h2>
                <c:remove var="ERROR_MESS" scope="session"/>
            </c:if>

        <c:if test="${empty result and not empty param.txtSearchValue}">
            <h2 style="color: red">No product is match!!</h2>
        </c:if>
    </div>

    <c:if test="${param.showCart == 'true'}">
        <div style="flex: 4; padding: 20px; background-color: #f9f9f9; height: 100vh; overflow-y: auto;">
            <div style="text-align: right;">
                <a href="DispatchServlet?btAction=Search&txtSearchValue=${param.txtSearchValue}" 
                   style="text-decoration: none; color: red; font-weight: bold;">❌</a>
            </div>
            <jsp:include page="viewCart.jsp" />
        </div>
    </c:if>

</body>
</html>
