<%-- 
    Document   : update
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
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
        <!--your code here-->
        <c:if test="${empty sessionScope.USER_INFO or sessionScope.USER_INFO.roleID ne 'AD'}">
            <c:redirect url="login.jsp" />
        </c:if>

        <h1>Update Fashion</h1>
        <hr />

        <form action="MainController" method="POST">
            <input type="hidden" name="paramLastSearchValue" value="${param.paramLastSearchValue}" />
            ID: <input type="text" name="txtId" value="${param.paramId}" readonly />
            <span style="color:red;">${requestScope.UPDATE_ERROR.idErr}</span> <br/>
            
            Name: <input type="text" name="txtName" value="${param.paramName}" />
            <span style="color:red">${requestScope.UPDATE_ERROR.nameErr}</span> <br/>
            
            Description: <input type="text" name="txtDescription" value="${param.paramDescription}" />
            <span style="color:red">${requestScope.UPDATE_ERROR.descriptionErr}</span> <br/>
            
            Price: <input type="text" name="txtPrice" value="${param.paramPrice}" />
            <span style="color:red">${requestScope.UPDATE_ERROR.priceErr}</span> <br/>
            
            Size: <input type="text" name="txtSize" value="${param.paramSize}" />
            <span style="color:red;">${requestScope.UPDATE_ERROR.sizeErr}</span> <br/>
            
            Status? <input type="checkbox" name="txtStatus" 
                <c:if test="${param.paramStatus == 'true' || param.paramStatus == 'on'}">checked</c:if> /> 
            <br/>

            <input type="submit" name="action" value="Update" />
        </form>
    </body>
</html>