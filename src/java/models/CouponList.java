/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import daos.CouponDAO;
import entities.Coupon;
import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author tuannnh
 */
public class CouponList implements Serializable {

    static Logger log = Logger.getLogger(CouponList.class);
    List<Coupon> coupons;

    public List<Coupon> getCoupons() {
        try {
            CouponDAO dao = new CouponDAO();
            coupons = dao.getAllCoupons();
        } catch (Exception e) {
            log.info("Error at Category List Model: " + e.getMessage());
        }
        return coupons;
    }
}
