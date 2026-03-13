/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mobile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "CreateServlet", urlPatterns = {"/CreateServlet"})
public class CreateServlet extends HttpServlet {

    private static final String MAIN_PAGE = "newMobile.jsp";

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

        String url = MAIN_PAGE;

        boolean isError = false;
        MobileErrorHandling errorObj = new MobileErrorHandling();

        String mobileId = request.getParameter("mobileId");
        String mobileName = request.getParameter("mobileName");
        String description = request.getParameter("description");

        String priceStr = request.getParameter("price");
        String yearStr = request.getParameter("yearOfProduction");
        String quantityStr = request.getParameter("quantity");

        boolean notSale = request.getParameter("notSale") != null;

        try {
            // validate ID
            if (mobileId == null || mobileId.trim().length() == 0 || mobileId.trim().length() > 10) {
                isError = true;
                errorObj.setMobileIdError("Mobile ID must be between 1 and 10 characters");
            }

            // validate name's length
            if (mobileName.trim().length() < 2 || mobileName.trim().length() > 20) {
                isError = true;
                errorObj.setMobileNameError("Mobile Name must be between 2 and 20 characters");
            }

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
                    errorObj.setYearOfProductionError("Year must be in range 1800 to 2100.");
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
                    errorObj.setQuantityError("Quantity must be positive!");
                }
            } catch (NumberFormatException e) {
                isError = true;
                errorObj.setQuantityError("Quantity must be a number!");
            }

            if (isError) {
                request.setAttribute("MOBILE_ERROR", errorObj);
            } else {
                MobileDAO dao = new MobileDAO();
                MobileDTO newMobile = new MobileDTO(mobileId, description, price, mobileName, year, quantity, notSale);
                boolean result = dao.createMobile(newMobile);

                if (result) {
                    request.setAttribute("SUCCESS", "Successfully inserted Mobile ID: " + mobileId);
                } else {
                    request.setAttribute("ERROR", "Failed to insert.");
                }
            }

        } catch (ClassNotFoundException ex) {
            log("CreateServlet_ClassNotFound: " + ex.getMessage());
        } catch (SQLException ex) {
            String errMsg = ex.getMessage();
            if (errMsg.toLowerCase().contains("duplicate")) {
                errorObj.setMobileIdDuplicatedError(mobileId + " is Existed!");
                request.setAttribute("MOBILE_ERROR", errorObj);
            } else {
                request.setAttribute("ERROR", "Database error occurred.");
            }
        } catch (Exception ex) {
            log("CreateServlet_Error: " + ex.getMessage());
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
