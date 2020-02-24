/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.services;

import entities.Product;
import java.io.IOException;
import java.util.List;
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
public class ChangePageAdminServlet extends HttpServlet {

    static Logger log = Logger.getLogger(ChangePageAdminServlet.class);

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
        try {
            String searchName = request.getParameter("txtAdminSearch");
            String searchCategory = request.getParameter("txtAdminCategory");
            String searchStatus = request.getParameter("txtAdminStatus");
            String pageIndex = request.getParameter("pageIndex");

            HttpSession session = request.getSession();
            List<Product> products = (List<Product>) session.getAttribute("ADMIN_SEARCH_LIST");

            request.setAttribute("ADMIN_SEARCH_NAME", searchName);
            request.setAttribute("ADMIN_SEARCH_CATEGORY", searchCategory);
            request.setAttribute("ADMIN_SEARCH_STATUS", searchStatus);
            request.setAttribute("PAGE", pageIndex);
            request.setAttribute("ADMIN_PRODUCTS", products);
        } catch (Exception e) {
            log.info("Error at Change Page Admin Servlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("index.jsp").forward(request, response);

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
