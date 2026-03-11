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
        <c:if test="${empty sessionScope.USER_INFO or not (sessionScope.USER_INFO.roleID == 'ST')}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <h1>Update Pet Information</h1>

        <form action="MainController" method="POST">
            ID: ${param.petID} <input type="hidden" name="id" value="${param.petID}" /> <br/>
            Name: <input type="text" name="petName" value="${param.petName}" required /><br/>
            Breed: <input type="text" name="breed" value="${param.breed}" required /><br/>
            Age: <input type="text" name="age" value="${param.age}" required /><br/>
            Price: <input type="text" name="price" value="${param.price}" required /><br/>
            Description: <input type="text" name="description" value="${param.description}" /><br/>

            <c:if test="${not empty requestScope.ERROR}">
                <p style="color: red;">
                    ${requestScope.ERROR}
                </p>
            </c:if>

            <input type="submit" name="btAction" value="Update" />
        </form>
    </body>
</html>