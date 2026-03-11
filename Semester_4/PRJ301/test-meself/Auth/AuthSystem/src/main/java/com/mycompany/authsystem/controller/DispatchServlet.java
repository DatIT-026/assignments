/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.authsystem.controller;

import com.sun.corba.se.spi.protocol.RequestDispatcherDefault;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author C00kies
 */
public class DispatchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Controller paths
    private static final String LOGIN_CONTROLLER = "LoginServlet";
    private static final String VERIFY_ACCOUNT_CONTROLLER = "VerifyAccountServlet";
    private static final String GOOGLE_LOGIN_CONTROLLER = "GoogleLoginServlet";
    private static final String REGISTRATION_CONTROLLER = "RegistrationServlet";
    
    // View paths
    private static final String LOGIN_PAGE = "views/login.jsp";
    private static final String REGISTRATION_PAGE = "views/registration.jsp";
    
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
        response.setContentType("txt/html;charset-UTF-8");
        
        String url = LOGIN_PAGE;
        
        // get action parameter
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        
        try {
            if (action == null) {
                url = LOGIN_PAGE;
            } else if (action.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (action.equals("Google Login")) {
                url = GOOGLE_LOGIN_CONTROLLER;
            } else if (action.equals("Create Account")) {
                url = REGISTRATION_CONTROLLER;
            } else if (action.equals("Verify")) {
                url = VERIFY_ACCOUNT_CONTROLLER;
            } else if (action.equals("Register")) {
                url = REGISTRATION_PAGE;
            } else {
                // Unknown action
                url = LOGIN_PAGE;
            }
            
        } catch (Exception ex) {
            log("Error in DispatchServlet: " + ex.getMessage(), ex);
            url = LOGIN_PAGE;
        } finally {
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
