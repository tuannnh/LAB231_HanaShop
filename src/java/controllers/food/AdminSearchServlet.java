/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.food;

import daos.ProductDAO;
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
public class AdminSearchServlet extends HttpServlet {

    static Logger log = Logger.getLogger(AdminSearchServlet.class);
    private static final String URL = "admin-food.jsp";

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
            HttpSession session = request.getSession();

            String searchName = request.getParameter("txtAdminSearch");
            if (searchName == null) {
                searchName = (String) session.getAttribute("ADMIN_SEARCH_NAME");
            }
            String searchCategory = request.getParameter("txtAdminCategory");
            if (searchCategory == null) {
                searchCategory = (String) session.getAttribute("ADMIN_SEARCH_CATEGORY");
            }
            String searchStatus = request.getParameter("txtAdminStatus");
            if (searchStatus == null) {
                searchStatus = (String) session.getAttribute("ADMIN_SEARCH_STATUS");
            }
            String searchPageIndex = request.getParameter("searchPageIndex");
            if (searchCategory != null && searchStatus != null) {
                ProductDAO dao = new ProductDAO();
                List<Product> products = dao.searchByAdmin(searchName, searchStatus, searchCategory);
                session.setAttribute("ADMIN_PRODUCTS", products);
            }
            if (searchPageIndex == null) {
                request.setAttribute("PAGE", "1");

            } else {
                request.setAttribute("PAGE", searchPageIndex);
            }

            //forward search value
            session.setAttribute("ADMIN_SEARCH_NAME", searchName);
            session.setAttribute("ADMIN_SEARCH_CATEGORY", searchCategory);
            session.setAttribute("ADMIN_SEARCH_STATUS", searchStatus);

        } catch (Exception e) {
            log.info("Error at Admin Search Servlet: " + e.getMessage());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(URL).forward(request, response);
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
