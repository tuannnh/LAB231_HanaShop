/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.CategoryDAO;
import daos.ProductDAO;
import entities.Category;
import entities.Product;
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
import javax.servlet.http.Part;
import org.apache.log4j.Logger;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)   	// 100 MB
public class CreateFoodServlet extends HttpServlet {

    static Logger log = Logger.getLogger(CreateFoodServlet.class);
    private static final String URL = "AdminSearchServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String name = getValue(request.getPart("txtName"));
            String description = getValue(request.getPart("txtDescription"));
            String price = getValue(request.getPart("txtPrice"));
            String quantity = getValue(request.getPart("txtQuantity"));
            String categoryId = getValue(request.getPart("txtCategory"));

            CategoryDAO cdao = new CategoryDAO();
            Category category = cdao.findById(Integer.parseInt(categoryId));

            Date createDate = new Date();
            String status = "Active";

            //Save Image
            Part filePart = request.getPart("image"); // Retrieves <input type="file" name="image">
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            File savePath = new File("/Users/tuannnh/Desktop/images");
            if (!savePath.exists()) {
                savePath.mkdir();
            }
            File file = new File(savePath, fileName);

            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            String imageURL = "/images/" + fileName;
            Product newProduct = new Product(name, imageURL, description, Float.parseFloat(price), createDate, Integer.parseInt(quantity), category, status);
            ProductDAO pdao = new ProductDAO();
            pdao.createProduct(newProduct);
        } catch (Exception e) {
            log.info("Error at Create Food Servlet: " + e.getMessage());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(URL).forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static String getValue(Part part) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[1024];
        for (int length = 0; (length = reader.read(buffer)) > 0;) {
            value.append(buffer, 0, length);
        }
        return value.toString();
    }
}
