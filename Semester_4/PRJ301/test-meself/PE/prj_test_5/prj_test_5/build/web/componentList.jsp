<%-- 
    Document   : componentList
    Created on : Mar 16, 2026, 7:42:00 PM
    Author     : Miniks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ELECTRONIC COMPONENT MANAGEMENT SYSTEM</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <div class="welcome-header">
            <h1>Welcome, ${sessionScope.USER_INFO.fullName} (${sessionScope.USER_INFO.roleID})</h1>
            <a href="MainController?btAction=Logout">Logout</a>
        </div>
        <hr/>

        <c:if test="${not empty param.ERROR}">
            <h2 style="color: red; text-align: center;">
                ${param.ERROR}
            </h2>
        </c:if>

        <form action="MainController" method="GET">
            Search Component (Name or Category): 
            <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" name="btAction" value="Search" />
        </form>

        <br/>

        <c:if test="${not empty requestScope.COMP_LIST}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Manufacturer</th>
                        <th>Specification</th>
                        <th>Location</th>
                        <th>Import Date</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                        <th>Warranty (Month)</th>
                        <th>Note</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${requestScope.COMP_LIST}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.itemID}</td>
                            <td>${dto.itemName}</td>
                            <td>${dto.category}</td>
                            <td>${dto.manufacturer}</td>
                            <td>${dto.specification}</td>
                            <td>${dto.warehouseLocation}</td>
                            <td>${dto.importDate}</td>
                            <td>${dto.quantity}</td>
                            <td>${dto.unitPrice}</td>
                            <td>${dto.warrantyMonth}</td>
                            <td>${dto.note}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty requestScope.COMP_LIST && not empty param.txtSearchValue}">
            <p class="error">No record found!</p>
        </c:if>

    </body>
</html>
