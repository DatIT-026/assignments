<%-- 
    Document   : index
    Created on : Jan 30, 2026, 2:52:38 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AutomobileWebApp - Home</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css"/>
    </head>
    <body>
        <nav class="navbar">
            <div class="container main-content">
                <span class="brand">AutomobileWebApp</span>
                <a href="#">Home</a>
                <a href="#">Privacy</a>
            </div>
        </nav>

        <div class="container">
            <h1>Car List</h1>
            <a href="CreateCar.jsp">Create New</a>

            <c:set var="result" value="${requestScope.LIST_RESULT}" />
            <c:if test="${not empty result}">
                <table class="table">
                    <thead>
                        <tr>
                            <th>CarID</th>
                            <th>CarName</th>
                            <th>Manufacturer</th>
                            <th>Price</th>
                            <th>ReleasedYear</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatchServlet" method="POST">
                            <tr>
                                <td>${dto.carID}</td>
                                <td>${dto.carName}</td>
                                <td>${dto.manufacturer}</td>
                                <td>${dto.price}</td>
                                <td>${dto.releasedYear}</td>
                                
                                <td class="action">
                                    <!-- Edit -->
                                    <c:url var="editLink" value="DispatchServlet">
                                        <c:param name="btAction" value="Edit" />
                                        
                                        <c:param name="txtCarID" value="${dto.carID}" />
                                        <c:param name="txtCarName" value="${dto.carName}" />
                                        <c:param name="txtManufacturer" value="${dto.manufacturer}" />
                                        <c:param name="txtPrice" value="${dto.price}" />
                                        <c:param name="txtReleasedYear" value="${dto.releasedYear}" />
                                    </c:url>
                                    <a href="${editLink}">Edit</a> | 

                                    <!-- Details -->
                                    <c:url var="detailLink" value="DispatchServlet">
                                        <c:param name="btAction" value="Details" />
                                        <c:param name="txtCarID" value="${dto.carID}" />
                                    </c:url>
                                    <a href="${detailLink}">Details</a> | 

                                    <!-- Delete -->
                                    <c:url var="deleteConfirmLink" value="DispatchServlet">
                                        <c:param name="btAction" value="Delete" />
                                        <c:param name="txtCarID" value="${dto.carID}" />
                                    </c:url>
                                    <a href="${deleteConfirmLink}">Delete</a>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${empty result}">
                <h2>
                    <font color="red">
                    No Record is matched!!!
                    </font>
                </h2>
            </c:if>
        </div>
    </body>
</html>
