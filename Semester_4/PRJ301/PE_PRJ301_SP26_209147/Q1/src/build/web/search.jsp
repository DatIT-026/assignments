<%-- 
    Document   : search
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

        <a href="MainController?action=Logout">Logout</a>

        <hr/>

        <form action="MainController" method="GET">
            Filter order by status: <br/>
            Newly orders <input type="radio" name="newOr" value="true" checked="checked"/>
            Rejected orders <input type="radio" name="rejOr" value="true" />
        </form>

        <c:if test="${not empty requestScope.ORDER_LIST}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>OrderId</th>
                        <th>OrderDate</th>
                        <th>Customer</th>
                        <th>Address</th>
                        <th>TotalAmount</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${requestScope.ORDER_LIST}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.orderID}</td>
                            <td>${dto.orderDate}</td>
                            <td>${dto.customer}</td>
                            <td>${dto.address}</td>
                            <td>${dto.totalAmount}</td>
                            <td>${dto.status}</td>
                            <td>
                                <input type="hidden" name="lastSearchValue" value="${param.txtSearchValue}" />
                                <input type="submit" name="action" value="Approved" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty requestScope.ORDER_LIST}">
            <p style="color: red; font-weight: bold;">No matching data found!</p>
        </c:if>
    </body>
</html>