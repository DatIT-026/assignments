<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Car</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css"/>
    </head>
    <body>
        <div class="container main-content">
            <h1>Update Car</h1>
            <hr />
            <div class="form-container">
                <c:set var="err" value="${requestScope.UPDATE_ERROR}" />
                <form action="DispatchServlet">
                    <!-- CarId -->  
                    <div class="form-group">
                        <label for="CarName">Car Id</label>
                        <input type="text" name="txtCarID" value="${param.txtCarID}" readonly="true"/> <br />
                    </div>

                    <!-- CarName -->              
                    <div class="form-group">
                        <label for="CarName">Car Name</label>
                        <input type="text" name="txtCarName" value="${param.txtCarName}" required /> <br />
                        <c:if test="${not empty err.carNameLengthErr}">
                            <font color="red">${err.carNameLengthErr}</font><br />
                        </c:if>
                    </div>

                    <!-- Manufacturer -->
                    <div class="form-group">
                        <label for="Manufacturer">Manufacturer</label>
                        <input type="text" name="txtManufacturer" value="${param.txtManufacturer}" required /> <br />
                        <c:if test="${not empty err.manufacturerLengthErr}">
                            <font color="red">${err.manufacturerLengthErr}</font><br />
                        </c:if>
                    </div>

                    <!-- Price -->
                    <div class="form-group">
                        <label for="Price">Price</label>
                        <input type="text" name="txtPrice" value="${param.txtPrice}" required /> <br />
                        <c:if test="${not empty err.priceFormatErr}">
                            <font color="red">${err.priceFormatErr}</font><br />
                        </c:if>
                    </div>


                    <!-- ReleasedYear -->
                    <div class="form-group">
                        <label for="ReleasedYear">ReleasedYear</label>
                        <input type="text" name="txtReleasedYear" value="${param.txtReleasedYear}" required /> <br />
                        <c:if test="${not empty err.releasedYearFormatErr}">
                            <font color="red">${err.releasedYearFormatErr}</font><br />
                        </c:if>
                    </div>

                    <input type="submit" value="Save" name="btAction" class="btn-save" />

                </form>
            </div>
        </div>
    </body>
</html>
