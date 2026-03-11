<%-- 
    Document   : search
    Created on : Jan 5, 2026, 10:11:53 AM
    Author     : datto
--%>

<%@page import="firehelperedu.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Search Courses</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="container">
            <div class="card">
                <h2>Search Courses</h2>
                <form action="DispatchServlet" class="form-group" style="display: flex; gap: 10px;">
                    <input type="text" name="txtSearchValue" 
                           value="${param.txtSearchValue}" 
                           placeholder="Enter Student ID or Name...">
                    <input type="submit" value="Search" name="btAction" id="buttonn2"/>
                </form>

                <c:set var="searchValue" value="${param.txtSearchValue}" />
                <c:if test="${not empty searchValue}">
                    <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
                    <c:if test="${not empty result}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>courseID</th>
                                    <th>courseName</th>
                                    <th>credits</th>
                                    <th>description</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${result}" varStatus="counter">
                                    <tr>
                                        <td>
                                            ${counter.count}
                                        </td>
                                        <td>
                                            ${dto.courseID}
                                        </td>
                                        <td>
                                            ${dto.courseName}
                                        </td>
                                        <td>
                                            ${dto.credits}
                                        </td>
                                        <td>
                                            ${dto.description}
                                        </td>
                                    </c:forEach>
                            </tbody>
                        </table>

                    </c:if>
                    <c:if test="${empty result}">
                        <h2>
                            <font color="red">
                            Nothing here!
                            </font>
                        </h2>
                    </c:if>
                </c:if>

                <!--
                        <h2>Search Courses</h2>
                        <form action="DispatchServlet" class="form-group" style="display: flex; gap: 10px;">
                            <input type="text" name="txtSearchValue" 
                                   value="<%= request.getParameter("txtSearchValue")%>" 
                                   placeholder="Enter Student ID or Name...">
                            <input type="submit" value="Search" name="btAction" id="buttonn2"/>
                        </form>
        
                <%
                    String searchValue = request.getParameter("txtSearchValue");
                    if (searchValue != null) {
                        List<RegistrationDTO> result
                                = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");

                        if (result != null) {
                %>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>courseID</th>
                            <th>courseName</th>
                            <th>credits</th>
                            <th>description</th>
                        </tr>
                    </thead>
                    <tbody>
                <%
                    int count = 1;
                    for (RegistrationDTO dto : result) {
                %>
                <tr>
                    <td>
                <%= count++%>
                </td>
                <td>
                <%= dto.getCourseID()%>
                </td>
                <td>
                <%= dto.getCourseName()%>
                </td>
                <td>
                <%= dto.getCredits()%>
                </td>
                <td>
                <%= dto.getDescription()%>
                </td>
            </tr>
                <%
                    }
                %>
            </tbody>
        </table>
                <%
                } else {
                %>
                <h2>
                    <font color="red">
                        Nothing here!
                    </font>
                </h2>
                <%
                        }
                    }
                %>
                -->
            </div>
        </div>
    </body>
</html>
