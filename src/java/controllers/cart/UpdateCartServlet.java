/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cart;

import daos.ProductDAO;
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
public class UpdateCartServlet extends HttpServlet {

    static Logger log = Logger.getLogger(UpdateCartServlet.class);
    private static final String URL = "cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("txtId");
            String action = request.getParameter("txtAction");
            int quantity;
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            ProductDAO dao = new ProductDAO();
            if (action.equals("Add")) {
                quantity = cart.getItemQuantity(Integer.parseInt(id));
                quantity += 1;
                if (dao.isAvailable(Integer.parseInt(id), quantity)) {
                    cart.updateCart(Integer.parseInt(id), quantity);
                } else {
                    request.setAttribute("ID", id);
                    request.setAttribute("MESSAGE", "Not enough in stock!");
                }

            } else if (action.equals("Remove")) {
                quantity = cart.getItemQuantity(Integer.parseInt(id));
                if (quantity > 1) {
                    quantity -= 1;
                    cart.updateCart(Integer.parseInt(id), quantity);
                } else {
                    cart.removeFromCart(Integer.parseInt(id));
                }
            }
            if(cart.getPurchasedItems().size() < 1){
                cart.setCoupon(null);
            }
            session.setAttribute("CART", cart);
        } catch (Exception e) {
            log.info("Error at Update Cart Servlet: " + e.getMessage());
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
