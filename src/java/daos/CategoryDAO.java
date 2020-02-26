/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Category;
import entities.Product;
import entities.Status;
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

    public Category findById(int category) throws Exception {
        EntityManager em = emf.createEntityManager();
        return em.find(Category.class, category);
    }

    public List<Category> getAllCategories() throws Exception {
        EntityManager em = emf.createEntityManager();
        List<Category> categories = em.createNamedQuery("Category.findAllAvailable").getResultList();
        em.close();
        return categories;
    }

    public List<Category> getAllCategoriesAdmin() throws Exception {
        EntityManager em = emf.createEntityManager();
        List<Category> categories = em.createNamedQuery("Category.findAll").getResultList();
        em.close();
        return categories;
    }

    public boolean createCategory(String name) throws Exception {
        EntityManager em = emf.createEntityManager();

        if (em.createNamedQuery("Category.findByName").setParameter("name", name).getResultList().size() < 1) {
            Category category = new Category(name);
            StatusDAO dao = new StatusDAO();
            Status newStatus = dao.getStatus("Active");
            category.setStatus(newStatus);
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
        if (!em.createNamedQuery("Category.findByName").setParameter("name", name).getResultList().contains(category))  {
            category.setName(name);
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
            em.close();
            return true;
        }

        return false;
    }

    public void deleteCategory(int id) throws Exception {
        EntityManager em = emf.createEntityManager();
        Category category = em.find(Category.class, id);
        StatusDAO dao = new StatusDAO();
        Status newStatus;
        if (category.getStatus().getStatusName().equals("Active")) {
            newStatus = dao.getStatus("Inactive");
            category.setStatus(newStatus);
        } else {
            newStatus = dao.getStatus("Active");
        }
        category.setStatus(newStatus);
        em.getTransaction().begin();
        em.merge(category);
        em.getTransaction().commit();
        em.close();
    }
}
