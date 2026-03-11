/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.authsystem.controller;

import com.authsystem.authsystem.model.UserDTO;
import com.mycompany.authsystem.error.ValidationError;
import com.mycompany.authsystem.infrastructure.EmailService;
import com.mycompany.authsystem.infrastructure.ValidationUtil;
import com.mycompany.authsystem.model.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
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
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String LOGIN_PAGE = "views/login.jsp";
    private static final String REGISTRATION_PAGE = "views/registration.jsp";
    private static final String VERIFICATION_SENT_PAGE = "views/verification-sent.jsp";
    
    private static final long VERFICATION_CODE_EXPIRY_TIME = 5;
    
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
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        String url = REGISTRATION_PAGE;
        UserDAO dao = null;
        
        String username = request.getParameter("txtUsername");
        String email = request.getParameter("txtEmail");
        String fullName = request.getParameter("txtFullName");
        String password = request.getParameter("txtPassword");
        String confirmPassword = request.getParameter("txtConfirmPassword");
        
        try {
            ValidationError errors = ValidationUtil.validateRegistration(
                    username, email, fullName, password, confirmPassword);
            if (errors.hasErrors()) {
                request.setAttribute("VALIDATION_ERROR", errors);
                url = REGISTRATION_PAGE;
                
                // check if username already exists
                UserDTO existingUser = dao.findByUsername(username);
                if (existingUser != null) {
                    errors.setUsernameError("Username already exists");
                }
                
                // check if email already exists
                existingUser = dao.findByEmail(email);
                if (existingUser != null) {
                    errors.setEmailError("Email already exists");
                }
                
                if (errors.hasErrors()) {
                    request.setAttribute("VALIDATION_ERROR", errors);
                    url = REGISTRATION_PAGE;
                } else {
                    // Generation verification code
                    String verificationCode = ValidationUtil.generateVerificationCode();
                            
                    UserDTO newUser = new UserDTO();
                    newUser.setUsername(username);
                    newUser.setEmail(email);
                    newUser.setFullName(fullName);
                    newUser.setPassword(password);
                    newUser.setVerificationCode(verificationCode);
                    newUser.setIsVerified(false);
                    newUser.setSocialId(null);
                    newUser.setProfilePicture(null);
                    
                    // Save to database
                    boolean created = dao.createUser(newUser);
                    
                    if (created) {
                        // calculate expiry time (5 minutes)
                        long expiryTime = System.currentTimeMillis()
                                + (VERFICATION_CODE_EXPIRY_TIME * 60 * 1000);
                        Timestamp expiry = new Timestamp(expiryTime);
                        
                        // update reset code in db
                        boolean updated = dao.updateVerificationCode(
                                email, verificationCode, expiry);
                        if (updated) {
                            // send verification email
                            ServletContext context = getServletContext();
                            boolean emailSent = EmailService.sendVerificationEmail(
                                    fullName, verificationCode, context);
                            
                            if (emailSent) {
                                request.setAttribute("SUCCESS_MESSAGE", 
                                        "Registration successfully! Please check your email for verification code.");
                                request.setAttribute("USER_EMAIL", email);
                                url = VERIFICATION_SENT_PAGE;
                            } else {
                                request.setAttribute("WARNING_MESSAGE", 
                                        "Account created but failed to send verification email.");
                                request.setAttribute("USER_EMAIL", email);
                                url = VERIFICATION_SENT_PAGE;
                            }
                            
                        } else {
                            errors.setGeneralError("Failed to process request. Please try again.");
                            request.setAttribute("VALIDATION_ERROR", errors);
                            url = LOGIN_PAGE;
                        }
                    } else {
                            errors.setGeneralError("Failed to create account. Please try again.");
                            request.setAttribute("VALIDATION_ERROR", errors);
                            url = REGISTRATION_PAGE;
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
