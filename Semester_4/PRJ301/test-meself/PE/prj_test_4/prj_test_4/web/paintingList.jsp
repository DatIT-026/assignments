<%-- 
    Document   : paintingList
    Created on : Mar 13, 2026, 6:16:06 PM
    Author     : Miniks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ARTWORK MANAGEMENT SYSTEM</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <h1>
            Welcome ${sessionScope.USER_INFO.fullName} 
            (${sessionScope.USER_INFO.roleID == 'AD' ? 'ADMIN' : 'CUSTOMER'})
        </h1>
        
        <a href="MainController?btAction=Logout">Logout</a>
        
        <h2>Search Page</h2>

        <form action="MainController">
            Search: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" />
        </form>

        <br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.PAINTING_LIST}"/>
            <c:set var="hasValidItem" value="false"/>
            <c:forEach var="check" items="${result}">
                <c:if test="${check.price > 0}">
                    <c:set var="hasValidItem" value="true"/>
                </c:if>
            </c:forEach>

            <c:choose>
                <c:when test="${not empty result && hasValidItem}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th><th>ID</th><th>Description</th>
                                <th>Creator</th><th>Height</th><th>Width</th>
                                <th>Price</th><th>Status</th><th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                                <c:if test="${dto.price > 0}">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${dto.paintingID}</td>
                                        <td>${dto.description}</td>
                                        <td>${dto.creator}</td>
                                        <td>${dto.height}</td>
                                        <td>${dto.width}</td>
                                        <td>${dto.price}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${dto.isAvailable}">
                                                    <span style="color: green;">Available</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span style="color: red;">Sold Out</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:if test="${sessionScope.USER_INFO.roleID eq 'AD'}">
                                                <form action="MainController" method="POST"> 
                                                    <input type="submit" name="btAction" value="Update" />
                                                    <input type="hidden" name="paintingID" value="${dto.paintingID}" />
                                                    <input type="hidden" name="lastSearchValue" value="${param.txtSearchValue}" />
                                                </form>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <h2 style="color: red;">No record found!</h2>
                </c:otherwise>
            </c:choose>
        </c:if>
    </body>
</html>
