/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mobile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mobile.model.MobileDAO;
import mobile.model.MobileDTO;
import mobile.model.MobileErrorHandling;

/**
 *
 * @author datto
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

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

        boolean isError = false;
        MobileErrorHandling errorObj = new MobileErrorHandling();

        try {
            String mobileId = request.getParameter("mobileId");
            String mobileName = request.getParameter("mobileName");
            String description = request.getParameter("description");

            String priceStr = request.getParameter("price");
            String yearStr = request.getParameter("yearOfProduction");
            String quantityStr = request.getParameter("quantity");

            // validate price: >0
            double price = 0;
            try {
                price = Double.parseDouble(priceStr);
                if (price < 0) {
                    isError = true;
                    errorObj.setPriceError("Price must be positive!");
                }
            } catch (NumberFormatException e) {
                isError = true;
                errorObj.setPriceError("Price must be a number!");
            }

            // validate year
            int year = 0;
            try {
                year = Integer.parseInt(yearStr);
                if (year < 1800 || year > 2100) {
                    isError = true;
                    errorObj.setYearOfProductionError("Year must be in range 1800 to 2100");
                }
            } catch (NumberFormatException e) {
                isError = true;
                errorObj.setYearOfProductionError("Year must be a number!");
            }

            // validate quantity: >0
            int quantity = 0;
            try {
                quantity = Integer.parseInt(quantityStr);
                if (quantity < 0) {
                    isError = true;
                    errorObj.setQuantityError("Quantity cannot be negative");
                }
            } catch (NumberFormatException e) {
                isError = true;
                errorObj.setQuantityError("Quantity must be a number!");
            }

            if (isError) {
                request.setAttribute("MOBILE_ERROR", errorObj);
            } else {
                boolean notSale = request.getParameter("notSale") != null;
                MobileDTO mobile = new MobileDTO(
                        mobileId, description, price, mobileName, year, quantity, notSale);
                MobileDAO dao = new MobileDAO();
                dao.updateMobile(mobile);
            }
        } catch (Exception e) {
            log("Error at UpdateServlet: " + e.toString());
        } finally {
            String lastSearchValue = request.getParameter("lastSearchValue");
            if (lastSearchValue == null) {
                lastSearchValue = "";
            }
            
            String url = "MainController?btAction=Search&txtSearchValue=" + lastSearchValue;
            
            if (request.getAttribute("MOBILE_ERROR") != null) {
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
