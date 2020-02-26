/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Account;
import entities.Privilege;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author tuannnh
 */
public class AccountDAO implements Serializable {

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
        em.close();
        return user;
    }

    public Account findByEmail(String email) throws Exception {
        EntityManager em = emf.createEntityManager();
        Account result = em.find(Account.class, email);
        em.close();
        return result;

    }

    public boolean registerByForm(String email, String password) throws Exception {
        EntityManager em = emf.createEntityManager();
        Account user = em.find(Account.class, email);
        if (user != null) {
            return false;
        }
        PrivilegeDAO dao = new PrivilegeDAO();
        Privilege privilege = dao.getPrivilege("User");
        user = new Account(email, password, privilege);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return true;

    }

    public boolean registerByGoogle(String email) throws Exception {
        EntityManager em = emf.createEntityManager();
        Account user = em.find(Account.class, email);
        if (user != null) {
            return false;
        }

        PrivilegeDAO dao = new PrivilegeDAO();
        Privilege privilege = dao.getPrivilege("User");
        user = new Account(email, privilege);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return true;

    }

}
