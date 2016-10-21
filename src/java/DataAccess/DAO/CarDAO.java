
package DataAccess.DAO;

import DataAccess.Entity.Car;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Fabian
 */
public class CarDAO implements Serializable{
    
    
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("CassemPUR");
    
    public Car persist(Car car) {
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(car);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            car = null;
        } finally {
            em.close();
        }
        return car;
    }
    
    
    public List<Car> listAllCars(){
        EntityManager em = emf1.createEntityManager();
        
        List<Car> parts = null;
        try {
            parts = em.createNamedQuery("Car.findAll").getResultList();
        } catch (Exception e) {
            e.getStackTrace();
        }finally{
            em.close();
        }

        return parts;
    }
    
}
