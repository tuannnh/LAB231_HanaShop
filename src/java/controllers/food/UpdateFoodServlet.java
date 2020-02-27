/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.food;

import static controllers.food.CreateFoodServlet.log;
import daos.CategoryDAO;
import daos.ProductDAO;
import daos.StatusDAO;
import entities.Account;
import entities.Category;
import entities.Product;
import entities.Status;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author tuannnh
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)   	// 100 MB
public class UpdateFoodServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            String id = getValue(request.getPart("txtId"));
            String name = getValue(request.getPart("txtName"));
            String description = getValue(request.getPart("txtDescription"));
            String price = getValue(request.getPart("txtPrice"));
            String quantity = getValue(request.getPart("txtQuantity"));
            String category = getValue(request.getPart("txtCategory"));
            String statusName = getValue(request.getPart("txtStatus"));
            String imageURL = getValue(request.getPart("txtImageURL"));

            CategoryDAO cdao = new CategoryDAO();
            Category newCategory = cdao.findById(Integer.parseInt(category));

            Date modifiedDate = new Date();
            Account modifiedBy = (Account) session.getAttribute("USER");
            //Save Image

            Part filePart = request.getPart("image"); // Retrieves <input type="file" name="image">
            if (filePart.getSize() > 0) {

                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                File savePath = new File("/Users/tuannnh/Desktop/images");
                if (!savePath.exists()) {
                    savePath.mkdir();
                }
                File file = new File(savePath, fileName);

                try (InputStream fileContent = filePart.getInputStream()) {
                    Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                imageURL = "images/" + fileName;
            }

            ProductDAO pdao = new ProductDAO();
            Product updateProduct = pdao.findById(Integer.parseInt(id));

            updateProduct.setName(name);
            updateProduct.setImageURL(imageURL);
            updateProduct.setDescription(description);
            updateProduct.setPrice(Double.parseDouble(price));
            updateProduct.setQuantity(Integer.parseInt(quantity));
            updateProduct.setCategory(newCategory);

            StatusDAO statusDAO = new StatusDAO();
            Status status = statusDAO.getStatus(statusName);

            updateProduct.setStatus(status);
            updateProduct.setModifiedDate(modifiedDate);
            updateProduct.setModifiedBy(modifiedBy);
            pdao.updateProduct(updateProduct);

            //After make change of list, refresh
            session.setAttribute("ADMIN_PRODUCTS", null);
            session.setAttribute("USER_PRODUCTS", null);
        } catch (Exception e) {
            log.info("Error at Update Food Servlet: " + e.getMessage());
        } finally {
            response.sendRedirect("AdminSearch");
        }

    }

    private static String getValue(Part part) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[1024];
        for (int length = 0; (length = reader.read(buffer)) > 0;) {
            value.append(buffer, 0, length);
        }
        return value.toString();
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
