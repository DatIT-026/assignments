/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.model.FashionDao;
import pe.model.FashionDto;

/**
 *
 * @author datto
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {
    public static final String UPDATE_PAGE = "update.jsp";
    public static final String BACK_TO_SEARCH = "MainController?action=Search&txtSearchValue=";
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
        String url = UPDATE_PAGE;

        String id = request.getParameter("txtId");
        String name = request.getParameter("txtName");
        String description = request.getParameter("txtDescription");
        String priceStr = request.getParameter("txtPrice");
        String size = request.getParameter("txtSize");
        String statusStr = request.getParameter("txtStatus");
        
        String lastSearchValue = request.getParameter("paramLastSearchValue");
        
        Map<String, String> errors = new HashMap<>();
        
        try {
            if (id == null || !id.matches("^F-\\d{3}$")) {
                errors.put("idErr", "ID must be in format F-XXX (e.g., F-001)!");
            }
            
            if (name == null || name.trim().length() < 2 || name.trim().length() > 50) {
                errors.put("nameErr", "Name length must be between 2 and 50 characters");
            }
            
            if (description != null && description.trim().length() > 500) {
                errors.put("descriptionErr", "Description length must be less than 500 characters");
            }
            
            float price = 0;
            try {
                price = Float.parseFloat(priceStr);
                if (price < 0) {
                    errors.put("priceErr", "Price must be a positive number!");
                }
            } catch (NumberFormatException e) {
                errors.put("priceErr", "Price must be a valid number!");
            }
            
            if (size == null || !size.trim().toUpperCase().matches("^(S|M|L|XL)$")) {
                errors.put("sizeErr", "Size must be S, M, L, or XL!");
            }

            boolean status = false;
            if ("true".equals(statusStr) || "on".equals(statusStr)) {
                status = true;
            }
            
            if (!errors.isEmpty()) {
                request.setAttribute("UPDATE_ERROR", errors);
            } else {
                FashionDao dao = new FashionDao();
                FashionDto dto = new FashionDto(id, name, description, price, size, status);
                boolean result = dao.updateFashion(dto);

                if (result) {
                    url = BACK_TO_SEARCH + (lastSearchValue != null ? lastSearchValue : "");
                }
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        } finally {
            if (!errors.isEmpty() || url.equals(UPDATE_PAGE)) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
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
