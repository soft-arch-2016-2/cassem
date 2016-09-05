/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.OrdersDAO;
import DataAccess.DAO.SaleDAO;
import DataAccess.DAO.UserDAO;
import DataAccess.Entity.Client;
import DataAccess.Entity.Orders;
import DataAccess.Entity.Sale;
import DataAccess.Entity.User;
import java.util.ArrayList;
import java.util.Date;
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
    
    public User getCurrentUser( String username ){
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.searchAllUsers();
        for( int i = 0; i < users.size(); i++ )
            if(users.get(i).getUsername().getUsername().equals(username))
                return users.get(i);
        return null;
    }
    public String createSale( ArrayList<Orders> orders, Client client, String username ) {
            
        String response = "Sale has not been created";
        Date d = new Date();
        Sale sale = new Sale(d);
        sale.setClientId(client);
        sale.setUserId(getCurrentUser(username));
        SaleDAO saleDAO = new SaleDAO();
        Sale saleE = saleDAO.persist(sale);
        if( saleE != null ){
            int count = 0;
            
            for( int i = 0; i < orders.size(); i++ ){
                Orders order = orders.get(i);
                order.setSaleId(sale);
                OrdersDAO orderDAO = new OrdersDAO();
                Orders orderE = orderDAO.persist(order);
                if( orderE!=null ){
                    count++;
                    
                }
            }
            if(count == orders.size()){
                response = "Sale has been created succesfully";
            } 
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
