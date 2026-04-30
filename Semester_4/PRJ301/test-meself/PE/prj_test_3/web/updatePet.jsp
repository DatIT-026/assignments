<%-- 
    Document   : updatePet
    Created on : Mar 6, 2026, 10:04:17 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Update Pet</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO or sessionScope.USER_INFO.roleID ne 'ST'}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <h1>Update Pet Information</h1>
        <hr />

        <form action="MainController" method="POST">
            <input type="hidden" name="paramLastSearchValue" value="${param.paramLastSearchValue}" />
            ID: <input type="text" name="txtId" value="${param.txtId}" readonly />
            <span style="color:red;">${requestScope.UPDATE_ERROR.idErr}</span> <br/>
            
            Name: <input type="text" name="txtPetName" value="${param.txtPetName}" />
            <span style="color:red;">${requestScope.UPDATE_ERROR.nameErr}</span> <br/>
            
            Breed: <input type="text" name="txtBreed" value="${param.txtBreed}" />
            <span style="color:red;">${requestScope.UPDATE_ERROR.breedErr}</span> <br/>
            
            Age: <input type="text" name="txtAge" value="${param.txtAge}" />
            <span style="color:red;">${requestScope.UPDATE_ERROR.ageErr}</span> <br/>
            
            Price: <input type="text" name="txtPrice" value="${param.txtPrice}" />
            <span style="color:red;">${requestScope.UPDATE_ERROR.priceErr}</span> <br/>
            
            Description: <input type="text" name="txtDescription" value="${param.txtDescription}" />
            <span style="color:red;">${requestScope.UPDATE_ERROR.descriptionErr}</span> <br/>
            
            <input type="submit" name="action" value="Update" />
        </form>
    </body>
</html>