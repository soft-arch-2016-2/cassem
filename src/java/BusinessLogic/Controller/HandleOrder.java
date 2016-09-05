/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.EmployeeDecreasePartDAO;
import DataAccess.DAO.OrderDAO;
import DataAccess.DAO.PartDAO;
import DataAccess.Entity.Car;
import DataAccess.Entity.CarHasPart;
import DataAccess.Entity.EmployeeDecreasePart;
import DataAccess.Entity.Orders;
import DataAccess.Entity.Part;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




public class HandleOrder {
    
    
    public List<Orders> getAllOrders(){
        OrderDAO orderDAO = new OrderDAO();
        List<Orders> dataList = orderDAO.listAllOrders();
        return dataList;
    }
    
    public String decreaseOrder( Orders order ){
        String result = "";
        String val = "";
        Car car = order.getCarId();
        
        
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        
        int value;
        boolean correct = true;
        for( CarHasPart carHasPart: car.getCarHasPartCollection() ){
           Part partId = carHasPart.getPartId();
            Car carId = carHasPart.getCarId();
            if( carId.getCarId() == car.getCarId() ){
                if(!map.containsKey(partId.getPartId())){
                    map.put(partId.getPartId(), order.getAmount());
                }else{
                    map.put(partId.getPartId(), map.get(partId.getPartId()) + order.getAmount());
                }
                value = map.get(partId.getPartId());
                
                if( partId.getStock() < value ){
                    val = partId.getName();
                    correct = false;
                    break;
                }
            } 
        }
        if(correct){
            
            int count = 0;
            for( CarHasPart carHasPart: car.getCarHasPartCollection() ){
                Part partId = carHasPart.getPartId();
                Car carId = carHasPart.getCarId();
                if( carId.getCarId() == car.getCarId() ){
                    partId.setStock(partId.getStock() - order.getAmount());
                    PartDAO partDAO = new PartDAO();
                    Part partE = partDAO.update(partId);
                    
                    EmployeeDecreasePart employeeDecreasePart = 
                            new EmployeeDecreasePart(order.getAmount(), order.getSaleId().getUserId(), partId);
                    EmployeeDecreasePartDAO employeeDecreaseDAO = new EmployeeDecreasePartDAO();
                    EmployeeDecreasePart employeeDecreaseE = employeeDecreaseDAO.persist(employeeDecreasePart);
                    if(partE != null ){
                        count++;
                    }
                }
            }
            if(count == car.getCarHasPartCollection().size()){
                result = "Car assembled succesfull and parts decrease in stock";
                order.setAssembled(order.getAmount());
                OrderDAO orderDAO = new OrderDAO();
                Orders orderE = orderDAO.update(order);
                
                
                
                
            }else{
                result = "Error in save part";
            }
            
            
        }else{
            result = "there are not enough parts of " + val;
        }
        
        return result;
    }
}
