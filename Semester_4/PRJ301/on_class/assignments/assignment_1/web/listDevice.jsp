<%-- 
    Document   : search
    Created on : Mar 11, 2026, 7:14:34 AM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Search Mobile</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <h1>Welcome ${sessionScope.USER_INFO.fullName} (${sessionScope.USER_INFO.userId})</h1>

        <div>
            <a href="MainController?btAction=Logout">Logout</a>
            <!-- user -->
            <c:if test="${sessionScope.USER_INFO.role == 0}">
                | <a href="MainController?btAction=DetailsCart">View Cart</a>
            </c:if>

            <!-- staff -->
            <c:if test="${sessionScope.USER_INFO.role == 1}">
                | <a href="MainController?btAction=Create">Add New Mobile</a>
            </c:if>
        </div>
        <hr/>

        <c:set var="err" value="${requestScope.MOBILE_ERROR}" />

        <c:if test="${sessionScope.USER_INFO.role == 1}">
            <h2>Staff Search</h2>
            <form action="MainController" method="POST">
                Search Mobile (by ID or Name): 
                <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" placeholder="Enter ID or Name"/>
                <input type="submit" name="btAction" value="Search" />
            </form>
        </c:if>

        <c:if test="${sessionScope.USER_INFO.role == 0}">
            <h2>User Search</h2>
            <form action="MainController" method="POST">
                Search by Price Range: <br/><br/>
                Min Price: <input type="number" step="0.01" name="minPrice" value="${param.minPrice}" required />
                Max Price: <input type="number" step="0.01" name="maxPrice" value="${param.maxPrice}" required />
                <input type="submit" name="btAction" value="Search" />
                <br/>
                <c:if test="${not empty err.priceError}">
                    <font color="red">${err.priceError}</font><br />
                </c:if>
            </form>
        </c:if>

        <c:if test="${not empty requestScope.MOBILE_LIST}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Mobile ID</th>
                        <th>Mobile Name</th>
                        <th>Year</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Not Sale</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="mobile" items="${requestScope.MOBILE_LIST}">
                    <form action="MainController" method="POST">
                        <tr>
                            <td>
                                ${mobile.mobileId}
                                <input type="hidden" name="mobileId" value="${mobile.mobileId}" />
                            </td>
                            <td>
                                ${mobile.mobileName}
                                <input type="hidden" name="mobileName" value="${mobile.mobileName}" />
                            </td>
                            <td>
                                <input type="text" name="yearOfProduction" value="${mobile.yearOfProduction}" />
                            </td>

                            <c:if test="${sessionScope.USER_INFO.role == 1}">
                                <td><input type="text" name="description" value="${mobile.description}" /></td>
                                <td><input type="number" step="0.01" name="price" value="${mobile.price}" /></td>
                                <td><input type="number" name="quantity" value="${mobile.quantity}" /></td>
                                <td>
                                    <input type="checkbox" name="notSale" value="true" 
                                           <c:if test="${mobile.notSale}">checked</c:if> />
                                    </td>
                                    <td>
                                        <input type="hidden" name="lastSearchValue" value="${param.txtSearchValue}" />
                                    <input type="submit" name="btAction" value="Update" />
                                    <input type="submit" name="btAction" value="Delete" />
                                </td>
                            </c:if>

                            <c:if test="${sessionScope.USER_INFO.role == 0}">
                                <td>
                                    ${mobile.description}
                                    <input type="hidden" name="description" value="${mobile.description}" />
                                </td>
                                <td>
                                    ${mobile.price}
                                    <input type="hidden" name="price" value="${mobile.price}" />
                                </td>
                                <td>
                                    ${mobile.quantity}
                                    <input type="hidden" name="quantity" value="${mobile.quantity}" />
                                </td>
                                <td>${mobile.notSale ? 'Yes' : 'No'}</td>
                                <td>
                                    <input type="hidden" name="minPrice" value="${param.minPrice}" />
                                    <input type="hidden" name="maxPrice" value="${param.maxPrice}" />
                                    <c:if test="${!mobile.notSale && mobile.quantity > 0}">
                                        <input type="submit" name="btAction" value="Add Cart" />
                                    </c:if>
                                    <c:if test="${mobile.notSale || mobile.quantity <= 0}">
                                        <span style="color:red;">Unavailable</span>
                                    </c:if>
                                </td>
                            </c:if>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
        <c:if test="${not empty err.yearOfProductionError}">
            <font color="red">${err.yearOfProductionError}</font><br />
        </c:if>

        <c:if test="${not empty err.quantityError}">
            <font color="red">${err.quantityError}</font><br />
        </c:if>

        <c:if test="${not empty err.priceError}">
            <font color="red">${err.priceError}</font><br />
        </c:if>
    </c:if>

    <c:if test="${empty requestScope.MOBILE_LIST}">
        <c:if test="${not empty param.txtSearchValue or not empty param.minPrice}">
            <p style="color: red; font-weight: bold;">No mobile devices found matching your search criteria!</p>
        </c:if>
    </c:if>
</body>
</html>