/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Privilege;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tuannnh
 */
public class PrivilegeDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab231_HanaShopPU");

    public Privilege getPrivilege(String privilegeName) {
        EntityManager em = emf.createEntityManager();
        Privilege privilege = em.find(Privilege.class, privilegeName);
        em.close();
        return privilege;
    }
}
