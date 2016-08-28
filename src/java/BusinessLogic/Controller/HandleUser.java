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

/**
 *
 * @author arqsoft_2016_2
 */
public class HandleUser {

    private static final String ADMIN = "ADMIN";
    private static final String EMPLOYEE = "EMPLOYEE";
    private static final String SELLER = "SELLER";

    public String createAccount(String username, String password, String role) {
        String response = "User has not been created";

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
                
                if(userE != null){
                    response = "User has been created succesfully";
                }
            }
        }
        return response;
    }

    public String loginUser(String username, String password) {
        String userId = null;

        UserDAO userDAO = new UserDAO();
        userId = userDAO.searchUserIdByUsernameAndPassword(username, password);

        return userId;
    }
}
