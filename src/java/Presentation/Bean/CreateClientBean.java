/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleClient;
import DataAccess.Entity.Client;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Maikol
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class CreateClientBean implements Serializable{

    private String name;
    private String message;
    private List<Client> clients;
    
    public CreateClientBean() {
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

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    
    public List<Client> getAllClients() {
        //if(clients == null){
            HandleClient handleClient = new HandleClient();
            clients = handleClient.getAllClients();
        //}
        return clients;
    }
    
    public void deleteClient( Client client){
        
        clients.remove(client);
        HandleClient handleClient = new HandleClient();
        message = handleClient.deleteClient(client);
        message = Util.buildSuccess("Correct", message);
    }
    
    /*
    public void updateNameClient(ValueChangeEvent event, Client client) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue(); 
    }*/
    
    public void updateClient(List<Client> c){
        System.out.println(c);
    }

    public void createClient() {
        HandleClient handleClient = new HandleClient();
        message = handleClient.createClient(name);
        message = Util.buildSuccess("Correct", message);
    }

}
