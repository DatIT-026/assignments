<%-- 
    Document   : search
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <!--your code here-->
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp" />
        </c:if>

        <h1>Welcome ${sessionScope.USER_INFO.fullName}</h1>

        <a href="MainController?action=logout">Logout</a>

        <hr/>

        <form action="MainController" method="POST">
            Search by Price Range: <br/>
            Min Price: <input type="number" name="minPrice" value="${param.minPrice}" required />
            Max Price: <input type="number" name="maxPrice" value="${param.maxPrice}" required />
            <input type="submit" name="action" value="search" />

            <br/>

            <c:if test="${not empty err.priceErr}">
                <font color="red">${err.priceErr}</font><br />
            </c:if>
        </form>

        <c:if test="${not empty requestScope.PRODUCT_LIST}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Sub Total</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${requestScope.PRODUCT_LIST}">
                    <form action="MainController" method="POST">
                        <tr>
                            <td>${dto.id}</td>
                            <td>${dto.name}</td>
                            <td>${dto.price}</td>
                            <td>${dto.quantity}</td>
                            <td><fmt:formatNumber value="${dto.price * dto.quantity}" maxFractionDigits="2" /></td>
                            <td>
                                <input type="hidden" name="minPrice" value="${param.minPrice}" />
                                <input type="hidden" name="maxPrice" value="${param.maxPrice}" />

                                <c:url var="deleteLink" value="MainController">
                                    <c:param name="action" value="delete"/>
                                    <c:param name="id" value="${dto.id}"/>
                                    <c:param name="minPrice" value="${param.minPrice}"/>
                                    <c:param name="maxPrice" value="${param.maxPrice}"/>
                                </c:url>
                                
                                <a href="${deleteLink}">Remove</a>

                            </td>
                        </tr>
                    </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty requestScope.PRODUCT_LIST}">
            <c:if test="${not empty param.minPrice}">
                <p style="color: red; font-weight: bold;">No data matching the search criteria found!</p>
            </c:if>
        </c:if>

    </body>
</html>