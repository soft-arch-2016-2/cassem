/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Orders;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Fabian
 */
public class OrderDAO implements Serializable{
    
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("CassemPUR");
    
    
     public Orders persist(Orders order) {
        
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
     
     public Orders update(Orders newOrder){
        
        EntityManager em = emf1.createEntityManager();
        
        em.getTransaction().begin();
        try {

            em.merge(newOrder);
            
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            newOrder = null;
        } finally {
            em.close();
        }
        return newOrder;  
    }
    
    
    public List<Orders> listAllOrders(){
        EntityManager em = emf1.createEntityManager();
        List<Orders> orders = null;
        try {
            orders = em.createNamedQuery("Orders.findAll").getResultList();
        } catch (Exception e) {
            e.getStackTrace();
        }finally{
            em.close();
        }
        return orders;
    }
    
}
