/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Car;
import DataAccess.Entity.Orders;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Fabian
 */
public class OrdersDAO {
    
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("CassemPU");
    
    public Orders persist( Orders order ) {
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(order);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            order = null;
        } finally {
            em.close();
        }
        return order;
    }
    
    
}
