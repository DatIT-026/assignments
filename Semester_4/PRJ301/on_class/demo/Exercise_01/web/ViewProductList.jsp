<%-- 
    Document   : ViewProductList
    Created on : Jan 28, 2026, 7:42:33 AM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>View Product List - Page</title>
    </head>

    <body>

        <a href="CreateProduct.jsp">Create New</a>

        <c:set var="result" value="${requestScope.PRODUCTS_RESULT}" />
        <c:if test="${not empty result}">
            <table>
                <thead>
                    <tr>
                        <th>ProductID</th>
                        <th>ProductName</th>
                        <th>UnitPrice</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="DispatchServlet" method="POST">
                        <tr>
                            <td>${dto.productID}</td>
                            <td>${dto.productName}</td>
                            <td>${dto.unitPrice}</td>
                            <td>${dto.quantity}</td>
                            <td>
                                <!-- Edit -->
                                <a href="">Edit</a> | 
                                
                                <!-- Details -->
                                <c:url var="detailLink" value="DispatchServlet">
                                    <c:param name="btAction" value="Details" />
                                    <c:param name="txtProductID" value="${dto.productID}" />
                                </c:url>
                                <a href="${detailLink}">Details</a>
                            </td>
                        </tr>
                    </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty result}">
            <h2>
                <font color="red">
                No Record is matched!!!
                </font>
            </h2>
        </c:if>
    </body>
</html>
