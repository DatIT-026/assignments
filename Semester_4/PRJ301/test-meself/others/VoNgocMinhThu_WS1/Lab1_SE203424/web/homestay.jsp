<%-- 
    Document   : homestay
    Created on : Feb 24, 2026, 9:53:05 AM
    Author     : C00kies
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h1 style="color: darkorange">Main Page</h1>

        <c:set var="welcome" value="${sessionScope.WELCOME_MSG.fullName}"/>
        <c:if test="${not empty welcome}">
            Welcome, user <b style="color: brown">${welcome}</b>!!
        </c:if>

        <%-- Wrap the button in a form pointing to that URL --%>
        <form action="mainController" method="POST">
            <p>
                <input type="submit" value="Logout" name="btAction" />
            </p>

            <p>
                <h3>Search Homestays by address:</h3> 
            </p>
            
            <p>
            <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" size="40%" />
            <input type="submit" value="Search" name="btAction" />
            </p>
        </form>


    <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
    <c:if test="${not empty result}">
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>HomeID</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Address</th>
                    <th>UserID</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="homestay" items="${result}" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${homestay.homeID}</td>
                        <td>${homestay.description}</td>
                        <td>${homestay.price}</td>
                        <td>${homestay.address}</td>
                        <td>${homestay.userID}</td>
                        <td>
                            <c:url var="deleteUrl" value="mainController">
                                <c:param name="btAction" value="Delete"/>
                                <c:param name="pk" value="${homestay.homeID}"/>
                                <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                            </c:url>
                            <a href="${deleteUrl}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </c:if>
    
    <c:set var="deleteMessage" value="${sessionScope.DELETE_MSG}"/>
    <c:if test="${not empty deleteMessage}">
        <h3>Deleted <b style="color: brown">${deleteMessage}</b> successfully!</h3>
        <c:remove var="DELETE_MSG" scope="session"/>
    </c:if>

</body>
</html>
