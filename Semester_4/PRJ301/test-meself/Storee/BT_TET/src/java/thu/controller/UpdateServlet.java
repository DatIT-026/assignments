/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package thu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thu.model.ProductDAO;

/**
 *
 * @author C00kies
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

    private static final String STAFF_SEARCH_PAGE = "productManagement.jsp";

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

        String url = STAFF_SEARCH_PAGE;

        String stringProductID = request.getParameter("txtProductID");
        String stringPrice = request.getParameter("txtPrice");
        String stringStockQuantity = request.getParameter("txtStockQuantity");
        String stringStatus = request.getParameter("txtStatus");

        String searchValue = request.getParameter("txtSearchValue");

        try {
            int productID = Integer.parseInt(stringProductID);
            float price = Float.parseFloat(stringPrice);
            int stockQuantity = Integer.parseInt(stringStockQuantity);
            boolean status = Boolean.parseBoolean(stringStatus);

            HttpSession session = request.getSession();
            boolean hasError = false;

            if (stockQuantity < 0) {
                session.setAttribute("ACTION_MESS", "Error: Stock quantity can be less than 0!");
                hasError = true;
            } else if (price < 0) {
                session.setAttribute("ACTION_MESS", "Error: Price can be less than 0!");
                hasError = true;
            }

            if (!hasError) {
                ProductDAO dao = new ProductDAO();
                boolean result = dao.update(productID, price, stockQuantity, status);

                if (result) {
                    session.setAttribute("ACTION_MESS", "Updated productID " + stringProductID + " completely!");
                }
            }
            url = "DispatchServlet?btAction=Search&txtSearchValue=" + searchValue;

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
