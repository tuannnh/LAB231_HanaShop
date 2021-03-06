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
public class SearchFoodServlet extends HttpServlet {

    static Logger log = Logger.getLogger(SearchFoodServlet.class);
    private static final String URL = "index.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String searchName = request.getParameter("txtUserSearch");
            String searchCategory = request.getParameter("txtUserCategory");
            String searchMin = request.getParameter("txtUserMin");
            String searchMax = request.getParameter("txtUserMax");
            String searchPageIndex = request.getParameter("searchPageIndex");
            ProductDAO dao = new ProductDAO();
            List<Product> products = dao.searchByUser(searchName, searchMin, searchMax, searchCategory);
            HttpSession session = request.getSession();
            session.setAttribute("USER_PRODUCTS", products);
            if (searchPageIndex == null) {
                request.setAttribute("PAGE", "1");

            } else {
                request.setAttribute("PAGE", searchPageIndex);
            }

            //forward search value
            session.setAttribute("USER_SEARCH_NAME", searchName);
            session.setAttribute("USER_SEARCH_CATEGORY", searchCategory);
            session.setAttribute("USER_SEARCH_MIN", searchMin);
            session.setAttribute("USER_SEARCH_MAX", searchMax);

        } catch (Exception e) {
            log.info("Error at Search Food Servlet: " + e.getMessage());
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
