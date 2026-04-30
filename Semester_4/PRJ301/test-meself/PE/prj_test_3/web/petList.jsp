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

        <a href="MainController?action=Logout">Logout</a>

        <hr/>

        <form action="MainController" method="GET">
            Search Pet (Name or Breed): 
            <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" name="action" value="Search" />
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
                        <c:if test="${sessionScope.USER_INFO.roleID eq 'ST'}">
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
                                    <c:url var="updateLink" value="updatePet.jsp">
                                        <c:param name="txtId" value="${pet.petID}"/>
                                        <c:param name="txtPetName" value="${pet.petName}"/>
                                        <c:param name="txtBreed" value="${pet.breed}"/>
                                        <c:param name="txtAge" value="${pet.age}"/>
                                        <c:param name="txtPrice" value="${pet.price}"/>
                                        <c:param name="txtDescription" value="${pet.description}"/>
                                        <c:param name="paramLastSearchValue" value="${param.txtSearchValue}"/>
                                    </c:url>

                                    <a href="${updateLink}">Update</a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty requestScope.PET_LIST}">
            <c:if test="${not empty param.txtSearchValue}">
                <p style="color: red; font-weight: bold;">No data matching the search criteria found!</p>
            </c:if>
        </c:if>
    </body>
</html>