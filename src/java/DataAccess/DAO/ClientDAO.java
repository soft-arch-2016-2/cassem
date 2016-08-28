/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Client;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Maikol
 */

public class ClientDAO {

    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("CassemPU");
    
    public Client persist(Client client) {
        
        EntityManager em = emf1.createEntityManager();
        
        em.getTransaction().begin();
        try {
            em.persist(client);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            client = null;
        } finally {
            em.close();
        }
        return client;
    }

    public List<Client> searchAllClient() {
        
        EntityManager em = emf1.createEntityManager();
        List<Client> query = null;
        
        try {
            query = em.createNamedQuery("Client.findAll").getResultList();
        } catch (Exception e) {
        } finally {
            em.close();
        }
        
        return query;
    }
    
    public Client delete(Client client){
        
        EntityManager em = emf1.createEntityManager();
        
        em.getTransaction().begin();
        try {
            em.remove(em.merge(client));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            client = null;
        } finally {
            em.close();
        }
        return client;  
    }
    
}
