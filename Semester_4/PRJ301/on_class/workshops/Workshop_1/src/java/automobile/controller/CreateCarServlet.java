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
@WebServlet(name = "CreateCarServlet", urlPatterns = {"/CreateCarServlet"})
public class CreateCarServlet extends HttpServlet {

    private static final String MAIN_PAGE = "ListCarServlet";
    private static final String CREATE_PAGE = "CreateCar.jsp";

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

        String carID = request.getParameter("txtCarID");
        String carName = request.getParameter("txtCarName");
        String manufacturer = request.getParameter("txtManufacturer");
        String price = request.getParameter("txtPrice");
        String releasedYear = request.getParameter("txtReleasedYear");

        boolean foundErr = false;
        CarErrorHandling errorObj = new CarErrorHandling();

        try {
            if (carID != null && carName != null && manufacturer != null && price != null && releasedYear != null) {
                // carid error handling
                int carid = 0;
                try {
                    carid = Integer.parseInt(carID);
                    if (carid <= 0) {
                        foundErr = true;
                        errorObj.setCarIDFormatErr("carID must be positive!");
                    }
                } catch (NumberFormatException e) {
                    foundErr = true;
                    errorObj.setCarIDFormatErr("Car ID must be a number!");
                }
                
                // carname length handling
                if (carName.trim().length() < 2 || carName.trim().length() > 50) {
                    foundErr = true;
                    errorObj.setCarNameLengthErr("Car name length must be positive and more than 2 or less than 50 characters");
                }
                
                // Manufacturer length handling
                if (manufacturer.trim().length() < 2 || manufacturer.trim().length() > 50) {
                    foundErr = true;
                    errorObj.setManufacturerLengthErr("Manufacturer lenght must be positive and more than 2 or less than 50 characters");
                }

                // price
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
                    url = CREATE_PAGE;
                    request.setAttribute("CREATE_ERROR", errorObj);

                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                } else {
                    CarDAO dao = new CarDAO();
                    CarDTO car = new CarDTO(carid, carName, manufacturer, priceC, releasedYearC);
                    boolean result = dao.createCar(car);

                    if (result) {
                        url = MAIN_PAGE;
                        response.sendRedirect(url);
                    }

                    
                }
            }
        } catch (ClassNotFoundException ex) {
            log("CreateCartServlet_ClassNotFound: " + ex.getMessage());
        } catch (SQLException ex) {
            String errMsg = ex.getMessage();
            if (errMsg.contains("duplicate")) {
                errorObj.setDuplicatedCarID(carID + " is Existed!");
                request.setAttribute("CREATE_ERROR", errorObj);
            }
            
            url = CREATE_PAGE;
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            log("CreateCarServlet_SQL: " + ex.getMessage());
        }
        finally {
            
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
