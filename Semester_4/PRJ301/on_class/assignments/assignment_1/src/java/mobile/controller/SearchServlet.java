/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mobile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mobile.model.MobileDAO;
import mobile.model.MobileDTO;
import mobile.model.MobileErrorHandling;

/**
 *
 * @author datto
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
    public static final String LIST_PAGE = "listDevice.jsp";

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
        String url = LIST_PAGE;

        try {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("USER_INFO") != null) {
                String searchValue = request.getParameter("txtSearchValue");
                
                String minPriceStr = request.getParameter("minPrice");
                String maxPriceStr = request.getParameter("maxPrice");
                
                MobileDAO dao = new MobileDAO();
                boolean isSearched = false;
                
                if (searchValue != null && searchValue.trim().length() > 0) {
                    dao.searchMobile(searchValue);
                    isSearched = true;
                } else if (minPriceStr != null && maxPriceStr != null) {
                    try {
                        double minPrice = Float.parseFloat(minPriceStr);
                        double maxPrice = Float.parseFloat(maxPriceStr);
                        
                        MobileErrorHandling errorObj = new MobileErrorHandling();
                        boolean isError = false;
                        
                        if (minPrice < 0 || maxPrice < 0) {
                            isError = true;
                            errorObj.setPriceError("Prices must be positive numbers!");
                        } else if (minPrice > maxPrice) {
                            isError = true;
                            errorObj.setPriceError("Min Price cannot be greater than Max Price!");
                        }
                        
                        if (isError) {
                            request.setAttribute("MOBILE_ERROR", errorObj);
                        } else {
                            dao.searchMobileByPriceRange(minPrice, maxPrice);
                            isSearched = true;
                        }
                    } catch (NumberFormatException e) {
                        log("Error parsing price: " + e.getMessage());
                    }
                }
                
                if (isSearched) {
                    List<MobileDTO> result = dao.getMobiles();
                    request.setAttribute("MOBILE_LIST", result);
                }
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
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
