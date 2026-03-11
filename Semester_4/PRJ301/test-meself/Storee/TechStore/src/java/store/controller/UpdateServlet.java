/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import store.model.ProductDAO;
import store.model.ProductDTO;

/**
 *
 * @author datto
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
    private static final String UPDATE_PAGE = "update.jsp";
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
        String action = request.getParameter("action"); 
        
        try {
            if ("Edit".equals(action)) {
                String id = request.getParameter("txtProductID"); 
                if (id != null) {
                    ProductDAO dao = new ProductDAO();
                    ProductDTO product = dao.getProductByID(Integer.parseInt(id));
                    request.setAttribute("PRODUCT", product); 
                    url = UPDATE_PAGE;
                }
            }
            
            else if ("Confirm Update".equals(action)) {
                boolean status = true;
                
                String stringProductID = request.getParameter("txtProductID");
                String stringPrice = request.getParameter("txtPrice");
                String stringStockQuantity = request.getParameter("txtStockQuantity");
                String searchValue = request.getParameter("txtSearchValue");
                
                if (searchValue == null) searchValue = "";
                url += searchValue;
                
                int productID = Integer.parseInt(stringProductID);
                double price = Double.parseDouble(stringPrice);
                int stockQuantity = Integer.parseInt(stringStockQuantity);

                HttpSession session = request.getSession();
                boolean hasError = false;

                if (stockQuantity < 0) {
                    request.setAttribute("ERROR_MSG", "Error: Stock quantity cannot be less than 0!");
                    hasError = true;
                } else if (price < 0) {
                    request.setAttribute("ERROR_MSG", "Error: Price cannot be less than 0!");
                    hasError = true;
                }

                if (hasError) {
                    ProductDAO dao = new ProductDAO();
                    ProductDTO p = dao.getProductByID(productID);
                    request.setAttribute("PRODUCT", p);
                    url = UPDATE_PAGE;
                } else {
                    ProductDAO dao = new ProductDAO();
                    ProductDTO p = new ProductDTO(productID, "", price, stockQuantity, "", status);
//                    boolean newStatus = true; // de cho DEBUG
                    boolean result = dao.updateProduct(p);
                    if (result) {
                        session.setAttribute("ACTION_MESS", "Updated productID " + stringProductID + " completely!");
                    }
                }
            }
        } catch (NumberFormatException ex) {
            log("UpdateServlet_NumberFormat: " + ex.getMessage());
            request.getSession().setAttribute("ACTION_MESS", "Error number format!");
        } catch (ClassNotFoundException ex) {
            log("UpdateServlet_ClassNotFound: " + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateServlet_SQL: " + ex.getMessage());
        } catch (Exception ex) {
            log("UpdateServlet: " + ex.getMessage());
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
