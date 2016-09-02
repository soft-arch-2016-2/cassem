/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Client;
import DataAccess.Entity.Sale;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Milder
 */

public class SaleDAO {

    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("CassemPU");
    
    public Sale persist(Sale sale) {
        
        EntityManager em = emf1.createEntityManager();
        
        em.getTransaction().begin();
        try {
            em.persist(sale);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            sale = null;
        } finally {
            em.close();
        }
        return sale;
    }

    public List<Sale> searchAllSale() {
        
        EntityManager em = emf1.createEntityManager();
        List<Sale> query = null;
        
        try {
            query = em.createNamedQuery("Sale.findAll").getResultList();
        } catch (Exception e) {
        } finally {
            em.close();
        }
        
        return query;
    }
    
    public Sale delete(Sale sale){
        
        EntityManager em = emf1.createEntityManager();
        
        em.getTransaction().begin();
        try {
            em.remove(em.merge(sale));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            sale = null;
        } finally {
            em.close();
        }
        return sale;  
    }
    
    public Sale update(Sale oldSale, Sale newSale){
        
        EntityManager em = emf1.createEntityManager();
        
        em.getTransaction().begin();
        try {
            
            //oldSale.setName(newSale.getName());
            em.merge(oldSale);
            
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            oldSale = null;
        } finally {
            em.close();
        }
        return oldSale;  
    }
    
    
}
