/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mobile.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author datto
 */
public class MainController extends HttpServlet {

    // login and logout
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String LOGIN_CONTROLLER = "LoginServlet";
    private static final String LOGOUT_CONTROLLER = "LogoutServlet";

    // search function
    private static final String SEARCH_CONTROLLER = "SearchServlet";

    // user's function
    private static final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
    private static final String REMOVE_CART_CONTROLLER = "RemoveCartServlet";
    private static final String UPDATE_CART_CONTROLLER = "UpdateCartServlet";
    private static final String VIEW_DETAILS_CART_CONTROLLER = "viewCart.jsp";

    // staff's function
    private static final String DELETE_MOBILE_CONTROLLER = "DeleteServlet";
    private static final String UPDATE_CONTROLLER = "UpdateServlet";
    private static final String CREATE_PAGE = "newMobile.jsp";
    private static final String CREATE_CONTROLLER = "CreateServlet";

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
        String button = request.getParameter("btAction");

        HttpSession session = request.getSession(false);

        try {
            if (button == null) {
            } else if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else {
                if (session != null && session.getAttribute("USER_INFO") != null) {
                    switch (button) {
                        case "Logout":
                            url = LOGOUT_CONTROLLER;
                            break;
                        case "Search":
                            url = SEARCH_CONTROLLER;
                            break;
                        case "Add Cart":
                            url = ADD_TO_CART_CONTROLLER;
                            break;
                        case "Update Cart":
                            url = UPDATE_CART_CONTROLLER;
                            break;
                        case "Remove Cart":
                            url = REMOVE_CART_CONTROLLER;
                            break;
                        case "DetailsCart":
                            url = VIEW_DETAILS_CART_CONTROLLER;
                            break;
                        case "Delete":
                            url = DELETE_MOBILE_CONTROLLER;
                            break;
                        case "Update":
                            url = UPDATE_CONTROLLER;
                            break;
                        case "Create":
                            url = CREATE_PAGE;
                            break;
                        case "Insert":
                            url = CREATE_CONTROLLER;
                            break;
                    }
                }
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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
