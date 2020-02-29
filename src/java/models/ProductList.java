/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import daos.ProductDAO;
import entities.Product;
import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author tuannnh
 */
public class ProductList implements Serializable {

    static Logger log = Logger.getLogger(ProductList.class);
    List<Product> products;


    public List<Product> getProducts() {
        try {
            ProductDAO dao = new ProductDAO();
            products = dao.getAllAvailableProducts();
        } catch (Exception e) {
            log.info("Error at Product List Model: " + e.getMessage());
        }
        return products;
    }

    public List<Product> getProductsAdmin() {
        try {
            ProductDAO dao = new ProductDAO();
            products = dao.getAllProducts();
        } catch (Exception e) {
            log.info("Error at Product List Model: " + e.getMessage());
        }
        return products;
    }

}
