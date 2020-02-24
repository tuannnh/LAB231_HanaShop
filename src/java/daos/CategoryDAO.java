/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Category;
import entities.Product;
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

    public List<Category> getAllCategories() throws Exception {
        EntityManager em = emf.createEntityManager();
        List<Category> categories = em.createNamedQuery("Category.findAll").getResultList();
        em.close();
        return categories;
    }

    public boolean createCategory(String name) throws Exception {
        EntityManager em = emf.createEntityManager();

        if (em.createNamedQuery("Category.findByName").setParameter("name", name).getResultList().size() < 1) {
            Category category = new Category(name);
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        return false;
    }

    public boolean updateCategory(int id, String name) throws Exception {
        EntityManager em = emf.createEntityManager();
        Category category = em.find(Category.class, id);
        if (em.createNamedQuery("Category.findByName").setParameter("name", name).getResultList().size() < 1) {
            category.setName(name);
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        return false;
    }

    public String deleteCategory(int id) throws Exception {
        EntityManager em = emf.createEntityManager();

        Category deleteCategory = em.find(Category.class, id);
        List<Product> usingProducts = deleteCategory.getProductList();
        if (usingProducts.size() < 1) {
            em.getTransaction().begin();
            em.remove(deleteCategory);
            em.getTransaction().commit();
            em.close();
            return "";
        }
        return deleteCategory.getName();

    }

}
