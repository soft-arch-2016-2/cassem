/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.CarHasPart;
import DataAccess.Entity.Part;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Fabian
 */
public class CarHasPartDAO {
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("CassemPU");
    
    public CarHasPart persist(CarHasPart carHasPart) {
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(carHasPart);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            carHasPart = null;
        } finally {
            em.close();
        }
        return carHasPart;
    }
}
