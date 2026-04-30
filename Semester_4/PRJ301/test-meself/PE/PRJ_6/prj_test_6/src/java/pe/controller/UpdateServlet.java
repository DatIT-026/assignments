/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.model.ComponentDao;
import pe.model.ComponentDto;
import pe.model.UserDto;

/**
 *
 * @author datto
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
    
    public static final String SEARCH_PAGE = "componentList.jsp";
    
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

        String url = SEARCH_PAGE;
        
        String itemID = request.getParameter("itemID");
        String itemName = request.getParameter("itemName");
        String category = request.getParameter("category");
        String manufacturer = request.getParameter("manufacturer");
        String specification = request.getParameter("specification");
        String warehouseLocation = request.getParameter("warehouseLocation");
        String quantityStr = request.getParameter("quantity");
        String unitPriceStr = request.getParameter("unitPrice");
        String warrantyMonthStr = request.getParameter("warrantyMonth");
        String isAvailableStr = request.getParameter("isAvailable");
        String note = request.getParameter("note");
        String lastSearch = request.getParameter("lastSearch");

        try {
            int quantity = Integer.parseInt(quantityStr);
            float unitPrice = Float.parseFloat(unitPriceStr);
            int warrantyMonth = Integer.parseInt(warrantyMonthStr);
            boolean isAvailable = "true".equals(isAvailableStr);
            Date currentDate = new Date(System.currentTimeMillis());

            HttpSession session = request.getSession(false);

            if (session != null && session.getAttribute("USER_INFO") != null) {
                UserDto check = (UserDto) session.getAttribute("USER_INFO");

                if ("CS".equals(check.getRoleID())) {
                    request.setAttribute("ERROR_MSG", "No Permission: Admin access required");
                    request.getRequestDispatcher("SearchServlet").forward(request, response);
                }

                else if (quantity >= 0 && unitPrice > 0) {
                    ComponentDto dto = new ComponentDto(itemID, itemName, category, manufacturer,
                            specification, warehouseLocation, currentDate, quantity, unitPrice,
                            warrantyMonth, isAvailable, note);

                    ComponentDao dao = new ComponentDao();
                    dao.updateComponent(dto);
                }
                
                url = "DispatchSerlet?action=Search&event=" + (lastSearch != null ? lastSearch : "");
                response.sendRedirect(url);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            log("Error at UpdateServlet: " + e.getMessage());
            url = SEARCH_PAGE;
        } catch (Exception e) {
            e.printStackTrace();
            log("Error at UpdateServlet: " + e.getMessage());
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
