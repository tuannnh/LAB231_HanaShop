/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import entities.Account;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author tuannnh
 */
public class FilterDispatcher implements Filter {

    private static final String HOME = "index.jsp";
    private static final String LOGIN = "login.jsp";
    private static final boolean debug = true;
    private FilterConfig filterConfig = null;
    static Logger log = Logger.getLogger(FilterDispatcher.class);

    public FilterDispatcher() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterDispatcher:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
	/*
         for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         String values[] = request.getParameterValues(name);
         int n = values.length;
         StringBuffer buf = new StringBuffer();
         buf.append(name);
         buf.append("=");
         for(int i=0; i < n; i++) {
         buf.append(values[i]);
         if (i < n-1)
         buf.append(",");
         }
         log(buf.toString());
         }
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterDispatcher:DoAfterProcessing");
        }

	// Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
	/*
         for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         Object value = request.getAttribute(name);
         log("attribute: " + name + "=" + value.toString());

         }
         */
        // For example, a filter might append something to the response.
	/*
         PrintWriter respOut = new PrintWriter(response.getWriter());
         respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String uri = httpRequest.getRequestURI();
        String url = HOME;
        try {
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex + 1);

            if (resource.length() > 0) {
                url = resource + "Servlet";

                if (resource.lastIndexOf(".jsp") > 0) {
                    url = resource;

                }

                if (resource.contains(".png") || resource.contains(".jpg")) {
                    url = resource;
                }

                if (resource.contains("CreateFood") || resource.contains("UpdateFood")) {
                    url = resource;
                }
            }

            if (uri.lastIndexOf("/assets") > 0) {
                url = uri.substring(lastIndex + 1);
            }

            //Authorize
            List<String> guestExceptions = new ArrayList<>();
            guestExceptions.add("Logout");
            guestExceptions.add("AddToCart");
            guestExceptions.add("CompletePurchase");
            guestExceptions.add("ReviewPurchase");
            guestExceptions.add("SearchHistory");
            guestExceptions.add("UpdateCart");
            guestExceptions.add("AuthorizePaypal");
            guestExceptions.add("AdminSearch");
            guestExceptions.add("CreateFood");
            guestExceptions.add("DeleteFood");
            guestExceptions.add("ViewFood");
            guestExceptions.add("UpdateFood");
            guestExceptions.add("ViewFood");
            guestExceptions.add("ChangePageAdmin");
            guestExceptions.add("CreateCategory");
            guestExceptions.add("UpdateCategory");
            guestExceptions.add("DeleteCategory");
            guestExceptions.add("admin-food.jsp");
            guestExceptions.add("admin-category.jsp");
            guestExceptions.add("create-food.jsp");
            guestExceptions.add("view-food.jsp");
            guestExceptions.add("review.jsp");
            guestExceptions.add("receipt.jsp");
            guestExceptions.add("history.jsp");
            guestExceptions.add("cart.jsp");

            List<String> userExceptions = new ArrayList<>();
            userExceptions.add("Login");
            userExceptions.add("Register");
            userExceptions.add("AdminSearch");
            userExceptions.add("CreateFood");
            userExceptions.add("DeleteFood");
            userExceptions.add("ViewFood");
            userExceptions.add("UpdateFood");
            userExceptions.add("ChangePageAdmin");
            userExceptions.add("CreateCategory");
            userExceptions.add("UpdateCategory");
            userExceptions.add("DeleteCategory");
            userExceptions.add("admin-food.jsp");
            userExceptions.add("admin-category.jsp");
            userExceptions.add("create-food.jsp");
            userExceptions.add("register.jsp");
            userExceptions.add("login.jsp");
            userExceptions.add("view-food.jsp");

            List<String> adminExceptions = new ArrayList<>();
            adminExceptions.add("Login");
            adminExceptions.add("Register");
            adminExceptions.add("AddToCart");
            adminExceptions.add("CompletePurchase");
            adminExceptions.add("ReviewPurchase");
            adminExceptions.add("SearchHistory");
            adminExceptions.add("UpdateCart");
            adminExceptions.add("AuthorizePaypal");
            adminExceptions.add("ChangePage");
            userExceptions.add("register.jsp");
            userExceptions.add("login.jsp");
            adminExceptions.add("cart.jsp");
            adminExceptions.add("history.jsp");
            adminExceptions.add("receipt.jsp");
            adminExceptions.add("review.jsp");

            HttpSession session = httpRequest.getSession();
            Account account = (Account) session.getAttribute("USER");
            String privilege = null;
            if (account != null) {
                privilege = account.getPrivilege();
            }

            if (resource.length() > 0) {
                //NOT LOGGED IN
                if (privilege == null) {

                    for (String guestException : guestExceptions) {
                        if (resource.contains(guestException)) {
                            httpResponse.sendRedirect(LOGIN);
                        }
                    }

                } else {// Logged in
                    if (privilege.equals("Admin")) {
                        for (String adminException : adminExceptions) {
                            if (resource.contains(adminException)) {
                                httpResponse.sendRedirect(HOME);
                            }
                        }
                    } else if (privilege.equals("User")) {
                        for (String userException : userExceptions) {
                            if (resource.contains(userException)) {
                                httpResponse.sendRedirect(HOME);
                            }
                        }
                    }
                }
            }

            if (url != null) {
                request.getRequestDispatcher(url).forward(httpRequest, response);
            } else {
                chain.doFilter(request, response);
            }

        } catch (Throwable t) {
            log.info("Error at Filter Dispatcher: " + t.getMessage());
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FilterDispatcher:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterDispatcher()");
        }
        StringBuffer sb = new StringBuffer("FilterDispatcher(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
