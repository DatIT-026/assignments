<%-- 
    Document   : login
    Created on : Jan 21, 2026, 11:00:14 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Authentication System</title>
        <script src="https://accounts.google.com/gsi/client?hl=en" async defer></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login_style.css"/>
    </head>
    <body>
        <div class="login-container">
            <div class="login">
                <h1>😔 Welcome Back</h1>
                <h1>Login to your account</h1>
            </div>
            
            <c:if test="${not empty requestScope.VALIDATION_ERROR
                          && not empty requestScope.VALIDATION_ERROR.generalError}">
                  <div class="error-message">
                      ${requestScope.VALIDATION_ERROR.generalError}
                  </div>
            </c:if>
            
            <c:if test="${not empty requestScope.ERROR_MESSAGE}">
                  <div class="error-message">
                      ${requestScope.ERROR_MESSAGE}
                  </div>
            </c:if>
            
            <c:if test="${not empty requestScope.ACCOUNT_ERROR}">
                  <div class="error-message">
                      ${requestScope.ACCOUNT_ERROR}
                  </div>
            </c:if>
            
            <form action="DispatchServlet" method="POST">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" name="txtUsername" value="${param.txtUsername}"/>
                    
                    <c:if test="${not empty requestScope.VALIDATION_ERROR 
                                  && not empty requestScope.VALIDATION_ERROR.usernameError}">
                          <div class="field-error">
                              ${requestScope.VALIDATION_ERROR.usernameError}
                          </div>
                    </c:if>
                </div>
                    
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="txtPassword" value="${param.txtPassword}" />
                    
                    <c:if test="${not empty requestScope.VALIDATION_ERROR 
                                  && not empty requestScope.VALIDATION_ERROR.usernameError}">
                          <div class="field-error">
                              ${requestScope.VALIDATION_ERROR.passwordError}
                          </div>
                    </c:if>
                </div>
                    
                <button type="submit" name="action" value="Login" class="btn btn-primary" >
                    Login
                </button>
            </form>
                    
            <div class="divider">
                <span>OR CONTINUE WITH</span>
            </div>
                    
            <div class="social-login">
                <!-- Google Sign-in -->
                <div class="google-btn-container">
                    <div id="g_id_onload"
                         data-client_id="649509966409-tlqp0u67ggknss1qp5n2lmov9s1tmjl4.apps.googleusercontent.com"
                         data-callback="handleGoogleLogin"
                         data-auto_prompt="false">
                    </div>
                    
                    <div class="g_id_signin"
                         data-type="standard"
                         data-size="large"
                         data-theme="outline"
                         data-text="signin_with"
                         data-shape="rectangular"
                         data-logo_alignment="left"
                         data-width="370">
                    </div>
                </div>
            </div>
            
            <div class="links">
                <a href="">Forgot Password</a>
                <span class="seperator">|</span>
                <a href="DispatchServlet?action=Register">Create Account</a>
            </div>
        </div>
                    
        <form id="googleLoginForm" action="DispatchServlet" method="POST" style="display: none;">
            <input type="hidden" name="action" value="Google Login"/>
            <input type="hidden" name="idToken" id="idToken"/>
        </form>
                    
        <script>
            function handleGoogleLogin(response) {
                console.log("Google Sign-In successful");
                document.getElementById('idToken').value = response.credential;
                document.getElementById('googleLoginForm').submit();
            }
        </script>
    </body>
</html>
