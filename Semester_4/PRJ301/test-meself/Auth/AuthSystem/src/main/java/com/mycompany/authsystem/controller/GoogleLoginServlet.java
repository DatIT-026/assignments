/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.authsystem.controller;

import com.authsystem.authsystem.model.UserDTO;
import com.mycompany.authsystem.error.ValidationError;
import com.mycompany.authsystem.infrastructure.EmailService;
import com.mycompany.authsystem.infrastructure.GoogleOAuthUtil;
import com.mycompany.authsystem.model.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author datto
 */
@WebServlet(name = "GoogleLoginServlet", urlPatterns = {"/GoogleLoginServlet"})
public class GoogleLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static final String LOGIN_PAGE = "views/login.jsp";
    private static final String DASHBOARD_PAGE = "views/dashboard.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = LOGIN_PAGE;
        UserDAO dao = null;
        
        try {
            String idTokenString = request.getParameter("idToken");
            
            if (idTokenString == null || idTokenString.trim().isEmpty()) {
                request.setAttribute("ERROR_MESSAGE", "Invalid Google Token");
            } else {
                GoogleOAuthUtil.GoogleUserInfo googleInfo = GoogleOAuthUtil.verifyGoogleToken(idTokenString);
                
                if (googleInfo == null) {
                    request.setAttribute("ERROR_MESSAGE", "Failed to verify Google account");
                    url = LOGIN_PAGE;
                } else if (!googleInfo.isEmailVerified()) {
                    request.setAttribute("ERROR_MESSAGE", "Google email is not verified");
                    url = LOGIN_PAGE;
                } else {
                    dao = new UserDAO();
                    
                    UserDTO user = dao.findBySocialID("Google: " + googleInfo.getGoogleId());
                    
                    if (user == null) {
                        user = dao.findByEmail(googleInfo.getEmail());
                        
                        if (user != null) {
                            request.setAttribute("ERROR_MESSAGE", "An account with this email is already existed! Please Login with Username and Password");
                        } else {
                            // create new user account
                            user = new UserDTO();
                            user.setSocialId("Google: " + googleInfo.getGoogleId());
                            user.setEmail(googleInfo.getEmail());
                            user.setFullName(googleInfo.getName());
                            user.setProfilePicture(googleInfo.getPictureUrl());
                            
                            String username = generateUsernameFromEmail(googleInfo.getEmail());
                            
                            user.setUsername(username);
                            user.setPassword(null);
                            user.setIsVerified(true);
                            user.setVerificationCode(null);
                            
                            boolean created = dao.createUser(user);
                            
                            if (created) {
                                user = dao.findBySocialID("Google: " + googleInfo.getGoogleId());
                                
                                // TODO: send welcome email
                                ServletContext context = getServletContext();
                                EmailService.sendSocialWelcomeEmail(user.getEmail(), user.getFullName(), "Google", context);
                                
                                // Login
                                HttpSession session = request.getSession();
                                session.setAttribute("USER_INFO", user);
                                dao.updateLastLogin(user.getUserId());
                                url = DASHBOARD_PAGE;
                            } else {
                                request.setAttribute("ERROR_MESSAGE", "Failed to create account");
                                url = LOGIN_PAGE;
                            }
                        }
                    } else {
                        // user exist -> directly login
                        HttpSession session = request.getSession();
                        session.setAttribute("USER_INFO", user);
                        dao.updateLastLogin(user.getUserId());
                        url = DASHBOARD_PAGE;
                    }
                }
            }
        } catch (SQLException ex) {
            log("LoginServlet_SQL: " + ex.getMessage(), ex);
            ValidationError err = new ValidationError();
            err.setGeneralError("Problem is occured when working with Database. Please try again later.");
            request.setAttribute("VALIDATION_ERROR", err);
            url = LOGIN_PAGE;
        } catch (NamingException ex) {
            log("LoginServlet_Naming: " + ex.getMessage(), ex);
            ValidationError err = new ValidationError();
            err.setGeneralError("Data Source Not Found. Please try again later.");
            request.setAttribute("VALIDATION_ERROR", err);
            url = LOGIN_PAGE;
        } catch (Exception ex) {
            log("LoginServlet: " + ex.getMessage(), ex);
            ValidationError err = new ValidationError();
            err.setGeneralError("System error occurred. Please try again later.");
            request.setAttribute("VALIDATION_ERROR", err);
            url = LOGIN_PAGE;
        } finally {
            if (dao != null) dao.close();
            
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }
    
    private String generateUsernameFromEmail(String email) {
        String username = email.substring(0, email.indexOf("0"));
        username = username.replaceAll("[^a-zA-Z0-9_]", "_");
        
        int random = (int) (Math.random() * 10000);
        username = username + random;
        
        return username;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
