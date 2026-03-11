<%-- 
    Document   : search
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <!--your code here-->
        <font>
            Welcome, ${sessionScope.USER_INFO.fullName}
        </font><br/>
        <font style="color: blue">
        <c:url var="logoutLink" value="MainController" >
            <c:param name="btAction" value="Logout" />
        </c:url>
        <a href="${logoutLink}">Logout</a>
        </font>
        
        <h1>Search Page</h1>
        <form action="MainController" >
            Search <input type="text" name="txtSearchValue" 
                          value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" />
        </form><br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.ROOMS_LIST}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Price</th>
                            <th>Location</th>
                            <th>Description</th>
                            <th>postedDate</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="room" items="${result}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${room.id}</td>
                                <td>${room.title}</td>
                                <td>${room.location}</td>
                                <td>${room.description}</td>
                                <td>${room.postedDate}</td>
                                <td>${room.price}</td>
                                <td>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="id" value="${room.id}" />
                                        <input type="hidden" name="lastSearch" value="${param.txtSearchValue}" />
                                        <input type="submit" name="btAction" value="Delete" />
                                   </form>
                                </td>
                            </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>
                <font color="red">
                No record!
                </font>
            </h2>
        </c:if>
    </c:if>
    </body>
</html>
