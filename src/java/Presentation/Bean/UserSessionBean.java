/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleUser;
import BusinessLogic.Controller.ResponseMessage;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fabianlm17-toshiba
 */
@ManagedBean
@RequestScoped
public class UserSessionBean {

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

    public void isLogged() {
        FacesContext context = FacesContext.getCurrentInstance();
        String username = (String) context.getExternalContext().getSessionMap().get("user");
        if (username == null) {
            context.getApplication().getNavigationHandler().handleNavigation(context, null, "/login?faces-redirect=true");
        }
    }

    @EJB
    public String login() {
        HandleUser handleUser = new HandleUser();

        ResponseMessage response = handleUser.login(username, password);

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
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

    public void isCorrectRole() {

        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest servletRequest = (HttpServletRequest) ctx.getExternalContext().getRequest();
        String fullURI = servletRequest.getRequestURI();

        String[] hierarchy = fullURI.split("/");
        String page = hierarchy[hierarchy.length - 1];

        String user_username = (String) ctx.getExternalContext().getSessionMap().get("user");

        HandleUser handleUser = new HandleUser();
        String role = handleUser.getRoleByUsername(user_username);

        boolean redirect = false;

        if (!page.startsWith("base")) {
            if (role.equals("EMPLOYEE")) {
                if (!page.startsWith("createPart") && !page.startsWith("createCar")) {
                    redirect = true;
                }
            } else if (role.equals("SELLER")) {
                if (!page.startsWith("createClient") && !page.startsWith("createSale")) {
                    redirect = true;
                }
            }
        }

        if (redirect) {
            ctx.getApplication().getNavigationHandler().handleNavigation(ctx, null, "/base?faces-redirect=true");
        }
    }
}
