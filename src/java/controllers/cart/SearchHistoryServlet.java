/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cart;

import daos.InvoiceDAO;
import entities.Account;
import entities.Invoice;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class SearchHistoryServlet extends HttpServlet {

    static Logger log = Logger.getLogger(SearchHistoryServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String searchName = request.getParameter("txtHistorySearchName");
            String searchDateStart = request.getParameter("txtHistoryDateStart");
            String searchDateEnd = request.getParameter("txtHistoryDateEnd");
            Account customer = (Account) session.getAttribute("USER");
            InvoiceDAO dao = new InvoiceDAO();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date start;
            Date end;
            if (searchName == null) {
                searchName = (String) session.getAttribute("HISTORY_SEARCH_NAME");
            }
            if (searchDateStart == null && session.getAttribute("HISTORY_SEARCH_DATE_START") == null) {
                start = new Date();
            } else if (searchDateStart != null) {
                start = sdf.parse(searchDateStart);

            } else {
                searchDateStart = (String) session.getAttribute("HISTORY_SEARCH_DATE_START");
                start = sdf.parse(searchDateStart);
            }
            if (searchDateEnd == null) {
                end = new Date();
            } else if (searchDateEnd != null) {
                end = sdf.parse(searchDateEnd);
            } else {
                searchDateEnd = (String) session.getAttribute("HISTORY_SEARCH_DATE_END");
                end = sdf.parse(searchDateEnd);
            }

            Calendar c = Calendar.getInstance();
            c.setTime(end);
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            c.set(Calendar.MILLISECOND, 999);
            end = c.getTime();
            List<Invoice> products = dao.searchHistoryByProductName(customer, searchName, start, end);

            session.setAttribute("HISTORY_LIST", products);
            //forward search value
            session.setAttribute("HISTORY_SEARCH_NAME", searchName);
            session.setAttribute("HISTORY_SEARCH_DATE_START", searchDateStart);
            session.setAttribute("HISTORY_SEARCH_DATE_END", searchDateEnd);

        } catch (Exception e) {
            log.info("Error at Search Food Servlet: " + e.getMessage());
        } finally {
            response.sendRedirect("history.jsp");
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
