/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cart;

import daos.ProductDAO;
import entities.Account;
import entities.Product;
import java.io.IOException;
import java.util.List;
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
public class AddToCartServlet extends HttpServlet {

    static Logger log = Logger.getLogger(AddToCartServlet.class);
    private static final String URL = "index.jsp";

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
            String id = request.getParameter("txtId");
            HttpSession session = request.getSession();
            ProductDAO dao = new ProductDAO();
            Product product = dao.findById(Integer.parseInt(id));

            Cart cart = (Cart) session.getAttribute("CART");
//            Account customer = (Account) session.getAttribute("USER");
            Account customer = new Account("mail@hungtuan.me", "123", "User");
            if (cart == null) {
                cart = new Cart(customer);
            }
            boolean result = cart.addToCart(product);
            if (result) {
                session.setAttribute("CART", cart);
                request.setAttribute("MESSAGE", product.getName() + " is added to your cart!");
            } else {
                request.setAttribute("MESSAGE", product.getName() + " is not enough in stock!");
            }
            //forward search value
            String searchName = request.getParameter("txtSearch");
            String searchCategory = request.getParameter("txtCategory");
            String searchMin = request.getParameter("txtMin");
            String searchMax = request.getParameter("txtMax");
            String pageIndex = request.getParameter("pageIndex");

            request.setAttribute("SEARCH_NAME", searchName);
            request.setAttribute("SEARCH_CATEGORY", searchCategory);
            request.setAttribute("SEARCH_MIN", searchMin);
            request.setAttribute("SEARCH_MAX", searchMax);
            request.setAttribute("PAGE", pageIndex);
        } catch (Exception e) {
            log.info("Error at Add To Cart Servlet: " + e.getMessage());
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
