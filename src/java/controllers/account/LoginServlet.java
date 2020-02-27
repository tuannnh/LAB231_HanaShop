/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.account;

import daos.AccountDAO;
import entities.Account;
import google.VerifyRecaptcha;
import java.io.IOException;
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
public class LoginServlet extends HttpServlet {

    static Logger log = Logger.getLogger(LoginServlet.class);
    private static final String ERROR = "login.jsp";
    private static final String USER = "index.jsp";
    private static final String ADMIN = "admin-food.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        String code = request.getParameter("code");

        try {
            if (code == null || code.isEmpty()) {
                // Not login by google
                String email = request.getParameter("txtEmail");
                String password = request.getParameter("txtPassword");
                String gRecaptchaResponse = request
                        .getParameter("g-recaptcha-response");
                boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
                if (!verify) {
                    request.setAttribute("RECATPCHA_MESSAGE", "You missed the recatpcha!");
                } else {
                    AccountDAO dao = new AccountDAO();
                    Account user = dao.checkLogin(email, password);
                    if (user != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("USER", user);
                        if (user.getPrivilege().getPrivilegeName().equals("Admin")) {
                            url = ADMIN;
                        } else {
                            url = USER;
                        }
                    }
                    if (url.equals(ERROR)) {
                        request.setAttribute("ERROR_MESSAGE", "Email or Password is not correct!");
                    }
                }

            } else {
                //Login by google
                String accessToken = google.GoogleUtils.getTokenLogin(code);
                google.GooglePojo userObj = google.GoogleUtils.getUserInfo(accessToken);
                String email = userObj.getEmail();
                AccountDAO dao = new AccountDAO();
                Account user = dao.findByEmail(email);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                    if (user.getPrivilege().getPrivilegeName().equals("Admin")) {
                        url = ADMIN;
                    } else {
                        url = USER;
                    }
                }
                if (url.equals(ERROR)) {
                    request.setAttribute("ERROR_MESSAGE", "Email or Password is not correct!");
                }
            }

        } catch (Exception e) {
            log.info("Error at Login Servlet: " + e.getMessage());
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
