/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mobile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mobile.model.MobileDAO;
import mobile.model.MobileDTO;

/**
 *
 * @author datto
 */
@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/UpdateCartServlet"})
public class UpdateCartServlet extends HttpServlet {
    private static final String VIEW_DETAILS_CART_PAGE = "viewCart.jsp";
    
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
        String url = VIEW_DETAILS_CART_PAGE;

        try {
            HttpSession session = request.getSession(false);
            
            String mobileId = request.getParameter("mobileId");
            String quantityStr = request.getParameter("quantity");
            
            if (session != null) {
                Map<String, MobileDTO> cart = (Map<String, MobileDTO>) session.getAttribute("CART");

                if (cart != null && mobileId != null && quantityStr != null) {
                    int quantity = Integer.parseInt(quantityStr);

                    if (cart.containsKey(mobileId)) {
                        MobileDAO dao = new MobileDAO();
                        MobileDTO item = dao.getMobileByID(mobileId);
                        
                        if (item != null) {
                            if (quantity <= item.getQuantity()) {
                                MobileDTO dto = cart.get(mobileId);
                                dto.setQuantity(quantity);
                                cart.put(mobileId, dto);
                                session.setAttribute("CART", cart);
                            } else {
                                request.setAttribute("ERROR", "Quantity for " + item.getMobileName()
                                        + " must be less than or equal to " + item.getQuantity() + ".");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("Error at UpdateCartServlet: " + e.toString());
        } finally {
            if (request.getAttribute("ERROR") != null) {
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                response.sendRedirect(url);
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
