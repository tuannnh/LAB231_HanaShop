/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cart;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import daos.InvoiceDAO;
import daos.OrderItemDAO;
import daos.ProductDAO;
import daos.SuggestProductDAO;
import entities.Invoice;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Cart;
import org.apache.log4j.Logger;
import paypal.PaypalServices;

/**
 *
 * @author tuannnh
 */
public class CompletePurchaseServlet extends HttpServlet {

    static Logger log = Logger.getLogger(CompletePurchaseServlet.class);
    private static final String SUCCESS = "receipt.jsp";
    private static final String ERROR = "cart.jsp";

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
        String url = ERROR;
        try {
            String paymentId = request.getParameter("paymentId");
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart.isAvailableCart()) {

                //Normal purchase
                if (paymentId == null) {

                    //Create invoce
                    InvoiceDAO invoiceDAO = new InvoiceDAO();
                    Invoice newInvoce = invoiceDAO.createNormalInvoice(cart);

                    //Create order detail
                    OrderItemDAO orderItemDAO = new OrderItemDAO();
                    orderItemDAO.createOrderItem(cart, newInvoce);

                    //Update stock
                    ProductDAO productDAO = new ProductDAO();
                    productDAO.updateQuantity(cart);

                    //Add suggestion
                    SuggestProductDAO suggestProductDAO = new SuggestProductDAO();
                    suggestProductDAO.addSuggestProduct(newInvoce);
                    //After add, shopping cart has no product, set null
                    session.setAttribute("CART", null);
                    session.setAttribute("USER_PRODUCTS", null);

                    double total = newInvoce.getTotal();

                  

                    request.setAttribute("TOTAL", total);
                    request.setAttribute("INVOICE_ID", newInvoce.getId());

                    url = SUCCESS;

                    //Paypal Purchase
                } else {
                    String payerId = request.getParameter("PayerID");
                    PaypalServices paypalServices = new PaypalServices();
                    Payment payment = paypalServices.executePayment(paymentId, payerId);
                    PayerInfo payerInfo = payment.getPayer().getPayerInfo();
                    Transaction transaction = payment.getTransactions().get(0);

                    //Create invoce
                    InvoiceDAO invoiceDAO = new InvoiceDAO();
                    Invoice newInvoce = invoiceDAO.createPaypalInvoice(cart, paymentId);

                    //Create order detail
                    OrderItemDAO orderItemDAO = new OrderItemDAO();
                    orderItemDAO.createOrderItem(cart, newInvoce);

                    //Update stock
                    ProductDAO productDAO = new ProductDAO();
                    productDAO.updateQuantity(cart);

                    SuggestProductDAO suggestProductDAO = new SuggestProductDAO();
                    suggestProductDAO.addSuggestProduct(newInvoce);
                    //After add, shopping cart has no product, set null
                    session.setAttribute("CART", null);
                    session.setAttribute("USER_PRODUCTS", null);

                    double total = newInvoce.getTotal();

                    request.setAttribute("TOTAL", total);

                    request.setAttribute("PAYER", payerInfo);
                    request.setAttribute("TRANSACTION", transaction);
                    request.setAttribute("INVOICE_ID", newInvoce.getId());

                    url = SUCCESS;
                }

            }

        } catch (Exception e) {
            log.info("Error at Complete Purchase Servlet: " + e.getMessage());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
