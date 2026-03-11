/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package store.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import store.model.Cart;
import store.model.CartItem;
import store.model.ProductDAO;
import store.model.ProductDTO;

/**
 *
 * @author datto
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {
    private static final String SHOP_PAGE = "shop.jsp";
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
        String url = "MainController?action=Search&txtSearchValue=";
        
        String stringProductID = request.getParameter("txtProductID");
        String searchValue = request.getParameter("txtSearchValue");
        if (searchValue != null) url += searchValue;

        try {
            if (stringProductID != null && !stringProductID.isEmpty()) {
                int productID = Integer.parseInt(stringProductID);
                ProductDAO dao = new ProductDAO();
                int stockInDB = dao.getStockQuantity(productID);
                ProductDTO product = dao.getProductByID(productID);

                HttpSession session = request.getSession();
                Cart cart = (Cart) session.getAttribute("CART");
                
                if (cart == null) cart = new Cart();
                
                int quantityInCart = 0;
                if (cart.getItems().containsKey(productID)) {
                    quantityInCart = cart.getItems().get(productID).getQuantity();
                }

                if (quantityInCart + 1 > stockInDB) {
                    session.setAttribute("ERROR_MESS", "Insufficient stock! Only " + stockInDB + " left.");
                } else {
                    cart.add(product);
                    session.setAttribute("CART", cart);
                    session.setAttribute("ADD_MESS", "Added " + product.getProductName() + " successfully!");
                }
            }
        } catch (Exception e) {
            log("AddToCartServlet: " + e.getMessage());
        } finally {
            response.sendRedirect(url);
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
