<%-- 
    Document   : update
    Created on : Jan 13, 2026, 10:12:03 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Painting</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <h1>Update Painting</h1>
        
        <form action="UpdatePaintingServlet" method="POST">
            ID: ${param.id} 
            <input type="hidden" name="id" value="${param.id}" /><br/><br/>
            
            Creator: <input type="text" name="creator" value="${param.creator}" required /><br/><br/>
            
            Description: <input type="text" name="description" value="${param.description}" required /><br/><br/>
            
            Height: <input type="number" name="height" value="${param.height}" required /><br/><br/>
            
            Width: <input type="number" name="width" value="${param.width}" required /><br/><br/>
            
            Price: <input type="number" step="0.01" name="price" value="${param.price}" required /><br/><br/>
            
            <input type="hidden" name="lastSearchValue" value="${param.lastSearchValue}" />
            
            <input type="submit" value="Save" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>