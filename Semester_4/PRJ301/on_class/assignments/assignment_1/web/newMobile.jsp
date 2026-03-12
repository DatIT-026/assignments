<%-- 
    Document   : newMobile
    Created on : Mar 11, 2026, 7:21:19 AM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Mobile</title>
        <style>
            font {
                margin-left: 10px;
            }

            .form-group {
                display: flex;
                flex-wrap: wrap;
                margin-bottom: 15px;
                margin-right: 5px;
                align-items: center;
            }
            .form-group label {
                width: 130px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO || sessionScope.USER_INFO.role != 1}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <h1>Add New Mobile Device</h1>
        <a href="listDevice.jsp" style="color: blue;">Back to Search</a>
        <hr />

        <div>
            <c:set var="err" value="${requestScope.MOBILE_ERROR}" />

            <form action="MainController" method="POST">
                <!-- Mobile Id -->
                <div class="form-group">
                    <label for="mobileId">ID:</label>
                    <input type="text" name="mobileId" value="${param.mobileId}" required maxlength="10" /> <br />
                    <c:if test="${not empty err.mobileIdError}">
                        <font color="red">${err.mobileIdError}</font><br />
                    </c:if>
                    <c:if test="${not empty err.mobileIdDuplicatedError}">
                        <font color="red">${err.mobileIdDuplicatedError}</font><br />
                    </c:if>
                </div>
                
                <!-- Device Name -->
                <div class="form-group">
                    <label for="mobileName">Device Name:</label>
                    <input type="text" name="mobileName" value="${param.mobileName}" required maxlength="20" /> <br />
                    <c:if test="${not empty err.mobileNameError}">
                        <font color="red">${err.mobileNameError}</font><br />
                    </c:if>
                </div>
                
                <!-- Description -->
                <div class="form-group">
                    <label for="description">Description:</label>
                    <input type="text" name="description" value="${param.description}" maxlength="50" /> <br />
                </div>

                <!-- Price -->
                <div class="form-group">
                    <label for="price">Price:</label>
                    <input type="text" name="price" value="${param.price}" required /> <br />
                    <c:if test="${not empty err.priceError}">
                        <font color="red">${err.priceError}</font><br />
                    </c:if>
                </div>

                <!-- Year -->
                <div class="form-group">
                    <label for="yearOfProduction">Year:</label>
                    <input type="text" name="yearOfProduction" value="${param.yearOfProduction}" required /> <br />
                    <c:if test="${not empty err.yearOfProductionError}">
                        <font color="red">${err.yearOfProductionError}</font><br />
                    </c:if>
                </div>

                <!-- Quantity -->
                <div class="form-group">
                    <label for="quantity">Quantity:</label>
                    <input type="text" name="quantity" value="${param.quantity}" required /> <br />
                    <c:if test="${not empty err.quantityError}">
                        <font color="red">${err.quantityError}</font><br />
                    </c:if>
                </div>

                <!-- Not for sale -->
                <div class="form-group">
                    <label for="notSale">Not for Sale:</label>
                    <input type="checkbox" name="notSale" value="true" 
                        <c:if test="${param.notSale != null}">checked</c:if> 
                    /> 
                </div>

                <div class="form-group" style="margin-top: 15px;">
                    <input type="submit" value="Insert" name="btAction" />
                </div>
            </form>

            <c:if test="${not empty requestScope.ERROR}">
                <p><font color="red"><b>${requestScope.ERROR}</b></font></p>
            </c:if>
            <c:if test="${not empty requestScope.SUCCESS}">
                <p><font color="green"><b>${requestScope.SUCCESS}</b></font></p>
            </c:if>
        </div>
    </body>
</html>