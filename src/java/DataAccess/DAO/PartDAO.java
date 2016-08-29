/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Part;
import DataAccess.Entity.Part;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



public class PartDAO {
    
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("CassemPU");

    public Part persist(Part part) {
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(part);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            part = null;
        } finally {
            em.close();
        }
        return part;
    }
    
    public List<Part> listAllParts(){
        EntityManager em = emf1.createEntityManager();
        
        List<Part> parts = null;
        try {
            parts = em.createNamedQuery("Part.findAll").getResultList();
        } catch (Exception e) {
            e.getStackTrace();
        }finally{
            em.close();
        }

        return parts;
    }
    
    public Part delete(Part part){
        
        EntityManager em = emf1.createEntityManager();
        
        em.getTransaction().begin();
        try {
            em.remove(em.merge(part));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            part = null;
        } finally {
            em.close();
        }
        return part;  
    }
    
    
    public Part update(Part oldPart, Part newPart){
        
        EntityManager em = emf1.createEntityManager();
        
        em.getTransaction().begin();
        try {
            
            oldPart.setName(newPart.getName());
            em.merge(oldPart);
            
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            oldPart = null;
        } finally {
            em.close();
        }
        return oldPart;  
    }
    
}
