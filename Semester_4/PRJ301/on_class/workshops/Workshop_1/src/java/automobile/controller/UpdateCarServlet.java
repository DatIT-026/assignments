/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package automobile.controller;

import automobile.model.CarDAO;
import automobile.model.CarDTO;
import automobile.model.CarErrorHandling;
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
@WebServlet(name = "UpdateCarServlet", urlPatterns = {"/UpdateCarServlet"})
public class UpdateCarServlet extends HttpServlet {

    private static final String MAIN_PAGE = "ListCarServlet";
    private static final String UPDATE_PAGE = "UpdateCar.jsp";

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

        String idP = request.getParameter("txtCarID");

        String carName = request.getParameter("txtCarName");
        String manufacturer = request.getParameter("txtManufacturer");
        String price = request.getParameter("txtPrice");
        String releasedYear = request.getParameter("txtReleasedYear");

        boolean foundErr = false;
        CarErrorHandling errorObj = new CarErrorHandling();
        
        try {
            // carname length hadling
            if (carName.trim().length() < 2 || carName.trim().length() > 50) {
                foundErr = true;
                errorObj.setCarNameLengthErr("Car name length must be positive and more than 2 or less than 50 characters");
            }
            
            // manufacturer length handling
            if (manufacturer.trim().length() < 2 || manufacturer.trim().length() > 50) {
                foundErr = true;
                errorObj.setManufacturerLengthErr("Manufacturer lenght must be positive and more than 2 or less than 50 characters");
            }
            
            float priceC = 0;
            try {
                priceC = Float.parseFloat(price);
                if (priceC <= 0) {
                    foundErr = true;
                    errorObj.setPriceFormatErr("Price must be positive!");
                }
            } catch (NumberFormatException e) {
                foundErr = true;
                errorObj.setPriceFormatErr("Price must be a number!");
            }
            
            int releasedYearC = 0;
            try {
                releasedYearC = Integer.parseInt(releasedYear);
                if (releasedYearC < 0) {
                    foundErr = true;
                    errorObj.setReleasedYearFormatErr("Released Year must be positive!");
                }
            } catch (NumberFormatException e) {
                foundErr = true;
                errorObj.setReleasedYearFormatErr("Released Year must be a number!");
            }
            
            if (foundErr) {
                url = UPDATE_PAGE;
                request.setAttribute("UPDATE_ERROR", errorObj);
                
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                CarDAO dao = new CarDAO();
                int id = Integer.parseInt(idP);
                int year = Integer.parseInt(releasedYear);
                float pr = Float.parseFloat(price);
                
                CarDTO dto = new CarDTO(id, carName, manufacturer, pr, year);
                boolean result = dao.updateCar(dto);

                if (result) {
                    url = MAIN_PAGE;
                    response.sendRedirect(url);
                }
            }

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        } finally {
              
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
