/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.category;

import daos.CategoryDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author tuannnh
 */
public class UpdateCategoryServlet extends HttpServlet {

    static Logger log = Logger.getLogger(UpdateCategoryServlet.class);
    private static final String URL = "admin-category.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("txtId");
            String name = request.getParameter("txtName");
            CategoryDAO dao = new CategoryDAO();
            boolean result = dao.updateCategory(Integer.parseInt(id), name);
            if (!result) {
                request.setAttribute("MESSAGE", "This category: " + name + " is existed!");
                request.getRequestDispatcher(URL).forward(request, response);
            }

            //After make change of list, refresh
            HttpSession session = request.getSession();
            session.setAttribute("ADMIN_PRODUCTS", null);
            session.setAttribute("USER_PRODUCTS", null);
        } catch (Exception e) {
            log.info("Error at Create Category Servlet: " + e.getMessage());
        } finally {
            response.sendRedirect(URL);
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
