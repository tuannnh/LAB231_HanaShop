/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import daos.SuggestProductDAO;
import entities.Product;
import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author tuannnh
 */
public class SuggestList implements Serializable {

    static Logger log = Logger.getLogger(SuggestList.class);

    List<Product> suggests;
    List<Product> purchasedItems;


    public List<Product> getSuggests() {
        try {
            SuggestProductDAO dao = new SuggestProductDAO();
            suggests = dao.getSuggestProducts(purchasedItems);
            System.out.println(suggests);
        } catch (Exception e) {
            log.info("Error at Suggest List Model: " + e.getMessage());
        }
        return suggests;
    }

    public void setPurchasedItems(List<Product> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

}
