/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.PartDAO;

import DataAccess.Entity.Part;
import java.util.List;


public class HandlePart {
    
    
    public String createPart(String nombre, int stock, int maxStock, String provider, float price,
            String category){
        String response = "Part has not been created";
        
        Part part = new Part(nombre, stock, category, provider, price, category);
        PartDAO partDAO = new PartDAO();
        Part partE = partDAO.persist(part);
        if(partE!=null){
            response = "Part has been created succesfully";
        }
        return response;
    }
    
    public String deletePart( Part part ){
        
        String response = "Part has not been removed";
        
        PartDAO partDAO = new PartDAO();
        
        if(partDAO.delete(part) != null){
            response = "Part has been removed succesfully";
        }
        
        return response;
        
    }
    
    public List<Part> getAllParts(){
        PartDAO partDAO = new PartDAO();
        List<Part> dataList = partDAO.listAllParts();
        return dataList;
    }
    
    
    public String updatePart(Part oldPart, Part newPart){
        
        String response = "Part has not been updated";
        
        PartDAO partDAO = new PartDAO();
        
        if(partDAO.update(oldPart, newPart) != null){
            response = "Part has been updated succesfully";
        }
        
        return response;
        
    }
    
}
