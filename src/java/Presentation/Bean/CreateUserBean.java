/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleUser;
import BusinessLogic.Controller.ResponseMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author arqsoft_2016_2
 */
@ManagedBean
@ViewScoped
public class CreateUserBean {
    
    private String username;
    private String password;
    private String message;
    private String type;
    
    public CreateUserBean(){
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
    
    public void createUser(){
        HandleUser handleUser = new HandleUser();
        ResponseMessage response = handleUser.createAccount(username, password, type);
        
        if(response.isSuccessful())
            message = Util.buildSuccess("Correct", response.getMessage());
        else
            message = Util.buildDanger("Error", "");
        username = password = "";
    }

}
