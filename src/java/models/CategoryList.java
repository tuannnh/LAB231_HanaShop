/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import daos.CategoryDAO;
import entities.Category;
import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author tuannnh
 */
public class CategoryList implements Serializable {

    static Logger log = Logger.getLogger(CategoryList.class);
    List<Category> categories;

    public List<Category> getCategories() {
        try {
            CategoryDAO dao = new CategoryDAO();
            categories = dao.getAllCategories();
        } catch (Exception e) {
            log.info("Error at Category List Model: " + e.getMessage());
        }
        return categories;
    }

    public List<Category> getCategoriesAdmin() {
        try {
            CategoryDAO dao = new CategoryDAO();
            categories = dao.getAllCategoriesAdmin();
        } catch (Exception e) {
            log.info("Error at Category List Model: " + e.getMessage());
        }
        return categories;
    }

}
