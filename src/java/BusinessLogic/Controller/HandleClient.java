/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.ClientDAO;
import DataAccess.Entity.Client;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Maikol
 */
public class HandleClient implements Serializable{
    
    public List<Client> getAllClients() {
        
        ClientDAO clientDAO = new ClientDAO();
        List<Client> query = clientDAO.searchAllClient();
        return query;
    }
    
    public String createClient(String name) {
            
        String response = "Client has not been created";

        ClientDAO clientDAO = new ClientDAO();
        
        Client client = new Client();
        client.setName(name);

        if (clientDAO.persist(client) != null) {
            response = "Client has been created succesfully";
        }
        
        return response;
    }
    
    public String deleteClient(Client client){
        
        String response = "Client has not been removed";
        
        ClientDAO clientDAO = new ClientDAO();
        
        if(clientDAO.delete(client) != null){
            response = "Client has been removed succesfully";
        }
        
        return response;
        
    }
    
    public String updateClient(Client oldClient, Client newClient){
        
        String response = "Client has not been updated";
        
        ClientDAO clientDAO = new ClientDAO();
        
        if(clientDAO.update(oldClient, newClient) != null){
            response = "Client has been updated succesfully";
        }
        
        return response;
        
    }
    
}
