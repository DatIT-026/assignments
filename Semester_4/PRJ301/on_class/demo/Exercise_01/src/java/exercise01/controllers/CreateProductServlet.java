/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package exercise01.controllers;

import exercise01.model.CreateProductErrorHandling;
import exercise01.model.ProductDAO;
import exercise01.model.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author datto
 */
@WebServlet(name = "CreateProductServlet", urlPatterns = {"/CreateProductServlet"})
public class CreateProductServlet extends HttpServlet {

    private static final String MAIN_PAGE = "ListProductServlet";
    private static final String CREATE_PAGE = "CreateProduct.jsp";

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

        String url = CREATE_PAGE;

        String productName = request.getParameter("txtProductName");
        String unitPrice = request.getParameter("txtUnitPrice");
        String quantity = request.getParameter("txtQuantity");

        boolean foundErr = false;
        CreateProductErrorHandling errorObj = new CreateProductErrorHandling();

        try {
            if (productName != null && unitPrice != null && quantity != null) {
                if (productName.trim().length() < 2 || productName.trim().length() > 50) {
                    foundErr = true;
                    errorObj.setProductNameLengthErr("Product Name must be positive and more than 2 or less than 50 characters");
                }

                float price = 0;
                try {
                    price = Float.parseFloat(unitPrice);
                    if (price <= 0) {
                        foundErr = true;
                        errorObj.setUnitPriceFormatErr("Unit Price must be positive!");
                    }
                } catch (NumberFormatException e) {
                    foundErr = true;
                    errorObj.setUnitPriceFormatErr("Unit Price must be a number!");
                }

                int quant = 0;
                try {
                    quant = Integer.parseInt(quantity);
                    if (quant < 0) {
                        foundErr = true;
                        errorObj.setQuantityFormatErr("Quantity must be positive!");
                    }
                } catch (NumberFormatException e) {
                    foundErr = true;
                    errorObj.setQuantityFormatErr("Quantity must be a number!");
                }

                if (foundErr) {
                    request.setAttribute("CREATE_ERROR", errorObj);

                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                } else {
                    ProductDAO dao = new ProductDAO();
                    ProductDTO product = new ProductDTO(0, productName, price, quant);
                    boolean result = dao.createProduct(product);

                    if (result) {
                        request.setAttribute("CREATE_SUCCESSFUL", "Your product was added!");
                        url = CREATE_PAGE;
                    }

                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }
            }
        } catch (ClassNotFoundException ex) {
            log("CreateProductServlet_ClassNotFound: " + ex.getMessage());
        } catch (SQLException ex) {
            log("CreateProductServlet_SQL: " + ex.getMessage());
        }
//        finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//        }
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
