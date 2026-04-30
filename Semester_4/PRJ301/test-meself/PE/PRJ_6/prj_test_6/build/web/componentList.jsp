<%-- 
    Document   : componentList
    Created on : Mar 16, 2026, 7:42:00 PM
    Author     : Miniks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ELECTRONIC COMPONENT MANAGEMENT SYSTEM</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp" />
        </c:if>

        <h1>
            Welcome ${sessionScope.USER_INFO.fullName} (${sessionScope.USER_INFO.roleID})
        </h1>
        

        <a href="DispatchSerlet?action=Logout">Logout</a>

        <c:if test="${not empty requestScope.ERROR_MSG}">
            <h3 style="color: red; font-weight: bold;">
                ${requestScope.ERROR_MSG}
            </h3>
        </c:if>
        
        <hr/>

        <form action="DispatchSerlet" method="GET">
            Search Component (Name / Category): 
            <input type="text" name="event" value="${param.event}" />
            <input type="submit" name="action" value="Search" />
        </form>

        <c:if test="${not empty requestScope.COMPONENT_LIST}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Category</th> 
                        <th>Manufacturer</th>
                        <th>Specification</th>
                        <th>Warehouse Location</th>
                        <th>Import Date</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                        <th>Warranty Month</th>
                        <th>Note</th>
                        <c:set var="role" value="${sessionScope.USER_INFO.roleID}" />
                        <c:if test="${role eq 'AD' or role eq 'CS'}">
                            <th>Action</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${requestScope.COMPONENT_LIST}">
                        <c:set var="isAdmin" value="${sessionScope.USER_INFO.roleID eq 'AD'}" />
                        <c:set var="isValidItem" value="${dto.unitPrice > 0 and dto.isAvailable and dto.quantity > 0}" />

                        <c:if test="${isAdmin or isValidItem}">
                            <tr>
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

                                <c:if test="${role eq 'AD' or role eq 'CS'}">
                                    <td>
                                        <form action="update.jsp" method="POST">
                                            <input type="hidden" name="itemID" value="${dto.itemID}"/>
                                            <input type="hidden" name="itemName" value="${dto.itemName}"/>
                                            <input type="hidden" name="category" value="${dto.category}"/>
                                            <input type="hidden" name="manufacturer" value="${dto.manufacturer}"/>
                                            <input type="hidden" name="specification" value="${dto.specification}"/>
                                            <input type="hidden" name="warehouseLocation" value="${dto.warehouseLocation}"/>
                                            <input type="hidden" name="quantity" value="${dto.quantity}"/>
                                            <input type="hidden" name="unitPrice" value="${dto.unitPrice}"/>
                                            <input type="hidden" name="warrantyMonth" value="${dto.warrantyMonth}"/>
                                            <input type="hidden" name="isAvailable" value="${dto.isAvailable}"/>
                                            <input type="hidden" name="note" value="${dto.note}"/>
                                            <input type="hidden" name="lastSearch" value="${param.event}"/>
                                            
                                            <input type="submit" value="Edit"/>
                                        </form>
                                    </td>
                                </c:if>
                            </tr> </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty requestScope.COMPONENT_LIST}">
            <c:if test="${not empty param.event}">
                <p style="color: red; font-weight: bold;">No data matching the search criteria found!</p>
            </c:if>
        </c:if>
    </body>
</html>
