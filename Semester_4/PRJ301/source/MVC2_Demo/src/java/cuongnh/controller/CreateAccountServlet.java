/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cuongnh.controller;

import cuongnh.registration.RegistrationCreateError;
import cuongnh.registration.RegistrationDAO;
import cuongnh.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author datto
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String ERROR_PAGE = "createAccount.jsp";
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
        
        String url = ERROR_PAGE;
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String lastname = request.getParameter("txtLastName");
        String confirm = request.getParameter("txtConfirm");
        
        boolean foundErr = false;
        RegistrationCreateError err = new RegistrationCreateError();
        
        try {
            if (username.trim().length() < 2 || username.trim().length() > 20) {
                foundErr = true;
                err.setUserNameLongErr("Username is too long or less than 2 characters!");
            } 
            
            if (password.trim().length() < 2 || password.trim().length() > 30) {
                foundErr = true;
                err.setPasswordLongErr("Password is too long or less than 2 characters!");
            } else if (!confirm.trim().equals(password.trim())) {
                foundErr = true;
                err.setConfirmNotMatch("The confirm password must be matched");
            }
            
            if (lastname.trim().length() < 2 || lastname.trim().length() > 50) {
                foundErr = true;
                err.setLastNameLongErr("Lastname is too long or less than 2 characters!");
            }
            
            if (foundErr) {
                request.setAttribute("CREATE_ERROR", err);
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO account = new RegistrationDTO(username, password, lastname, false);
                boolean result = dao.createAccount(account);

                if (result) url = LOGIN_PAGE;
            }
            
        } catch (ClassNotFoundException ex) {
            log("CreateAccountServlet_ClassNotFound: " + ex.getMessage());
        } catch (SQLException ex) {
            String errMsg = ex.getMessage();
            if (errMsg.contains("duplicate")) {
                err.setDuplicatedUsername(username + "is Existed!");
                request.setAttribute("CREATE_ERROR", err);
            }
            log("CreateAccountServlet_SQL: " + ex.getMessage());
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
