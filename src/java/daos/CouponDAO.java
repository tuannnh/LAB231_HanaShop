/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Coupon;
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
public class CouponDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab231_HanaShopPU");

    public List<Coupon> getAllCoupons() throws Exception {
        List<Coupon> result;
        EntityManager em = emf.createEntityManager();
        result = em.createNamedQuery("Coupon.findAll").getResultList();
        em.close();
        return result;
    }

    public Coupon getCoupon(String coupon) throws Exception {
        EntityManager em = emf.createEntityManager();
        Coupon result = em.find(Coupon.class, coupon);
        em.close();
        return result;
    }

    public boolean addCoupon(Coupon newCoupon) throws Exception {
        boolean result = false;

        EntityManager em = emf.createEntityManager();
        Coupon coupon = getCoupon(newCoupon.getCoupon());
        if (coupon == null) {
            em.getTransaction().begin();
            em.persist(newCoupon);
            em.getTransaction().commit();
            em.close();
            result = true;
        }
        return result;
    }

    public void deleteCoupon(String coupon) throws Exception {
        EntityManager em = emf.createEntityManager();
        Coupon newCoupon = em.find(Coupon.class, coupon);
        StatusDAO dao = new StatusDAO();
        Status newStatus;
        if (newCoupon.getStatus().getStatusName().equals("Active")) {
            newStatus = dao.getStatus("Inactive");
            newCoupon.setStatus(newStatus);
        } else {
            newStatus = dao.getStatus("Active");
        }
        newCoupon.setStatus(newStatus);
        em.getTransaction().begin();
        em.merge(newCoupon);
        em.getTransaction().commit();
        em.close();
    }

}
