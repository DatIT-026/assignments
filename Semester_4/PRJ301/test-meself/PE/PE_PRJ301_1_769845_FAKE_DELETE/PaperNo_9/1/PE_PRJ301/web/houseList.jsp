<%-- 
    Document   : admin
    Created on : Mar 1, 2022, 8:29:12 PM
    Author     : hd
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>House List</title>
    </head>
    <body>
        <!--your code here-->
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp" />
        </c:if>

        <h1>Welcome ${sessionScope.USER_INFO.name}</h1>

        <ul>
            <li>Search by name</li>
            <li><a href="MainController?action=logout">Logout</a></li>
        </ul>

        <hr/>

        <form action="MainController" method="GET">
            <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" name="action" value="search" />
        </form>

        <c:if test="${not empty requestScope.HOUSE_LIST}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Size</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${requestScope.HOUSE_LIST}" varStatus="counter">
                        <c:if test="${dto.status == true}" >
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.id}</td>
                                <td>${dto.name}</td>
                                <td>${dto.description}</td>
                                <td>${dto.price}</td>
                                <td>${dto.size}</td>
                                <td>
                                    <input type="checkbox" name="txtStatus" value="true" <c:if test="${dto.status}">checked</c:if> />
                                </td>
                                <td>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="paramId" value="${dto.id}" />
                                        <input type="hidden" name="paramSearchValue" value="${param.txtSearchValue}" />
                                        <input type="submit" name="action" value="remove" 
                                               onclick="return confirm('Are you sure you want to delete this house?')" />
                                    </form>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty requestScope.HOUSE_LIST}">
            <c:if test="${not empty param.txtSearchValue or not empty param.paramSearchValue}">
                <p style="color: red; font-weight: bold;">No data matching the search criteria found!</p>
            </c:if>
        </c:if>

    </body>
</html>