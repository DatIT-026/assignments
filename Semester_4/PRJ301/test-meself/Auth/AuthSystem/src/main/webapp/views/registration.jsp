<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : registration
    Created on : Mar 11, 2026, 10:09:21 PM
    Author     : datto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Authentication System</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration_style.css"/>
    </head>
    <body>
        <div class="registration-contatiner">
            <div class="logo">
                <h1>Create Account</h1>
                <h3>Join us today</h3>

                <c:if test="${not empty requestScope.VALIDATION_ERROR 
                              && not empty requestScope.VALIDATION_ERROR.generalError}">
                      <div class="error message">
                          ${requestScope.VALIDATION_ERROR.generalError}
                      </div>
                </c:if>

                <form action="DispatchServlet" method="POST">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" name="txtUsername" id="username" 
                               placeholder="Enter a username (3-20 chars)" 
                               value="${param.txtUsername}" />
                        <c:if test="${not empty requestScope.VALIDATION_ERROR 
                                      && not empty requestScope.VALIDATION_ERROR.usernameError}">
                              <div class="error message">
                                  <div>${requestScope.VALIDATION_ERROR.usernameError}</div>
                              </div>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text" name="txtEmail" id="email" 
                               placeholder="your.email@example.com" 
                               value="${param.txtEmail}" />
                        <c:if test="${not empty requestScope.VALIDATION_ERROR 
                                      && not empty requestScope.VALIDATION_ERROR.emailError}">
                              <div class="error message">
                                  <div>${requestScope.VALIDATION_ERROR.emailError}</div>
                              </div>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="fullName">Full Name</label>
                        <input type="text" name="txtEmail" id="fullName" 
                               placeholder="Your full name" 
                               value="${param.txtFullName}" />
                        <c:if test="${not empty requestScope.VALIDATION_ERROR 
                                      && not empty requestScope.VALIDATION_ERROR.fullNameError}">
                              <div class="error message">
                                  <div>${requestScope.VALIDATION_ERROR.fullNameError}</div>
                              </div>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" name="txtPassword" id="password" 
                               placeholder="Your password" />
                        <c:if test="${not empty requestScope.VALIDATION_ERROR 
                                      && not empty requestScope.VALIDATION_ERROR.passwordError}">
                              <div class="error message">
                                  <div>${requestScope.VALIDATION_ERROR.passwordError}</div>
                              </div>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="confirmPassword">Password</label>
                        <input type="text" name="txtConfirmPassword" id="confirmPassword" 
                               placeholder="re-enter your password" />
                        <c:if test="${not empty requestScope.VALIDATION_ERROR 
                                      && not empty requestScope.VALIDATION_ERROR.confirmPasswordError}">
                              <div class="error message">
                                  <div>${requestScope.VALIDATION_ERROR.confirmPasswordError}</div>
                              </div>
                        </c:if>
                    </div>

                    <button type="submit" name="action" value="Create Account" class="btn btn-primary">
                        Create Account
                    </button>
                </form>

                <div class="links">
                    Already have an account? <a href="DispatchServlet">Login here</a>
                </div>

            </div>
        </div>
    </body>
</html>
