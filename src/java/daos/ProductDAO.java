/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Product;
import entities.Status;
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

    public void updateProduct(Product updateProduct) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(updateProduct);
        em.getTransaction().commit();
        em.close();
    }

    public List<Product> getAllProducts() throws Exception {
        EntityManager em = emf.createEntityManager();
        List<Product> products = em.createNamedQuery("Product.findAll").getResultList();
        em.close();
        return products;
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
                    product.setQuantity(quantity);
                    em.merge(product);
                    em.flush();
                }
            }
        }
        em.getTransaction().commit();
        em.close();
    }

    public boolean isAvailable(int id, int quantity) throws Exception {
        boolean result = false;
        EntityManager em = emf.createEntityManager();
        Query qr = em.createQuery("SELECT p FROM Product p WHERE p.id = :id AND p.status.statusName = 'Active' AND p.quantity >= :quantity");

        if (qr.setParameter("id", id).setParameter("quantity", quantity).getResultList().size() > 0) {
            result = true;
        }
        return result;
    }

    public List<Product> searchByAdmin(String searchName, String searchStatus, String category) throws Exception {
        EntityManager em = emf.createEntityManager();
        Query qr;
        if (category.equals("0") && !searchStatus.equals("All")) {
            qr = em.createQuery("SELECT p FROM Product p "
                    + "WHERE p.name "
                    + "LIKE :searchName "
                    + "AND p.status.statusName = :status "
                    + "ORDER BY p.createDate DESC");

            qr.setParameter("searchName", "%" + searchName + "%");
            qr.setParameter("status", searchStatus);
        } else if (!category.equals("0") && searchStatus.equals("All")) {
            qr = em.createQuery("SELECT p FROM Product p "
                    + "WHERE p.name "
                    + "LIKE :searchName "
                    + "AND p.category.id = :category "
                    + "ORDER BY p.createDate DESC");

            qr.setParameter("searchName", "%" + searchName + "%");
            qr.setParameter("category", Integer.parseInt(category));
        } else if (category.equals("0") && searchStatus.equals("All")) {
            qr = em.createQuery("SELECT p FROM Product p "
                    + "WHERE p.name "
                    + "LIKE :searchName "
                    + "ORDER BY p.createDate DESC");

            qr.setParameter("searchName", "%" + searchName + "%");
        } else {
            qr = em.createQuery("SELECT P FROM Product p WHERE p.name "
                    + "LIKE :searchName "
                    + "AND p.category.id = :category "
                    + "AND p.status.statusName = :status "
                    + "ORDER BY p.createDate DESC");
            qr.setParameter("searchName", "%" + searchName + "%");
            qr.setParameter("status", searchStatus);
            qr.setParameter("category", Integer.parseInt(category));

        }
        List<Product> products = qr.getResultList();
        em.close();
        return products;
    }

    public List<Product> searchByUser(String searchName, String searchMin, String searchMax, String category) throws Exception {
        EntityManager em = emf.createEntityManager();
        Query qr = null;
        if (searchMin.equals("")) {
            searchMin = "1";
        }
        if (searchMax.equals("")) {
            searchMax = "5000";
        }
        if (category.equals("0")) {
            qr = em.createQuery("SELECT p FROM Product p "
                    + "WHERE p.name "
                    + "LIKE :searchName "
                    + "AND p.price >= :searchMin "
                    + "AND p.price <= :searchMax "
                    + "AND p.status.statusName = 'Active' "
                    + "AND p.quantity > 0 "
                    + "ORDER BY p.createDate DESC");
            qr.setParameter("searchName", "%" + searchName + "%");
            qr.setParameter("searchMin", Float.parseFloat(searchMin));
            qr.setParameter("searchMax", Float.parseFloat(searchMax));
        } else {
            qr = em.createQuery("SELECT P FROM Product p WHERE p.name "
                    + "LIKE :searchName "
                    + "AND p.price >= :searchMin "
                    + "AND p.price <= :searchMax "
                    + "AND p.category.id = :category "
                    + "AND p.status.statusName = 'Active' "
                    + "AND p.quantity > 0 "
                    + "ORDER BY p.createDate DESC");
            qr.setParameter("searchName", "%" + searchName + "%");
            qr.setParameter("searchMin", Float.parseFloat(searchMin));
            qr.setParameter("searchMax", Float.parseFloat(searchMax));
            qr.setParameter("category", Integer.parseInt(category));

        }
        List<Product> products = qr.getResultList();
        em.close();
        return products;
    }
    
    

    public void deleteProduct(int id) throws Exception {
        EntityManager em = emf.createEntityManager();
        Product deleteProduct = findById(id);
        StatusDAO dao = new StatusDAO();
        Status inactive = dao.getStatus("Inactive");
        deleteProduct.setStatus(inactive);
        em.getTransaction().begin();
        em.merge(deleteProduct);
        em.getTransaction().commit();
        em.close();

    }

}
