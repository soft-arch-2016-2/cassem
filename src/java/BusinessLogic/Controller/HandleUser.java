/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.AdminDAO;
import DataAccess.DAO.EmployeeDAO;
import DataAccess.DAO.SellerDAO;
import DataAccess.DAO.UserDAO;
import DataAccess.Entity.Admin;
import DataAccess.Entity.Employee;
import DataAccess.Entity.Seller;
import DataAccess.Entity.User;

/**
 *
 * @author arqsoft_2016_2
 */
public class HandleUser {

    private static final String ADMIN = "ADMIN";
    private static final String EMPLOYEE = "EMPLOYEE";
    private static final String SELLER = "SELLER";

    public String createAccount(String username, String password, String type) {
        String response = "";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        UserDAO userDAO = new UserDAO();
        User userE = userDAO.persist(user);

        boolean userCreated = userE != null, userTypeCreated = false;

        if (userCreated) {
            switch (type) {
                case ADMIN:
                    Admin admin = new Admin();
                    admin.setUserId(userE.getUserId());
                    AdminDAO adminDAO = new AdminDAO();
                    Admin adminE = adminDAO.persist(admin);
                    userTypeCreated |= adminE != null;
                    break;
                case EMPLOYEE:
                    Employee employee = new Employee();
                    employee.setUserId(userE.getUserId());
                    EmployeeDAO employeeDAO = new EmployeeDAO();
                    Employee employeeE = employeeDAO.persist(employee);
                    userTypeCreated |= employeeE != null;
                    break;
                case SELLER:
                    Seller seller = new Seller();
                    seller.setUserId(userE.getUserId());
                    SellerDAO sellerDAO = new SellerDAO();
                    Seller sellerE = sellerDAO.persist(seller);
                    userTypeCreated |= sellerE != null;
                    break;
            }
        }

        if (userCreated && userTypeCreated) {
            response = "User has been created succesfully";
        } else {
            response = "User has not been created";
        }

        return response;
    }
    
    public Integer loginUser(String username, String password){
        Integer userId = null;
        
        UserDAO userDAO = new UserDAO();
        userId = userDAO.searchUserIdByUsernameAndPassword(username, password);
        
        if(userId == null) userId = -11235813;
        
        return userId;
    }
}
