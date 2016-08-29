/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Client;
import DataAccess.Entity.User;
import java.util.List;
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
    
    public User searchById(Integer id){
        EntityManager em = emf1.createEntityManager();

        User user = null;

        try {
            user = em.find(User.class, id);
        } catch (Exception e) {

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

    public String searchUserIdByUsernameAndPassword(String username, String password) {
        EntityManager em = emf1.createEntityManager();

        Query query = em.createNamedQuery("Auth.findByUsernameAndPassword");

        query.setParameter("username", username);
        query.setParameter("password", password);

        String userId = null;

        try {
            userId = (String) query.getSingleResult();
        } catch (Exception e) {

        } finally {
            em.close();
        }

        return userId;
    }

    public List<User> searchAllUsers() {
        EntityManager em = emf1.createEntityManager();

        List<User> users = null;

        try {
            users = em.createNamedQuery("User.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return users;
    }

    public User updateUsername(User oldUser, String newUserName) {

        EntityManager em = emf1.createEntityManager();

        em.getTransaction().begin();
        try {

            oldUser.getUsername().setUsername(newUserName);
            em.merge(oldUser);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            oldUser = null;
        } finally {
            em.close();
        }
        return oldUser;
    }
    
    public User updatePassword(User oldUser, String newPassword) {

        EntityManager em = emf1.createEntityManager();

        em.getTransaction().begin();
        try {

            oldUser.getUsername().setPassword(newPassword);
            em.merge(oldUser);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            oldUser = null;
        } finally {
            em.close();
        }
        return oldUser;
    }
    
    public User delete(User user){
        
        EntityManager em = emf1.createEntityManager();
        
        em.getTransaction().begin();
        try {
            em.remove(em.merge(user));
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
}
