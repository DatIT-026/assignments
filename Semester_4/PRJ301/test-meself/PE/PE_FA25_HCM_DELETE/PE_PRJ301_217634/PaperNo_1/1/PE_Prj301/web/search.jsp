<%-- 
    Document   : search
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <!--your code here-->
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp" />
        </c:if>

        <h1>Welcome ${sessionScope.USER_INFO.fullName}</h1>

        <a href="MainController?action=Logout">Logout</a>

        <hr/>

        <form action="MainController" method="GET">
            Search University (Name): 
            <input type="text" name="paramSearchValue" value="${param.paramSearchValue}" />
            <input type="submit" name="action" value="Search" />
        </form>

        <c:if test="${not empty requestScope.UNIVERSITY_LIST}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>ShortName</th>
                        <th>FoundedYear</th>
                        <th>TotalFaculties</th>
                        <th>City</th>
                        <th>Region</th>
                        <th>Type</th>
                        <c:if test="${sessionScope.USER_INFO.roleID eq 'QL'}">
                            <th>isDraft</th>
                            <th>Action</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${requestScope.UNIVERSITY_LIST}">
                        <tr>
                            <td>${dto.id}</td>
                            <td>${dto.name}</td>
                            <td>${dto.description}</td>
                            <td>${dto.shortName}</td>
                            <td>${dto.foundedYear}</td>
                            <td>${dto.totalFaculties}</td>
                            <td>${dto.city}</td>
                            <td>${dto.region}</td>
                            <td>${dto.type}</td>
                            <c:if test="${sessionScope.USER_INFO.roleID eq 'QL'}">
                                <td>${dto.isDraft}</td>
                                <c:if test="${dto.isDraft}">
                                    <td>
                                        <c:url var="deleteLink" value="MainController">
                                            <c:param name="action" value="Delete"/>
                                            <c:param name="paramId" value="${dto.id}"/>
                                            <c:param name="paramLastSearchValue" value="${param.paramSearchValue}"/>
                                        </c:url>
                                        <a href="${deleteLink}">Delete</a>
                                    </td>
                                </c:if>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty requestScope.UNIVERSITY_LIST}">
            <c:if test="${not empty param.paramSearchValue}">
                <p style="color: red; font-weight: bold;">No data matching the search criteria found!</p>
            </c:if>
        </c:if>

    </body>
</html>
