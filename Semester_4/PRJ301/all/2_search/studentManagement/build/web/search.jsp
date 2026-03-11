<%-- 
    Document   : search
    Created on : Jan 5, 2026, 10:11:53 AM
    Author     : datto
--%>

<%@page import="firehelperedu.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <form action="SearchCourseName" class="form-group" style="display: flex; gap: 10px;">
                    <input type="text" name="txtSearchValue" 
                           value="<%= request.getParameter("txtSearchValue") %>" 
                           placeholder="Enter Student ID or Name...">
                    <input type="submit" value="Search" name="btAction" id="buttonn2"/>
                </form>

                <%
                    String searchValue = request.getParameter("txtSearchValue");
                    if (searchValue != null) {
                        List<RegistrationDTO> result = 
                            (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");

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
                                                    <%= count++ %>
                                                </td>
                                                <td>
                                                    <%= dto.getCourseID() %>
                                                </td>
                                                <td>
                                                    <%= dto.getCourseName() %>
                                                </td>
                                                <td>
                                                    <%= dto.getCredits() %>
                                                </td>
                                                <td>
                                                    <%= dto.getDescription() %>
                                                </td>
                                            </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                <%
                        }
                    }
                %>

            </div>
        </div>
    </body>
</html>
