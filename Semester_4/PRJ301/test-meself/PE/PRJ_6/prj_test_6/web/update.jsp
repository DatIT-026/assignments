<%-- 
    Document   : update
    Created on : Mar 20, 2026, 10:48:47 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp" />
        </c:if>
        
        <c:set var="isAdmin" value="${sessionScope.USER_INFO.roleID eq 'AD'}" />
        
        <h2>Update Component</h2>
        <form action="DispatchSerlet" method="POST">
            <input type="hidden" name="itemID" value="${param.itemID}" />
            <input type="hidden" name="lastSearch" value="${param.lastSearch}" />
            
            ID: <input type="text" name="itemID" value="${param.itemID}" readonly /><br/>
            Name: <input type="text" name="itemName" value="${param.itemName}" required /><br/>
            Category: <input type="text" name="category" value="${param.category}" /><br/>
            Manufacturer: <input type="text" name="manufacturer" value="${param.manufacturer}" /><br/>
            Specification: <input type="text" name="specification" value="${param.specification}" /><br/>
            Warehouse: <input type="text" name="warehouseLocation" value="${param.warehouseLocation}" /><br/>
            Quantity: <input type="number" name="quantity" value="${param.quantity}" min="0" required /><br/>
            Note: <input type="text" name="note" value="${param.note}" /><br/>
            
            Unit Price: 
            <input type="number" name="unitPrice" value="${param.unitPrice}" ${!isAdmin ? 'readonly' : ''} /><br/>
                   
            Warranty Month: 
            <input type="number" name="warrantyMonth" value="${param.warrantyMonth}" ${!isAdmin ? 'readonly' : ''} /><br/>
            
            Is Available: 
            <input type="checkbox" name="isAvailable" value="true" 
                   ${param.isAvailable == 'true' ? 'checked' : ''} ${!isAdmin ? 'disabled' : ''} /><br/>
                   
            <c:if test="${!isAdmin}">
                <input type="hidden" name="isAvailable" value="${param.isAvailable}" />
            </c:if>
            
            <br/>
            <input type="submit" name="action" value="Update" />
            <a href="DispatchSerlet?action=Search&event=${param.lastSearch}">Cancel</a>
        </form>
    </body>
</html>