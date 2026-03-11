/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.model.PaintingDAO;
import pe.model.PaintingDTO;
import pe.model.UsersDAO;
import pe.model.UsersDTO;

/**
 *
 * @author datto
 */
@WebServlet(name = "UpdatePaintingServlet", urlPatterns = {"/UpdatePaintingServlet"})
public class UpdatePaintingServlet extends HttpServlet {
    public static final String LOGIN_PAGE = "login.jsp";
    public static final String PAINTING_PAGE = "paintingList.jsp";
    
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
        String url = LOGIN_PAGE;

        try {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("USER_INFO") != null) {
                UsersDTO user = (UsersDTO) session.getAttribute("USER_INFO");
                if ("AD".equals(user.getRoleID())) {
                    String id = request.getParameter("id");
                    String creator = request.getParameter("creator");
                    String description = request.getParameter("description");
                    int height = Integer.parseInt(request.getParameter("height"));
                    int width = Integer.parseInt(request.getParameter("width"));
                    double price = Double.parseDouble(request.getParameter("price"));
                    String lastSearchValue = request.getParameter("lastSearchValue");
                    
                    PaintingDTO dto = new PaintingDTO(id, creator, description, height, width, price);
                    PaintingDAO dao = new PaintingDAO();
                    boolean result = dao.updatePainting(dto);
                    
                    if (result) {
                        url = "SearchPaintingServlet?txtSearchValue=" + lastSearchValue;
                    } else {
                        url = "error.jsp"; 
                    }
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
