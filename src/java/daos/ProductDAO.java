/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Product;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import models.Cart;

/**
 *
 * @author tuannnh
 */
public class ProductDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab231_HanaShopPU");

    public Product findById(int id) throws Exception {
        EntityManager em = emf.createEntityManager();
        return em.find(Product.class, id);
    }

    public void createProduct(Product newProduct) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(newProduct);
        em.getTransaction().commit();
        em.close();
    }

    public List<Product> getAllAvailableProducts() throws Exception {
        EntityManager em = emf.createEntityManager();
        List<Product> products = em.createNamedQuery("Product.findAllAvailable").getResultList();
        em.close();
        return products;
    }

    public void updateQuantity(Cart cart) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<Product> allProducts = getAllAvailableProducts();
        int quantity;
        em.getTransaction().begin();
         for (Product product : allProducts) {
             for (Product purchase : cart.getPurchasedItems()) {
                if (purchase.equals(product)) {
                    quantity = product.getQuantity() - purchase.getQuantity();
                    System.out.println("Before change: " + product);
                    product.setQuantity(quantity);
                    em.merge(product);
                    em.flush();
                    System.out.println("After change: " +product);
                }
            }
         }
        em.getTransaction().commit();
        em.close();
    }

    public boolean isAvailable(int id, int quantity) throws Exception {
        boolean result = false;
        EntityManager em = emf.createEntityManager();
        Query qr = em.createQuery("SELECT p FROM Product p WHERE p.id = :id AND p.status = 'Active' AND p.quantity >= :quantity");

        if (qr.setParameter("id", id).setParameter("quantity", quantity).getResultList().size() > 0) {
            result = true;
        }
        return result;
    }

    public List<Product> searchByUser(String searchName, String searchMin, String searchMax, String categoryId) throws Exception {
        EntityManager em = emf.createEntityManager();
        Query qr = null;
        if (categoryId.equals("0")) {
            qr = em.createQuery("SELECT p FROM Product p "
                    + "WHERE p.name "
                    + "LIKE :searchName "
                    + "AND p.price >= :searchMin "
                    + "AND p.price <= :searchMax "
                    + "AND p.status = 'Active' "
                    + "AND p.quantity > 0");
            qr.setParameter("searchName", "%" + searchName + "%");
            qr.setParameter("searchMin", Float.parseFloat(searchMin));
            qr.setParameter("searchMax", Float.parseFloat(searchMax));
        } else {
            qr = em.createQuery("SELECT P FROM Product p WHERE p.name "
                    + "LIKE :searchName "
                    + "AND p.price >= :searchMin "
                    + "AND p.price <= :searchMax "
                    + "AND p.categoryId.id = :categoryId "
                    + "AND p.status = 'Active' "
                    + "AND p.quantity > 0 "
                    + "ORDER BY p.createDate DESC");
            qr.setParameter("searchName", "%" + searchName + "%");
            qr.setParameter("searchMin", Float.parseFloat(searchMin));
            qr.setParameter("searchMax", Float.parseFloat(searchMax));
            qr.setParameter("categoryId", Integer.parseInt(categoryId));

        }
        List<Product> products = qr.getResultList();
        em.close();
        return products;
    }
}
