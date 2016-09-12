/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.CarHasPartDAO;
import DataAccess.Entity.Car;
import DataAccess.Entity.CarHasPart;
import java.util.List;

/**
 *
 * @author Maikol
 */
public class HandleCarHasPart {
    
    public List<CarHasPart> getAllCarHasPart(){
        CarHasPartDAO carHasPartDAO = new CarHasPartDAO();
        List<CarHasPart> dataList = carHasPartDAO.listAllCarHasPart();
        return dataList;
    }
    
    public List<CarHasPart> getAllCarHasPartByCar(Car car){
        CarHasPartDAO carHasPartDAO = new CarHasPartDAO();
        List<CarHasPart> dataList = carHasPartDAO.listAllCarHasPartByCar(car);
        return dataList;
    }
    
    
}
