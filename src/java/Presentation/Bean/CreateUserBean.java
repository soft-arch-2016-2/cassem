/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleClient;
import BusinessLogic.Controller.HandleUser;
import BusinessLogic.Controller.ResponseMessage;
import DataAccess.Entity.Client;
import DataAccess.Entity.User;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author arqsoft_2016_2
 */
@ManagedBean
//@ViewScoped
//@SessionScoped
@ApplicationScoped
public class CreateUserBean implements Serializable{

    private String username;
    private String password;
    private String message;
    private String type;
    
    private List<User> users;

    public CreateUserBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void createUser() {
        HandleUser handleUser = new HandleUser();
        ResponseMessage response = handleUser.createAccount(username, password, type);

        if (response.isSuccessful()) {
            message = Util.buildSuccess("Correct", response.getMessage());
        } else {
            message = Util.buildDanger("Error", "");
        }
        username = password = "";
    }

    public List<User> getAllUsers() {
        HandleUser handleUser = new HandleUser();
        users = handleUser.getAllUsers();
        return users;
    }
    
    public void updateUserUsername(ValueChangeEvent event) throws IOException {
       
        UIInput component = (UIInput) event.getComponent();
        
        Integer idUser = Integer.parseInt(component.getAttributes().get("idUser").toString());
        
        String newUsername = event.getNewValue().toString();
               
        HandleUser handleUser = new HandleUser();
        message = handleUser.updateUserUsername(idUser, newUsername);
        message = Util.buildSuccess("Correct", message);
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("createUser.xhtml");
     }
    
    public void updateUserPassword(ValueChangeEvent event) throws IOException {
       
        UIInput component = (UIInput) event.getComponent();
        
        Integer idUser = Integer.parseInt(component.getAttributes().get("idUser").toString());
        
        String newPassword = event.getNewValue().toString();
               
        HandleUser handleUser = new HandleUser();
        message = handleUser.updateUserPassword(idUser, newPassword);
        message = Util.buildSuccess("Correct", message);
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("createUser.xhtml");
     }
    
    public void deleteUser( User user){
        
        users.remove(user);
        HandleUser handleUser = new HandleUser();
        message = handleUser.deleteUser(user);
        message = Util.buildSuccess("Correct", message);
    }

}
