/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Category;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tuannnh
 */
public class CategoryDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab231_HanaShopPU");

    public Category findById(int categoryId) throws Exception {
        EntityManager em = emf.createEntityManager();
        return em.find(Category.class, categoryId);
    }
    
    public List<Category> getAllCategories() throws Exception{
        EntityManager em = emf.createEntityManager();
        List<Category> categories = em.createNamedQuery("Category.findAll").getResultList();
        return categories;
    }
    
}
