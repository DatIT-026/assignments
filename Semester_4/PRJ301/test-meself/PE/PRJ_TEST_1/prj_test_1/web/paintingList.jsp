<%-- 
    Document   : paintingList
    Created on : Jan 13, 2026, 10:11:54 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>painting list</title>
    </head>

    <body>
        <font>
        Welcome, ${sessionScope.USER_INFO.fullname}(${sessionScope.USER_INFO.userID})
        </font><br/>
        <font style="color: blue">
        <c:url var="logoutLink" value="main_controller" >
            <c:param name="btAction" value="Logout" />
        </c:url>
        <a href="${logoutLink}">Logout</a>
        </font>

        <h1>Search Page</h1>
        <form action="main_controller" >
            Search <input type="text" name="txtSearchValue" 
                          value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" />
        </form><br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.PAINTING_LIST}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>ID</th>
                            <th>Description</th>
                            <th>Height</th>
                            <th>Width</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="update.jsp" method="POST"> 
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.id}</td>
                                <td>${dto.description}</td>
                                <td>${dto.height}</td>
                                <td>${dto.width}</td>
                                <td>${dto.price}</td>
                                <td>
                                    <c:if test="${sessionScope.USER_INFO.roleID == 'AD'}">
                                        <input type="submit" value="Update" />
                                        <input type="hidden" name="id" value="${dto.id}" />
                                        <input type="hidden" name="creator" value="${dto.creator}" />
                                        <input type="hidden" name="description" value="${dto.description}" />
                                        <input type="hidden" name="height" value="${dto.height}" />
                                        <input type="hidden" name="width" value="${dto.width}" />
                                        <input type="hidden" name="price" value="${dto.price}" />
                                        <input type="hidden" name="lastSearchValue" value="${param.txtSearchValue}" />
                                    </c:if>
                                </td>
                            </tr>
                        </form>
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
