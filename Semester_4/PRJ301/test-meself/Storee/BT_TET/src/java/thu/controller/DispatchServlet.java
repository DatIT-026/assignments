/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package thu.controller;

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

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String CHECK_LOGIN_CONTROLLER = "LoginServlet";
    private static final String UPDATE_CONTROLLER = "UpdateServlet";
    private static final String DELETE_CONTROLLER = "DeleteServlet";
    private static final String USER_SEARCH_CONTROLLER = "SearchServlet";
    private static final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
    private static final String UPDATE_CART_CONTROLLER = "UpdateCartServlet";
    private static final String CHECKOUT_CONTROLLER = "CheckoutServlet";

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
        String btn = request.getParameter("btAction");
        HttpSession session = request.getSession(false);

        try {
            if (btn == null || btn.equals("Login")) {
                if (btn.equals("Login")) {
                    url = CHECK_LOGIN_CONTROLLER;
                }
            } else {
                if (session == null || session.getAttribute("USER_INFO") == null){
                    request.setAttribute("ERROR_MSG", "You must login to perform this action!");
                    url = LOGIN_PAGE;
                } else {
                    switch (btn) {
                        case "Search":
                            url = USER_SEARCH_CONTROLLER;
                            break;
                        case "Update":
                            url = UPDATE_CONTROLLER;
                            break;
                        case "Delete":
                            url = DELETE_CONTROLLER;
                            break;
                        case "AddToCart":
                            url = ADD_TO_CART_CONTROLLER;
                            break;
                        case "UpdateCart":
                            url = UPDATE_CART_CONTROLLER;
                            break;
                        case "Checkout":
                            url = CHECKOUT_CONTROLLER;
                            break;
                    }
                }
            }
        } catch(Exception e){
            log("DispatchServlet: " + e.getMessage());
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
