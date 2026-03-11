<%-- 
    Document   : productManagement
    Created on : Feb 26, 2026, 3:47:56 PM
    Author     : C00kies
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1 style="color: darkorange">Product Management Page</h1>

        <c:set var="welcome" value="${sessionScope.USER_INFO.username}"/>
        <c:if test="${not empty welcome}">
            Welcome, admin <span style="color: red">${welcome}</span>!!
        </c:if>

        <p>
        <form action="DispatchServlet">
            Search: <input type="text" name="txtSearchValue" 
                           value="${param.txtSearchValue}" size="20%" />
            <input type="submit" value="Search" name="btAction"/>
        </form>
    </p>

    <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>


    <c:if test="${not empty result}">
        <table border="1">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Stock Quantity</th>
                    <th>Category</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dto" items="${result}">
                <form action="DispatchServlet" method="POST">
                    <tr style="${dto.stockQuantity <= 0 ? 'color: red;' : ''} ${!dto.status ? 'color: gray;' : ''}">
                        <td>
                            <input type="hidden" name="txtProductID" value="${dto.productID}" />
                            ${dto.productID}
                        </td>
                        <td>
                            ${dto.productName}
                        </td>
                        <td>
                            <input type="text" name="txtPrice" value="${dto.price}" style="color: inherit;" size="10%"/>
                        </td>
                        <td>
                            <input type="text" name="txtStockQuantity" value="${dto.stockQuantity}" style="color: inherit;" size="10%"/>
                        </td>
                        <td>
                            ${dto.category}
                        </td>
                        <td>
                            <select name="txtStatus">
                                <option value="true" ${dto.status ? 'selected' : ''}>True</option>
                                <option value="false" ${!dto.status ? 'selected' : ''}>False</option>
                            </select>
                        </td>
                        <td>
                            <input type="submit" value="Update" name="btAction" style="color: darkblue"/>
                            <input type="submit" value="Delete" name="btAction" style="color: red" />
                            <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" />
                        </td>
                    </tr>
                </form>

            </c:forEach>
        </tbody>
    </table>
</c:if>

<c:set var="actionMess" value="${sessionScope.ACTION_MESS}"/>
<c:if test="${not empty actionMess}">
    <h2>
        <span style="color: red">${actionMess}</span>
    </h2>
    <c:remove var="ACTION_MESS" scope="session"/>
</c:if>


<c:if test="${empty result and not empty param.txtSearchValue}">
    <h2 style="color: red">No product is match!!</h2>
</c:if>
</body>

</body>
</html>
