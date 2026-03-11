/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package store.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import store.model.AccountDTO;
import store.model.Cart;
import store.model.OrderDAO;

/**
 *
 * @author datto
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {
    private static final String VIEW_CART_PAGE = "viewCart.jsp";
    private static final String SUCCESS_PAGE = "success.jsp";
    
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
        
        String url = VIEW_CART_PAGE;
        
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            AccountDTO user = (AccountDTO) session.getAttribute("USER_INFO");

            if (cart != null && user != null && !cart.getItems().isEmpty()) {
                OrderDAO dao = new OrderDAO();
                boolean check = dao.checkout(user.getUserID(), cart);

                if (check) {
                    session.removeAttribute("CART");
                    session.removeAttribute("CHECKOUT_MESS"); 
                    session.removeAttribute("ERROR_MESS");
                    
                    url = SUCCESS_PAGE;
                } else {
                    session.setAttribute("CHECKOUT_MESS", "Checkout failed. Please check stock again.");
                }
            } else {
                session.setAttribute("CHECKOUT_MESS", "Cart is empty or session expired.");
            }
        } catch (Exception e) {
            log("CheckoutServlet: " + e.getMessage());
            request.getSession().setAttribute("CHECKOUT_MESS", "Error: " + e.getMessage());
        } finally {
            if (SUCCESS_PAGE.equals(url)) {
                response.sendRedirect(url);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
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
