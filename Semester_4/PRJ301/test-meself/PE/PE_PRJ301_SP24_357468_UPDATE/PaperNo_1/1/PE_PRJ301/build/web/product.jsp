<%-- 
    Document   : admin
    Created on : Mar 1, 2022, 8:29:12 PM
    Author     : hd
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>product Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp" />
        </c:if>

        <h1>Welcome ${sessionScope.USER_INFO.fullName}</h1>

        <a href="MainController?action=Logout">Logout</a>

        <hr/>

        <form action="MainController" method="GET">
            Search Product by description: 
            <input type="text" name="paramSearchValue" value="${param.paramSearchValue}" />
            <input type="submit" name="action" value="Search" />
        </form>

        <c:if test="${not empty requestScope.PRODUCT_LIST}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Size</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${requestScope.PRODUCT_LIST}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.id}</td>
                            <td>${dto.name}</td>
                            <td>${dto.price}</td>
                            <td>${dto.size}</td>
                            <td>${dto.description}</td>
                            <td>
                                <form action="update.jsp" method="POST">
                                    <input type="hidden" name="paramId" value="${dto.id}"/>
                                    <input type="hidden" name="paramName" value="${dto.name}"/>
                                    <input type="hidden" name="paramDescription" value="${dto.description}"/>
                                    <input type="hidden" name="paramPrice" value="${dto.price}"/>
                                    <input type="hidden" name="paramSize" value="${dto.size}"/>
                                    <input type="hidden" name="paramLastSearchValue" value="${param.paramSearchValue}"/>
                                    <input type="submit" value="Update"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty requestScope.PRODUCT_LIST}">
            <c:if test="${not empty param.paramSearchValue}">
                <p style="color: red; font-weight: bold;">No data matching the search criteria found!</p>
            </c:if>
        </c:if>

    </body>
</html>