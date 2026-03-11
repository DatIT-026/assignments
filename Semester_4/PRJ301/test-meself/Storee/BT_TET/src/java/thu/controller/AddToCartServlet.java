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
import thu.model.ProductDTO;

/**
 *
 * @author C00kies
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

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
        String productName = request.getParameter("txtProductName");
        String stringPrice = request.getParameter("txtPrice");
        String stringStockQuantity = request.getParameter("txtStockQuantity");
        String category = request.getParameter("txtCategory");

        String searchValue = request.getParameter("txtSearchValue");
        if (searchValue == null) {
            searchValue = "";
        }

        try {
            int productID = Integer.parseInt(stringProductID);
            float price = Float.parseFloat(stringPrice);

            //So luong trong DB
            ProductDAO dao = new ProductDAO();
            int stockInDB = dao.getStockQuantity(productID);
            
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");

            if (cart == null) {
                cart = new Cart();
            }
            
            //So luong trong cart
            int quantityInCart = 0;
            if (cart.getProducts().containsKey(productID)) {
                quantityInCart = cart.getProducts().get(productID).getStockQuantity();
            }
            
            if (quantityInCart + 1 > stockInDB){
                session.setAttribute("ERROR_MESS", productID);
            } else {
                ProductDTO dto = new ProductDTO();
                dto.setProductID(productID);
                dto.setProductName(productName);
                dto.setPrice(price);
                dto.setStockQuantity(1);
                dto.setCategory(category);

                cart.add(dto);
                session.setAttribute("CART", cart);
                session.setAttribute("ADD_MESS", productID);
            }

            url = "DispatchServlet?btAction=Search"
                    + "&txtSearchValue=" + searchValue
                    + "&showCart=true";

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
