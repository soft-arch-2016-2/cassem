/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.CarDAO;
import DataAccess.DAO.CarHasPartDAO;
import DataAccess.Entity.Car;
import DataAccess.Entity.CarHasPart;
import DataAccess.Entity.Part;
import java.util.List;

/**
 *
 * @author Fabian
 */
public class HandleCar {
    
    
    public String createCar( String name, long price, List<Part> parts){
        String response = "Car has not been created";
        Car car = new Car(name, price);
        CarDAO carDAO = new CarDAO();
        Car carE = carDAO.persist(car);
        if(carE!=null){
            int count= 0;
            for( Part part: parts){
                CarHasPart carHasPart = new CarHasPart(car,part);
                CarHasPartDAO carHasPartDAO = new CarHasPartDAO();
                CarHasPart carHasPartE = carHasPartDAO.persist(carHasPart);
                if(carHasPartE!=null){
                    count++;
                }
            }
            if( count == parts.size() ){
                response = "Part has been created succesfully";
            }     
        }
        return response;
    }
    
    
    
    
}
