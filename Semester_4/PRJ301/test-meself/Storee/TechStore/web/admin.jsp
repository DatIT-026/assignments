<%-- 
    Document   : admin
    Created on : Mar 1, 2026, 1:14:16 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Management</title>
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
            Welcome, ${sessionScope.USER_INFO.username} | <a href="MainController?action=Logout">Logout</a>
        </div>
        <hr/>
        <h1>Product Management (Admin)</h1>


        <c:if test="${not empty sessionScope.ACTION_MESS}">
            <h4 style="color: green;">${sessionScope.ACTION_MESS}</h4>
            <c:remove var="ACTION_MESS" scope="session"/>
        </c:if>

        <form action="MainController" method="POST">
            Search: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}"/>
            <input type="submit" name="action" value="Search"/>
        </form>

        <br/>
        <c:if test="${not empty requestScope.SEARCH_RESULT}">
            <table border="1">
                <tr>
                    <th>Name</th>
                    <th>Stock</th>
                    <th>Price</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="p" items="${requestScope.SEARCH_RESULT}">
                    <tr style="${p.stockQuantity == 0 ? 'background-color: #ffcccc;' : ''} ${p.status == false ? 'color: grey;' : ''}">
                        <td>${p.productName}</td>
                        <td>${p.stockQuantity}</td>
                        <td>${p.price}</td>
                        <td>${p.status ? "Active" : "Hidden"}</td>
                        <td>
                            <a href="MainController?action=Edit&txtProductID=${p.productID}&txtSearchValue=${param.txtSearchValue}">Edit</a>

                            <c:if test="${p.status == true}">
                                | <a href="MainController?action=Delete&txtProductID=${p.productID}&txtSearchValue=${param.txtSearchValue}" onclick="return confirm('Are you sure you want to delete?')">Delete</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>