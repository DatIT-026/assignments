<%-- 
    Document   : error_404
    Created on : Jan 21, 2026, 11:07:53 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>User Authentication System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error_404_style.css"/>
  </head>
  <body>
    <div class="error-container">
      <div class="error-icon">🔍</div>
      <div class="error-code">404</div>
      <h1>Oops! Page not found</h1>
      <p>The page you are looking for does not exist or has been moved</p>

      <div class="error-details">
        <strong>request URL:</strong>
        <%=request.getAttribute("javax.servlet.error.request_uri") != null ?
        request.getAttribute("javax.servlet.error.request_uri") : "N/A"%>
      </div>

      <div class="suggestions">
        <h3>💡 Suggestion:</h3>
        <ul>
          <li>Check if the URL is correct</li>
          <li>The Page may have been deleted or moved</li>
          <li>Please search or return to the homepage</li>
          <li>Contact admin if you think this is bug</li>
        </ul>
      </div>

      <div class="button-group">
        <a
          href="<%= request.getContextPath()%>/DispatchServlet"
          class="btn btn-primary"
        >
          🏠 Go to home page
        </a>
        <br />
        <a href="javascript:history.back()" class="btn btn-secondary">
          ⬅ Go back
        </a>
      </div>

      <div class="error-info">
        <p>Error Code: 404 | User Authentication System</p>
        <p>If the problem persists, please contact the admin</p>
      </div>
    </div>
  </body>
</html>