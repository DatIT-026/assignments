/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package thu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thu.model.ProductCart.Cart;
import thu.model.ProductDAO;

/**
 *
 * @author C00kies
 */
@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/UpdateCartServlet"})
public class UpdateCartServlet extends HttpServlet {

    private static final String USER_SEARCH_PAGE = "shoppingStore.jsp";

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

        String url = USER_SEARCH_PAGE;

        String stringProductID = request.getParameter("txtProductID");
        String method = request.getParameter("method");
        String searchValue = request.getParameter("txtSearchValue");

        try {
            int productID = Integer.parseInt(stringProductID);
            HttpSession session = request.getSession(false);
            ProductDAO dao = new ProductDAO();

            if (session != null) {
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
                    boolean canUpdate = true;
                    if (method.equals("increase")) {
                        int stockInDB = dao.getStockQuantity(productID);
                        
                        int quantityInCart = 0;
                        if (cart.getProducts().containsKey(productID)) {
                            quantityInCart = cart.getProducts().get(productID).getStockQuantity();
                        }
                        
                        if (quantityInCart + 1 > stockInDB){
                            session.setAttribute("ERROR_MESS", productID);
                            canUpdate = false;
                        }
                    }
                    
                    if (canUpdate){
                        cart.update(productID, method);
                        session.setAttribute("CART", cart);
                    }
                }
            }
            url = "DispatchServlet"
                    + "?btAction=Search"
                    + "&showCart=true"
                    + "&txtSearchValue=" + searchValue;
        } catch (Exception e) {
            log("UpdateCartServlet: " + e.getMessage());
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
