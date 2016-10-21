/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.EDPDAO;
import DataAccess.Entity.EmployeeDecreasePart;
import java.io.Serializable;
import java.util.List;

public class HandleEDP implements Serializable{
    
    public HandleEDP(){
    }
    
    public List<EmployeeDecreasePart> getAllEDP(){
        EDPDAO edpDAO = new EDPDAO();
        List<EmployeeDecreasePart> edpList = edpDAO.searchAllEDP();
        return edpList;
    }
}
