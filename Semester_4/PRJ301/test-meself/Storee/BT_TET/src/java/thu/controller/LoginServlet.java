/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package thu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thu.model.UserDAO;
import thu.model.UserDTO;

/**
 *
 * @author C00kies
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String ADMIN_PAGE = "productManagement.jsp";
    private static final String CUSTOMER_PAGE = "shoppingStore.jsp";

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

        String stringUserID = request.getParameter("txtUserID");
        String password = request.getParameter("txtPassword");

        try {
            int userID = Integer.parseInt(stringUserID);
            UserDAO dao = new UserDAO();
            UserDTO dto = dao.checkLogin(userID, password);

            if (dto != null) {
                Cookie cookie = new Cookie(stringUserID, password);
                cookie.setMaxAge(3 * 60);

                if (dto.getRole().equals("ADMIN")) {
                    url = ADMIN_PAGE;
                } else {
                    url = CUSTOMER_PAGE;
                }
                HttpSession session = request.getSession();
                session.setAttribute("USER_INFO", dto);
            }
            else request.setAttribute("ERROR_MSG", "Invalid userID or password!");

        } catch (ClassNotFoundException e) {
            log("LoginServlet_ClassNotFound: " + e.getMessage());
        } catch (SQLException e) {
            log("LoginServlet_SQL: " + e.getMessage());
        } catch (Exception e) {
            log("LoginServlet: " + e.getMessage());
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
