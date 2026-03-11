<%-- 
    Document   : createAccount
    Created on : Jan 7, 2026, 10:48:51 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Product</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h2>Product</h2>

        <form action="DispatchServlet">                
            <c:set var="err" value="${requestScope.CREATE_ERROR}" />
            ProductName <input type="text" name="txtProductName" value="${param.txtProductName}" required /> <br />
            <c:if test="${not empty err.productNameLengthErr}">
                <font color="red">${err.productNameLengthErr}</font><br />
            </c:if>

            UnitPrice <input type="text" name="txtUnitPrice" value="${param.txtUnitPrice}" required /> <br />
            <c:if test="${not empty err.unitPriceFormatErr}">
                <font color="red">${err.unitPriceFormatErr}</font><br />
            </c:if>

            Quantity <input type="text" name="txtQuantity" value="${param.txtQuantity}" required /> <br />
            <c:if test="${not empty err.quantityFormatErr}">
                <font color="red">${err.quantityFormatErr}</font><br />
            </c:if>

            <input type="submit" value="Create" name="btAction" />
            
            <c:set var="result" value="${requestScope.CREATE_SUCCESSFUL}" />
            <c:if test="${not empty result}">
                <font color="red">Created Successfully!</font><br />
            </c:if>
        </form>

        <a href="DispatchServlet">Back to List</a>
    </body>
</html>
