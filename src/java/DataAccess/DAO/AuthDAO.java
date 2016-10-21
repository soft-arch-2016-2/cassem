/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Auth;
import DataAccess.Entity.User;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AuthDAO implements Serializable{
    
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("CassemPUR");

    public Auth persist(Auth auth) {
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(auth);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            auth = null;
        } finally {
            em.close();
        }
        return auth;
    }
    
    public Auth searchByUsername(String username) {
        EntityManager em = emf1.createEntityManager();

        Query query = em.createNamedQuery("Auth.findByUsername");

        query.setParameter("username", username);
        
        Auth auth = null;

        try {
            auth = (Auth) query.getSingleResult();
        } catch (Exception e) {

        } finally {
            em.close();
        }
        return auth;
    }
}
