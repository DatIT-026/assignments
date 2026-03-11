/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Controllers.Authentication;

import Models.DTO.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author datto
 */
public class AuthenticationFilter implements Filter {
    private static final String loginPage = "Login.jsp";
    private final String createController = "CreateController";
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String action = request.getParameter("action");
        if(action!=null && action.equalsIgnoreCase("Create")){
            request.getRequestDispatcher(createController).forward(request, response);
        }
        
        if (session != null) {
            User user = (User) session.getAttribute("userLoggedIn");
            if (user != null) {
                req.setAttribute("loggedByAdmin", user.isIsAdmin());
                chain.doFilter(request, response);
            }
        } else {
            resp.sendRedirect(loginPage);
        }
    }
    
    //=========================
    public void destroy() { }
    //=========================
    public void init(FilterConfig filterConfig) { }
}
