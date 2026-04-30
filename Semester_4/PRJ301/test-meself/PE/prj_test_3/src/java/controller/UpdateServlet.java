/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import javax.servlet.http.HttpSession;
import model.PetDao;
import model.PetDto;
import model.UserDto;

/**
 *
 * @author datto
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
    public static final String UPDATE_PET_PAGE = "updatePet.jsp";
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
        String url = UPDATE_PET_PAGE;

        String idStr = request.getParameter("txtId");
        String petName = request.getParameter("txtPetName");
        String breed = request.getParameter("txtBreed");
        String ageStr = request.getParameter("txtAge");
        String priceStr = request.getParameter("txtPrice");
        String description = request.getParameter("txtDescription");

        String lastSearchValue = request.getParameter("paramLastSearchValue");
        
        Map<String, String> errors = new HashMap<>();

        try {
            int id = 0;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                errors.put("idErr", "Pet ID is invalid!");
            }
            
            if (petName == null || petName.trim().length() < 2 || petName.trim().length() > 100) {
                errors.put("nameErr", "Name length must be between 2 and 100 characters");
            }
            
            if (breed == null || breed.trim().length() < 2 || breed.trim().length() > 50) {
                errors.put("breedErr", "Breed length must be between 2 and 50 characters");
            }
            
            int age = 0;
            try {
                age = Integer.parseInt(ageStr);
                if (age < 0) {
                    errors.put("ageErr", "Age must be a positive number!");
                }
            } catch (NumberFormatException e) {
                errors.put("ageErr", "Age must be a valid number!");
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
            
            if (description != null && description.trim().length() > 255) {
                errors.put("descriptionErr", "Description length must be less than 255 characters");
            }

            if (!errors.isEmpty()) {
                request.setAttribute("UPDATE_ERROR", errors);
            } else {
                PetDao dao = new PetDao();
                PetDto dto = new PetDto(id, petName, breed, age, price, description);
                boolean result = dao.updatePet(dto);

                if (result) {
                    url = BACK_TO_SEARCH + (lastSearchValue != null ? lastSearchValue : "");
                }
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (!errors.isEmpty() || url.equals(UPDATE_PET_PAGE)) {
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
