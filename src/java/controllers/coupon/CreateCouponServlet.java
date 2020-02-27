/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.coupon;

import daos.CouponDAO;
import daos.StatusDAO;
import entities.Coupon;
import entities.Status;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author tuannnh
 */
public class CreateCouponServlet extends HttpServlet {

    static Logger log = Logger.getLogger(CreateCouponServlet.class);
    private static final String URL = "admin-coupon.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
try {
            String name = request.getParameter("txtName");
            String discount = request.getParameter("txtDiscount");
            CouponDAO dao = new CouponDAO();
            StatusDAO sdao = new StatusDAO();
            Status status = sdao.getStatus("Active");
            Coupon newCoupon = new Coupon(name, Double.parseDouble(discount), status);
            boolean result = dao.addCoupon(newCoupon);
            if(!result){
                request.setAttribute("MESSAGE", "This Coupon: " + name + " is existed!");
                request.getRequestDispatcher(URL).forward(request, response);
            }
        } catch (Exception e) {
            log.info("Error at Create Coupon Servlet: " + e.getMessage());
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
