/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.SaleDAO;
import DataAccess.Entity.Sale;
import java.util.List;

/**
 *
 * @author Milder
 */
public class HandleSale {
    
    public List<Sale> getAllSales() {
        
        SaleDAO clientDAO = new SaleDAO();
        List<Sale> query = clientDAO.searchAllSale();
        return query;
    }
    
    public String createSale(String name) {
            
        String response = "Sale has not been created";

        SaleDAO clientDAO = new SaleDAO();
        
        Sale client = new Sale();
        //client.setName(name);

        if (clientDAO.persist(client) != null) {
            response = "Sale has been created succesfully";
        }
        
        return response;
    }
    
    public String deleteSale(Sale client){
        
        String response = "Sale has not been removed";
        
        SaleDAO clientDAO = new SaleDAO();
        
        if(clientDAO.delete(client) != null){
            response = "Sale has been removed succesfully";
        }
        
        return response;
        
    }
    
    public String updateSale(Sale oldSale, Sale newSale){
        
        String response = "Sale has not been updated";
        
        SaleDAO clientDAO = new SaleDAO();
        
        if(clientDAO.update(oldSale, newSale) != null){
            response = "Sale has been updated succesfully";
        }
        
        return response;
        
    }
    
}
