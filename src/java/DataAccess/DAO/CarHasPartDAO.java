/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Car;
import DataAccess.Entity.CarHasPart;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Fabian
 */
public class CarHasPartDAO implements Serializable{
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("CassemPUR");
    
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
    
    public List<CarHasPart> listAllCarHasPart(){
        EntityManager em = emf1.createEntityManager();
        
        List<CarHasPart> carHasPart = null;
        try {
            carHasPart = em.createNamedQuery("CarHasPart.findAll").getResultList();
        } catch (Exception e) {
            e.getStackTrace();
        }finally{
            em.close();
        }
        return carHasPart;
    }
    
    public List<CarHasPart> listAllCarHasPartByCar(Car car){
        EntityManager em = emf1.createEntityManager();
        
        List<CarHasPart> carHasPart = null;
        try {
            carHasPart = em.createNamedQuery("CarHasPart.findByCarId").setParameter("carId", car).getResultList();
        } catch (Exception e) {
            e.getStackTrace();
        }finally{
            em.close();
        }
        return carHasPart;
    }

    
}
