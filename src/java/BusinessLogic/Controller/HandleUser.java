/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.AuthDAO;
import DataAccess.DAO.UserDAO;
import DataAccess.Entity.Auth;
import DataAccess.Entity.User;
import javax.faces.context.FacesContext;

/**
 *
 * @author arqsoft_2016_2
 */
public class HandleUser {

    private static final String ADMIN = "ADMIN";
    private static final String EMPLOYEE = "EMPLOYEE";
    private static final String SELLER = "SELLER";

    public ResponseMessage createAccount(String username, String password, String role) {

        String message = null;
        boolean result = false;

        if (role.equals(ADMIN) || role.equals(EMPLOYEE) || role.equals(SELLER)) {
            Auth auth = new Auth(username, password);

            AuthDAO authDAO = new AuthDAO();
            Auth authE = authDAO.persist(auth);

            if (authE != null) {
                User user = new User();
                user.setUsername(auth);
                user.setRole(role);

                UserDAO userDAO = new UserDAO();
                User userE = userDAO.persist(user);

                if (userE != null) {
                    result = true;
                    message = "User has been created succesfully";
                }
            }
        }

        ResponseMessage response = new ResponseMessage(result, message);
        
        return response;
    }
    
    public ResponseMessage login(String username, String password){
        
        boolean successful = false;
        String message = null;
        
        UserDAO userDAO = new UserDAO();
        
        String user_username = userDAO.searchUserIdByUsernameAndPassword(username, password);
        FacesContext context = FacesContext.getCurrentInstance();

        if (user_username == null) {
            //context.addMessage(null, new FacesMessage("Unknown login, try again"));
        } else {
            context.getExternalContext().getSessionMap().put("user", user_username);
            successful = true;
            message = "base?faces-redirect=true";
        }
        
        ResponseMessage response = new ResponseMessage(successful, message);
        return response;
    } 
}
