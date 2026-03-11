/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mobile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mobile.model.MobileDTO;

/**
 *
 * @author datto
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

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
        String url = "MainController?btAction=Search"; 

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Map<String, MobileDTO> cart = (Map<String, MobileDTO>) session.getAttribute("CART");
                if (cart == null) {
                    cart = new HashMap<>();
                }
                
                String mobileId = request.getParameter("mobileId");
                String mobileName = request.getParameter("mobileName");
                String priceStr = request.getParameter("price");

                if (mobileId != null && priceStr != null) {
                    double price = Double.parseDouble(priceStr);
                    
                    if (cart.containsKey(mobileId)) {
                        MobileDTO existingItem = cart.get(mobileId);
                        existingItem.setQuantity(existingItem.getQuantity() + 1);
                    } else {
                        MobileDTO newItem = new MobileDTO(mobileId, "", price, mobileName, 0, 1, false);
                        cart.put(mobileId, newItem);
                    }
                    
                    session.setAttribute("CART", cart);
                    request.setAttribute("MESSAGE", "Successfully added " + mobileName + " to cart!");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
