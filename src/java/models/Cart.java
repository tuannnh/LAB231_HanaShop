/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import daos.ProductDAO;
import entities.Account;
import entities.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tuannnh
 */
public class Cart implements Serializable {

    private Account customer;
    private List<Product> purchasedItems;

    public Account getCustomer() {
        return customer;
    }

    public void setCustomer(Account customer) {
        this.customer = customer;
    }

    public List<Product> getPurchasedItems() {

        return purchasedItems;
    }

    public void setPurchasedItems(List<Product> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    public Cart() {
    }

    public Cart(Account customer) {
        this.customer = customer;
        purchasedItems = new ArrayList<>();

    }

    public boolean addToCart(Product product) throws Exception {
        ProductDAO dao = new ProductDAO();

        for (Product item : purchasedItems) {
            // already have product in cart, increase quantity
            if (item.equals(product)) {
                int quantity = item.getQuantity() + 1;
                if (dao.isAvailable(item.getId(), quantity)) {
                    item.setQuantity(quantity);
                    return true;
                } else {
                    return false;
                }
            }
        }
        //Add new product to cart
        if (dao.isAvailable(product.getId(), 1)) {
            product.setQuantity(1);
            purchasedItems.add(product);
            return true;
        } else {
            return false;
        }
    }

    public String getTotal() throws Exception {
        float result = 0;
        for (Product item : purchasedItems) {
            result += item.getPrice() * item.getQuantity();
        }
        return String.format("%.1f", result);
    }

    public void updateCart(int id, int quantity) throws Exception {
        for (Product item : purchasedItems) {
            if (item.getId() == id) {
                item.setQuantity(quantity);
            }
        }
    }

    public int getItemQuantity(int id) throws Exception {
        int quantity = 1;
        for (Product item : purchasedItems) {
            if (item.getId() == id) {
                quantity = item.getQuantity();
            }
        }

        return quantity;
    }

    public void removeFromCart(int id) throws Exception {
        purchasedItems.removeIf(item -> item.getId().equals(id));
    }

    public boolean isAvailableCart() throws Exception {
        ProductDAO dao = new ProductDAO();
        boolean result = true;
        List<Product> stock = dao.getAllAvailableProducts();
        for (Product product : stock) {
            for (Product purchasedItem : purchasedItems) {
                if (purchasedItem.equals(product)) {
                    if (purchasedItem.getQuantity() > product.getQuantity()) {
                        purchasedItem.setError("Out of stock!");
                        result = false;
                    } else {
                        purchasedItem.setError("");
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Cart{" + "customer=" + customer + ", purchasedItems=" + purchasedItems + '}';
    }

}
