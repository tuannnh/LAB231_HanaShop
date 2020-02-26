/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Status;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tuannnh
 */
public class StatusDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab231_HanaShopPU");

    public Status getStatus(String statusName) throws Exception {
        EntityManager em = emf.createEntityManager();
        Status result = em.find(Status.class, statusName);
        em.close();
        return result;
    }
}
