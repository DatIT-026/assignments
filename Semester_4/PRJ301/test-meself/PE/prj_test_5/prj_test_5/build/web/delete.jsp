<%-- 
    Document   : Delete
    Created on : Mar 16, 2026, 10:11:37 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Management - Delete Component</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO or sessionScope.USER_INFO.roleID ne 'AD'}">
            <c:set var="ERROR" value="No Permission" scope="request"/>
            <c:redirect url="componentList.jsp"/>
        </c:if>

        <h1>Welcome Admin: ${sessionScope.USER_INFO.fullName}</h1>
        <a href="home.jsp">Back to Home</a> | <a href="MainController?btAction=Logout">Logout</a>
        <hr/>

        <h3>Management - Delete Component</h3>
        
        <form action="MainController" method="GET">
            Search to Delete: 
            <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="hidden" name="fromPage" value="delete" />
            <input type="submit" name="btAction" value="Search" />
        </form>

        <c:if test="${not empty requestScope.COMP_LIST}">
            <table border="1" style="margin-top: 20px">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Unit Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${requestScope.COMP_LIST}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.itemID}</td>
                            <td>${dto.itemName}</td>
                            <td>${dto.category}</td>
                            <td>${dto.unitPrice}</td>
                            <td>
                                <c:url var="deleteLink" value="MainController">
                                    <c:param name="btAction" value="Delete"/>
                                    <c:param name="itemID" value="${dto.itemID}"/>
                                    <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                    <c:param name="fromPage" value="delete"/>
                                </c:url>
                                <a href="${deleteLink}" onclick="return confirm('Confirm delete?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>