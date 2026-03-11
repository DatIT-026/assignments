<%-- 
    Document   : index
    Created on : Jan 14, 2026, 7:35:58 AM
    Author     : datto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Student Portal - Home</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <nav>
            <div class="logo">FireHelper Edu</div>
            <ul>
                <li><a href="search.jsp">Search</a></li>
            </ul>
        </nav>

        <div class="container">
            <font>Welcome, ${sessionScope.USER_INFO.fullname} (${sessionScope.USER_INFO.role ? 'Admin' : 'User'})</font>
            
            <div class="card">
                    <h1>Welcome to Student Management System</h1>
                    <p style="margin-top: 1rem; color: #64748b;">
                        Manage student records, track academic progress, and handle administrative tasks efficiently.
                    </p>
                    
                    <c:if test="${sessionScope.USER_INFO.role}">
                        <c:set var="result" value="${requestScope.ACCOUNT_RESULT}" />
                        <c:if test="${not empty result}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>ID</th>
                                    <th>Username</th>
                                    <th>Fullname</th>
                                    <th>Password</th>
                                    <th>Address</th>
                                    <th>Date created</th>
                                    <th>Admin?</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${result}" varStatus="counter">
                                <form action="DispatchServlet" method="POST">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${dto.id}</td>
                                        <td>${dto.username}</td>
                                        <td>
                                            <input type="text" name="txtFullName" value="${dto.fullname}" />
                                        </td>
                                        <td>
                                            <input type="password" name="txtPassword" value="${dto.password}" />
                                        </td>
                                        <td>
                                            <input type="text" name="txtAddress" value="${dto.address}" />
                                        </td>
                                        <td>${dto.dateCreated}</td>
                                        <td>
                                            <input type="text" name="txtIsAdmin" value="${dto.role}" 
                                                   ${dto.id == sessionScope.USER_INFO.id ? 'readonly style="background-color: #e2e8f0;"' : '    '} />
                                        </td>
                                        
                                        <td>
                                            <c:url var="deleteLink" value="DispatchServlet" >
                                                <c:param name="btAction" value="Delete" />
                                                <c:param name="pk" value="${dto.username}" />
                                                <c:param name="lastSearchValue" value="${param.txtSearchValue}" />
                                            </c:url>
                                            <a href="${deleteLink}">Delete</a>
                                        </td>

                                        <td>
                                            <input type="submit" name="btAction" value="Update" />
                                            <input type="hidden" name="lastStudentID" value="${dto.id}" />
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
                                No Record is matched!!!
                                </font>
                            </h2>
                        </c:if>
                    </c:if>
            </div>
        </div>
    </body>
</html>
