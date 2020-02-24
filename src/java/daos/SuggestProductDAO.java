/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Invoice;
import entities.OrderItem;
import entities.Product;
import entities.SuggestProduct;
import java.io.Serializable;
import java.util.ArrayList;
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
public class SuggestProductDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab231_HanaShopPU");

    public boolean checkSuggestionIsExisted(Product mainProduct, Product suggestProduct) throws Exception {
        EntityManager em = emf.createEntityManager();
        Query qr = em.createQuery("SELECT sp FROM SuggestProduct sp WHERE sp.mainProduct = :mainProduct AND sp.suggestProduct = :suggestProduct");
        qr.setParameter("mainProduct", mainProduct.getId());
        qr.setParameter("suggestProduct", suggestProduct.getId());
        List<SuggestProduct> result = qr.getResultList();
        if (result.size() > 0) {
            return true;
        }
        return false;
    }

    public void addSuggestProduct(Invoice newInvoice) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<OrderItem> orderItems = newInvoice.getOrderItemList();
        SuggestProduct newSuggestProduct = null;
        em.getTransaction().begin();
        for (OrderItem orderItemMain : orderItems) {
            for (OrderItem orderItemSuggest : orderItems) {
                if (!checkSuggestionIsExisted(orderItemMain.getProductId(), orderItemSuggest.getProductId())
                        && !orderItemMain.equals(orderItemSuggest)) {
                    newSuggestProduct = new SuggestProduct(orderItemMain.getProductId().getId(), orderItemSuggest.getProductId().getId());
                    em.persist(newSuggestProduct);

                }
            }

        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Product> getSuggestProducts(Cart cart) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        Product newProduct;
        EntityManager em = emf.createEntityManager();
        List<Product> suggestList = new ArrayList<>();
        List<SuggestProduct> suggestProducts;
        if (cart != null) {
            List<Product> purchasedItem = cart.getPurchasedItems();
            Query qr;
            for (Product item : purchasedItem) {
                qr = em.createQuery("SELECT sp FROM SuggestProduct sp WHERE sp.mainProduct = :item");

                qr.setParameter("item", item.getId());
                suggestProducts = qr.getResultList();
                for (SuggestProduct suggestItem : suggestProducts) {
                    newProduct = productDAO.findById(suggestItem.getSuggestProduct());
                    if (!suggestList.contains(newProduct)
                            && !purchasedItem.contains(newProduct)) {
                        suggestList.add(newProduct);
                    }

                }
            }
        }
        return suggestList;
    }
}
