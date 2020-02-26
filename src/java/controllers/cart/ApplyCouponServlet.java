/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cart;

import daos.CouponDAO;
import entities.Coupon;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Cart;
import org.apache.log4j.Logger;

/**
 *
 * @author tuannnh
 */
public class ApplyCouponServlet extends HttpServlet {

    static Logger log = Logger.getLogger(ApplyCouponServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String coupon = request.getParameter("txtCoupon");
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            CouponDAO dao = new CouponDAO();
            Coupon applyCoupon = dao.getCoupon(coupon);
            if (applyCoupon != null && applyCoupon.getStatus().getStatusName().equals("Active")) {
                cart.setCoupon(applyCoupon);
                session.setAttribute("CART", cart);
            } else {
                request.setAttribute("COUPON_MESSAGE", "Coupon: "+ coupon + " is not valid!");
            }
        } catch (Exception e) {
            log.info("Error at Apply Coupon Servlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("cart.jsp").forward(request, response);
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
