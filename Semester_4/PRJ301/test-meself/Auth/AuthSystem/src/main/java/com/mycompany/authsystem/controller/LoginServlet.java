/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.authsystem.controller;

import com.mycompany.authsystem.error.ValidationError;
import com.mycompany.authsystem.infrastructure.ValidationUtil;
import com.mycompany.authsystem.model.UserDAO;
import com.authsystem.authsystem.model.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
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
        request.setCharacterEncoding("UTF-8");
        
        String url = LOGIN_PAGE;
        UserDAO dao = null;
        
        // 1. Get form param
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        
        try {
            // Validation input
            ValidationError errors = ValidationUtil
                    .validationLogin(username, password);
            if (errors.hasErrors()) {
                request.setAttribute("VALIDATION_ERROR", dao);
            } else {
                dao = new UserDAO();
                UserDTO user = dao.checkLogin(username, password);
                
                if (user != null) {
                    // check if account is verified
                    if (!user.isIsVerified()) {
                        // Generate verification code
                        
                        // Calculate expire time
                        
                        // Update reset code in db
                        
                        // send verification code
                        
                        // TODO
                        
                    } else {
                        // Login successfully
                        HttpSession session = request.getSession();
                        session.setAttribute("USER_INFO", user);
                        
                        // Update last login
                        dao.updateLastLogin(user.getUserId());
                        
                        url = DASHBOARD_PAGE;
                    }
                } else {
                    request.setAttribute("ACCOUNT_ERROR", "Invalid username or password!");
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
