/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleCarHasPart;
import DataAccess.Entity.Car;
import DataAccess.Entity.CarHasPart;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Maikol
 */
@ManagedBean
//@ViewScoped
//@SessionScoped
@ApplicationScoped
public class CarHasPartBean implements Serializable{
    
    private List<CarHasPart> carHasPart;

    public List<CarHasPart> getCarHasPart() {
        return carHasPart;
    }

    public void setCarHasPart(List<CarHasPart> carHasPart) {
        this.carHasPart = carHasPart;
    }

    public List<CarHasPart> getAllCarHasPart() {
        HandleCarHasPart handleCarHasPart = new HandleCarHasPart();
        carHasPart = handleCarHasPart.getAllCarHasPart();
        return carHasPart;
    }
    
    public List<CarHasPart> getCarHasPartByCar(Car car) {
        
        
        HandleCarHasPart handleCarHasPart = new HandleCarHasPart();
        //carHasPart = handleCarHasPart.getAllCarHasPart();
        
        carHasPart = handleCarHasPart.getAllCarHasPartByCar(car);

        return carHasPart;
        
    }
    
}
