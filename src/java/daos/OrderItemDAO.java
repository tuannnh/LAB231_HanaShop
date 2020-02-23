/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Invoice;
import entities.OrderItem;
import entities.Product;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.Cart;

/**
 *
 * @author tuannnh
 */
public class OrderItemDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab231_HanaShopPU");

    public void createOrderItem(Cart cart, Invoice invoice) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        for (Product item : cart.getPurchasedItems()) {
            OrderItem newOrderItem = new OrderItem(item.getQuantity(), item.getPrice(),invoice, item);
            invoice.getOrderItemList().add(newOrderItem);
            em.persist(newOrderItem);
        }
        em.getTransaction().commit();
        em.close();
    }
}
