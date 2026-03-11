<%-- 
    Document   : petList
    Created on : Mar 6, 2026, 9:58:08 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Pet List</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER_INFO}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <h1>Welcome ${sessionScope.USER_INFO.fullName} (${sessionScope.USER_INFO.userID})</h1>

        <a href="MainController?btAction=Logout">Logout</a>

        <hr/>

        <form action="MainController" method="GET">
            Search Pet (Name or Breed): 
            <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" name="btAction" value="Search" />
        </form>

        <c:if test="${not empty requestScope.PET_LIST}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name - Breed</th>
                        <th>Age</th>
                        <th>Price</th>
                        <th>Description</th>
                        <c:if test="${sessionScope.USER_INFO.roleID == 'ST'}">
                            <th>Action</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="pet" items="${requestScope.PET_LIST}">
                        <tr>
                            <td>${pet.petID}</td>
                            <td>${pet.petName} - ${pet.breed}</td>
                            <td>${pet.age}</td>
                            <td>${pet.price}</td>
                            <td>${pet.description}</td>
                            <c:if test="${sessionScope.USER_INFO.roleID eq 'ST'}">
                                <td>
                                    <form action="updatePet.jsp" method="POST">
                                        <input type="hidden" name="petID" value="${pet.petID}" />
                                        <input type="hidden" name="petName" value="${pet.petName}" />
                                        <input type="hidden" name="breed" value="${pet.breed}" />
                                        <input type="hidden" name="age" value="${pet.age}" />
                                        <input type="hidden" name="price" value="${pet.price}" />
                                        <input type="hidden" name="description" value="${pet.description}" />
                                        <input type="submit" value="Update" />
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>