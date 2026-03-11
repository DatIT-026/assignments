<%-- 
    Document   : search
    Created on : Jan 14, 2026, 8:54:31 AM
    Author     : datto
--%>

<%@page import="firehelperedu.registration.CoursesDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Search Courses</title>
        <link rel="stylesheet" href="style.css">
    </head>
    
    <body>
        <div class="container">
            <font>Welcome, ${sessionScope.USER_INFO.fullname}</font> <br/>
            <font style="color: blue;">
                <c:url var="logoutLink" value="DispatchServlet">
                    <c:param name="btAction" value="Logout" />
                </c:url>
                <a href="${logoutLink}">Logout</a>
            </font>
            
            <div class="card">
                <h2>Search Courses</h2>
                <form action="DispatchServlet" class="form-group" style="display: flex; gap: 10px;">
                    <input type="text" name="txtSearchValue" value="" placeholder="Enter Course ID or Name...">
                    <input type="submit" value="Search" name="btAction" id="buttonn2"/>
                </form>
                
                <c:set var="searchValue" value="${param.txtSearchValue}"/>
                <c:if test="${not empty searchValue}">
                    <c:set var="result" value="${requestScope.SEARCH_RESULT}" />
                    <c:if test="${not empty result}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>CourseID</th>
                                    <th>CourseName</th>
                                    <th>Credits</th>
                                    <th>Description</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${result}" varStatus="counter">
                                <form action="DispatchServlet" method="POST">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${dto.course_id}</td>
                                        <td>${dto.course_name}</td>
                                        <td>${dto.credits}</td>
                                        <td>${dto.description}</td>
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
