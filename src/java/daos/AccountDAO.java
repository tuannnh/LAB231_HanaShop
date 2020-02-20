/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Account;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author tuannnh
 */
public class AccountDAO implements Serializable{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab231_HanaShopPU");

    public Account checkLogin(String email, String password) throws Exception {
        EntityManager em = emf.createEntityManager();
        Account user = null;
        String sql = "Select a From Account a Where a.email = :email And a.password =:password";
        Query qr = em.createQuery(sql);
        qr.setParameter("email", email);
        qr.setParameter("password", password);

        if (qr.getResultList().size() > 0) {
            user = ((Account) qr.getSingleResult());
        }
        return user;
    }

    public Account findByEmail(String email) throws Exception {
        EntityManager em = emf.createEntityManager();
        return em.find(Account.class, email);

    }

    public boolean registerByForm(String email, String password) throws Exception {
        EntityManager em = emf.createEntityManager();
        Account user = em.find(Account.class, email);
        if (user != null) {
            return false;
        }

        user = new Account(email, password, "User");
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return true;

    }

    public boolean registerByGoogle(String email) throws Exception {
        EntityManager em = emf.createEntityManager();
        Account user = em.find(Account.class, email);
        if (user != null) {
            return false;
        }

        user = new Account(email, "User");
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return true;

    }

}
