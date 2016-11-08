/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleUser;
import BusinessLogic.Controller.ResponseMessageCassem;
import BusinessLogic.Service.HandleBusService;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fabianlm17-toshiba
 */
@ManagedBean(name="userSessionBean",eager=true)
//@SessionScoped
@ApplicationScoped
public class UserSessionBean implements Serializable {

    public UserSessionBean() {
    }

    private String username;
    private String password;
    private String message;

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

    /*public String getUserNameCookie(){
        FacesContext context = FacesContext.getCurrentInstance();
        Cookie cookie = (Cookie)context.getExternalContext().getRequestCookieMap().get("user");
        return cookie.getValue();
    }*/
    
    public void isLogged() {
        FacesContext context = FacesContext.getCurrentInstance();
        //String username = (String) context.getExternalContext().getSessionMap().get("user");
        
        username =getUsernameSession(context);
        //username = getUserNameCookie();
        if (username == null || username.equals("")) {
            context.getApplication().getNavigationHandler().handleNavigation(context, null, "/login?faces-redirect=true");
        }
    }
    
    public String getUser(){
        FacesContext context = FacesContext.getCurrentInstance();
        String username = getUsernameSession(context);
        //String username = (String) context.getExternalContext().getSessionMap().get("user");
        return username;
    }

    public String login() {
        
        HandleUser handleUser = new HandleUser();

        ResponseMessageCassem response = handleUser.login(username, password);

        username = password = "";

        if (!response.isSuccessful()) {
            message = Util.buildDanger("Error", "Username or password incorrect");
            return "";
        } else {
            message = "";
            return "base?faces-redirect=true";
        }
    }

    public String logout() {
        //FacesContext context = FacesContext.getCurrentInstance();
        //context.getExternalContext().addResponseCookie("user", "",null);
        //FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap().remove("user");
               
         try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest servletRequest = (HttpServletRequest) context.getExternalContext().getRequest();
            servletRequest.logout();
            servletRequest.getSession().invalidate();

        } catch (ServletException ex) {
            Logger.getLogger(UserSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "index?faces-redirect=true";
    }
    
    public String getUsernameSession(FacesContext context ){
        
         HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
         
         return (String)session.getAttribute("username");
    }
    
    public void loginUser(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest servletRequest = (HttpServletRequest) context.getExternalContext().getRequest();
            servletRequest.getSession();
            servletRequest.login(username, "123");
        } catch (ServletException ex) {
            Logger.getLogger(UserSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void isCorrectRole() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest servletRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        
        String fullURI = servletRequest.getRequestURI();

        String[] hierarchy = fullURI.split("/");
        String page = hierarchy[hierarchy.length - 1];

        //String user_username = getUserNameCookie();
        String user_username = getUsernameSession(context);
        //String user_username =(String) ctx.getExternalContext().getSessionMap().get("user");
        HandleUser handleUser = new HandleUser();
        String role = handleUser.getRoleByUsername(user_username);

        boolean redirect = false;

        if (!page.startsWith("base")) {
            if (role.equals("EMPLOYEE")) {
                if (!page.startsWith("createPart") && !page.startsWith("createCar") && !page.startsWith("decreasePart")) {
                    redirect = true;
                }
            } else if (role.equals("SELLER")) {
                if (!page.startsWith("createClient") && !page.startsWith("createSale") && !page.startsWith("sale")) {
                    redirect = true;
                }
            }
        }

        if (redirect) {
            context.getApplication().getNavigationHandler().handleNavigation(context, null, "/base?faces-redirect=true");
        }
    }

    public boolean userExist( FacesContext context){
        return false;
        //ExternalContext ext = context.getExternalContext();
        //return ext.getSessionMap().containsKey(ext);
    }
    
}
