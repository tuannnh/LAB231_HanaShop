/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Account;
import entities.Invoice;
import java.io.Serializable;
import java.util.Date;
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
public class InvoiceDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab231_HanaShopPU");

    public Invoice createNormalInvoice(Cart cart) throws Exception {
        EntityManager em = emf.createEntityManager();
        Invoice newInvoice;
        if (cart.getCoupon() != null) {
            newInvoice = new Invoice(new Date(), Double.parseDouble(cart.getTotal()), cart.getCustomer(), cart.getCoupon());
        } else {
            newInvoice = new Invoice(new Date(), Double.parseDouble(cart.getTotal()), cart.getCustomer());
        }
        em.getTransaction().begin();
        em.persist(newInvoice);
        em.getTransaction().commit();
        em.close();
        return newInvoice;
    }

    public Invoice createPaypalInvoice(Cart cart, String paypalId) throws Exception {
        EntityManager em = emf.createEntityManager();
        Invoice newInvoice;
        if (cart.getCoupon() != null) {
            newInvoice = new Invoice(new Date(), Double.parseDouble(cart.getTotal()), cart.getCustomer(), paypalId, cart.getCoupon());
        } else {
            newInvoice = new Invoice(new Date(), Double.parseDouble(cart.getTotal()), cart.getCustomer(), paypalId);
        }
        em.getTransaction().begin();
        em.persist(newInvoice);
        em.getTransaction().commit();
        em.close();
        return newInvoice;
    }

    public List<Invoice> getAllInvoce() throws Exception {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Invoice.findAll").getResultList();
    }

    public List<Invoice> searchHistoryByProductName(Account customer, String searchName, Date searchDateStart, Date searchDateEnd) throws Exception {
        EntityManager em = emf.createEntityManager();
        Query qr = em.createQuery("SELECT i FROM Invoice i "
                + "WHERE i.customer = :customer "
                + "AND i.createDate >= :searchDateStart AND i.createDate <= :searchDateEnd "
                + "AND i IN "
                + "(SELECT o.invoice FROM OrderItem o WHERE o.product IN "
                + "(SELECT p FROM Product p WHERE p.name LIKE :searchName)) "
                + "ORDER BY i.createDate DESC");
        qr.setParameter("customer", customer);
        qr.setParameter("searchDateStart", searchDateStart);
        qr.setParameter("searchDateEnd", searchDateEnd);
        qr.setParameter("searchName", "%" + searchName + "%");
        List<Invoice> result = qr.getResultList();
        em.close();
        return result;
    }
    
    public void addRating(int invoiceId, double rating) throws Exception{
           EntityManager em = emf.createEntityManager();
           Invoice rateInvoice = em.find(Invoice.class, invoiceId);
           rateInvoice.setRating(rating);
           em.getTransaction().begin();
           em.merge(rateInvoice);
           em.getTransaction().commit();
           em.close();
    }

}
