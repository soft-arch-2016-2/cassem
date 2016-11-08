/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;


import BusinessLogic.Controller.HandleCar;
import BusinessLogic.Controller.HandlePart;
import DataAccess.Entity.Car;
import DataAccess.Entity.Part;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author Fabian
 */
@ManagedBean
//@ViewScoped
//@SessionScoped
@ApplicationScoped
public class CreateCarBeanS implements Serializable{
    
    private String name;
    private String message;
    private int price;
    private List<Part> partsAdded;
    private List<Part> parts;
    private List<Car> cars;
    private Part valuePart;
    
    
    
    
    public CreateCarBeanS(){
        partsAdded = new ArrayList<Part>();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Car> getCars() {
        if(cars == null){
            HandleCar handleCar = new HandleCar();
            cars = handleCar.getAllCars();
        }
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    

    public void createCar(){
        
        if(name.isEmpty() || name == null){
            message = Util.buildDanger("Error", "Name cannot be empty.");
        }else if(!Util.onlyFloatNumbers(price+"")){
            message = Util.buildDanger("Error", "Price must be a number.");
        }else{
            HandleCar HandleCar = new HandleCar();
            message = HandleCar.createCar(name,price,partsAdded);
            message = Util.buildSuccess("Correct", message);
        }
    }
    
    public List<Part> listAllCars(){
        HandlePart HandlePart = new HandlePart();
        parts =  HandlePart.getAllParts();
        return parts;
    }

    public List<Part> getPartsAdded() {
        return partsAdded;
    }

    public void setPartsAdded(List<Part> partsAdded) {
        this.partsAdded = partsAdded;
    }

    public void setPartsAdded(ArrayList<Part> partsAdded) {
        this.partsAdded = partsAdded;
    }

    public List<Part> getParts() {
        if(parts == null ){
            HandlePart HandlePart = new HandlePart();
            parts =  HandlePart.getAllParts();
        }
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public Part getValuePart() {
        return valuePart;
    }

    public void setValuePart(Part valuePart) {
        this.valuePart = valuePart;
    }
    
    
    public void deleteCar(Car car) {
        
        
    
    }
    
    
    public void onCarDrop(DragDropEvent ddEvent) {
        Part part = ((Part) ddEvent.getData());
        partsAdded.add(part);
    }
    

}
