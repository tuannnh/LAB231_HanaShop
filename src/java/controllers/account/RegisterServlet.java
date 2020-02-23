/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.account;

import daos.AccountDAO;
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
public class RegisterServlet extends HttpServlet {

    static Logger log = Logger.getLogger(RegisterServlet.class);
    private static final String ERROR = "register.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        String code = request.getParameter("code");
        try {
            if (code == null || code.isEmpty()) {
                //Register by filling form
                String email = request.getParameter("txtEmail");
                String password = request.getParameter("txtPassword");
                AccountDAO dao = new AccountDAO();
                boolean result = dao.registerByForm(email, password);
                if (result) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR_MESSAGE", "The email is registered!");
                }
            } else {
                // Register by Google
                String accessToken = google.GoogleUtils.getTokenRegister(code);
                System.out.println("Access token: " + accessToken);
                google.GooglePojo userObj = google.GoogleUtils.getUserInfo(accessToken);
                String email = userObj.getEmail();
                System.out.println(email + "This is email");
                AccountDAO dao = new AccountDAO();
                boolean result = dao.registerByGoogle(email);
                if (result) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR_MESSAGE", "The email is registered!");
                }
            }
            if (url.equals(ERROR)) {
                request.setAttribute("ERROR_MESSAGE", "Register Error!");
            }
        } catch (Exception e) {
            log.info("Error at Register Servlet: " + e.getMessage());
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
