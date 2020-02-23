/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Invoice;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.Cart;

/**
 *
 * @author tuannnh
 */
public class InvoiceDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab231_HanaShopPU");

    public Invoice createNormalInvoice(Cart cart) throws Exception {
        EntityManager em = emf.createEntityManager();
        Invoice newInvoice = new Invoice(new Date(), Float.parseFloat(cart.getTotal()), cart.getCustomer());
        em.getTransaction().begin();
        em.persist(newInvoice);
        em.getTransaction().commit();
        em.close();
        return newInvoice;
    }

    public Invoice createPaypalInvoice(Cart cart, String paypalId) throws Exception {
        EntityManager em = emf.createEntityManager();
        Invoice newInvoice = new Invoice(new Date(), Float.parseFloat(cart.getTotal()), cart.getCustomer(), paypalId);
        em.getTransaction().begin();
        em.persist(newInvoice);
        em.getTransaction().commit();
        em.close();
        return newInvoice;
    }

}
