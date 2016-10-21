/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Client;
import DataAccess.Entity.EmployeeDecreasePart;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EDPDAO implements Serializable{
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("CassemPUR");
    
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

    public List<EmployeeDecreasePart> searchAllEDP() {
        
        EntityManager em = emf1.createEntityManager();
        List<EmployeeDecreasePart> query = null;
        
        try {
            query = em.createNamedQuery("EmployeeDecreasePart.findAll").getResultList();
        } catch (Exception e) {
        } finally {
            em.close();
        }
        
        return query;
    }
    
   
    

}
