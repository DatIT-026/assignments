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
        
        <ul>
            <li>Search by name</li>
            <li><a href="MainController?action=Logout">Logout</a></li>
        </ul>
        
        <hr/>

        <form action="MainController" method="GET">
            <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" name="action" value="Search" />
        </form>

        <c:if test="${not empty requestScope.FASHION_LIST}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Size</th>
                        <c:if test="${sessionScope.USER_INFO.roleID == 'AD'}">
                            <th>Status</th>
                            <th>Tool</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${requestScope.FASHION_LIST}">
                        <c:if test="${sessionScope.USER_INFO.roleID eq 'AD' or dto.status == true}">
                        <tr>
                            <td>${dto.id}</td>
                            <td>${dto.name}</td>
                            <td>${dto.description}</td>
                            <td>${dto.price}</td>
                            <td>${dto.size}</td>
                            <c:if test="${sessionScope.USER_INFO.roleID eq 'AD'}">
                                <td>${dto.status}</td>
                                <td>
                                    <c:url var="updateLink" value="update.jsp">
                                        <c:param name="paramId" value="${dto.id}"/>
                                        <c:param name="paramName" value="${dto.name}"/>
                                        <c:param name="paramDescription" value="${dto.description}"/>
                                        <c:param name="paramPrice" value="${dto.price}"/>
                                        <c:param name="paramSize" value="${dto.size}"/>
                                        <c:param name="paramStatus" value="${dto.status}"/>
                                        <c:param name="paramLastSearchValue" value="${param.txtSearchValue}"/>
                                    </c:url>

                                    <a href="${updateLink}">Update</a>
                                </td>
                            </c:if>
                        </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <c:if test="${empty requestScope.FASHION_LIST}">
            <c:if test="${not empty param.txtSearchValue}">
                <p style="color: red; font-weight: bold;">No data matching the search criteria found!</p>
            </c:if>
        </c:if>

    </body>
</html>
