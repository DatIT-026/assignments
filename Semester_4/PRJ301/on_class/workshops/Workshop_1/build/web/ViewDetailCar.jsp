<%-- 
    Document   : ViewDetailCar
    Created on : Jan 30, 2026, 3:33:17 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>View Detail Car - Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css"/>
    </head>

    <body>
        <div class="container main-content">
            <h1>Car List</h1>
            <h4>Car</h4>
            <hr/>

            <dl class="row">
                <c:set var="dto" value="${requestScope.CAR_DETAIL}" />
                <c:if test="${not empty dto}">
                    <dt class="col-sm-2">CarID</dt>
                    <dd class="col-sm-10">${dto.carID}</dd>

                    <dt class="col-sm-2">CarName</dt>
                    <dd class="col-sm-10">${dto.carName}</dd>

                    <dt class="col-sm-2">Manufacturer</dt>
                    <dd class="col-sm-10">${dto.manufacturer}</dd>

                    <dt class="col-sm-2">Price</dt>
                    <dd class="col-sm-10">${dto.price}</dd>

                    <dt class="col-sm-2">ReleasedYear</dt>
                    <dd class="col-sm-10">${dto.releasedYear}</dd>
                </c:if>
            </dl>

        </div>

        <div class="detail_actions">
            <c:url var="editLink" value="DispatchServlet">
                <c:param name="btAction" value="Edit" />
                <c:param name="txtCarID" value="${dto.carID}" />
                <c:param name="txtCarName" value="${dto.carName}" />
                <c:param name="txtManufacturer" value="${dto.manufacturer}" />
                <c:param name="txtPrice" value="${dto.price}" />
                <c:param name="txtReleasedYear" value="${dto.releasedYear}" />
            </c:url>
            <a href="${editLink}">Edit</a> | 
            <a href="DispatchServlet">Back to List</a>
        </div>
    </body>
</html>
