<%-- 
    Document   : update
    Created on : Mar 21, 2026, 8:15:29 PM
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
        
        <h2>Update </h2>
        <form action="MainController" method="POST">
            <input type="hidden" name="txtId" value="${param.paramId}" />
            <input type="hidden" name="paramLastSearchValue" value="${param.paramLastSearchValue}" />
            
            ID: ${param.paramId} <br/>
            Name: <input type="text" name="txtName" value="${param.paramName}" required /><br/>
            Description: <input type="text" name="txtDescription" value="${param.paramDescription}" /><br/>
            Price: <input type="text" name="txtPrice" value="${param.paramPrice}" /><br/>
            Size: <input type="text" name="txtSize" value="${param.paramSize}" /><br/>
            
            <input type="submit" name="action" value="Update" />
            <a href="MainController?action=Search&paramSearchValue=${param.paramLastSearchValue}">Cancel</a>
        </form>
    </body>
</html>