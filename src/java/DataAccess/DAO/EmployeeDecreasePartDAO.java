/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.EmployeeDecreasePart;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Fabian
 */
public class EmployeeDecreasePartDAO implements Serializable{
    
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("CassemPUR");
    
    public EmployeeDecreasePart persist(EmployeeDecreasePart employeeDecreasePart) {
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(employeeDecreasePart);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            employeeDecreasePart = null;
        } finally {
            em.close();
        }
        return employeeDecreasePart;
    }
    
}
