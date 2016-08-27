/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author arqsoft_2016_2
 */
public class UserDAO {

    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("CassemPU");

    public User persist(User user) {
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            user = null;
        } finally {
            em.close();
        }
        return user;
    }

    public User searchByUsername(String username) {
        EntityManager em = emf1.createEntityManager();

        User user = null;

        try {
            user = em.find(User.class, username);
        } catch (Exception e) {

        } finally {
            em.close();
        }
        return user;
    }
    
    public Integer searchUserIdByUsernameAndPassword(String username, String password){
        EntityManager em = emf1.createEntityManager();

        //Query query = em.createNamedQuery("User.findByUsernameAndPassword");
        Query query = em.createQuery("SELECT u.userId FROM User u WHERE u.username = :username AND u.password = :password");
        
        query.setParameter("username", username);
        query.setParameter("password", password);
        
        Integer userId = null;

        try {
            userId = (Integer) query.getSingleResult();
        } catch (Exception e) {

        } finally {
            em.close();
        }
        
        return userId;
    }
}
