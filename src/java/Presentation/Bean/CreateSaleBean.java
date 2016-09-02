/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleSale;
import DataAccess.Entity.Sale;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Milder
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class CreateSaleBean implements Serializable{

    private String name;
    private String message;
    private List<Sale> sales;
    
    public CreateSaleBean() {
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

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
    
    public List<Sale> getAllSales() {
        //if(sales == null){
            HandleSale handleSale = new HandleSale();
            sales = handleSale.getAllSales();
        //}
        return sales;
    }

    public void createSale() {
        HandleSale handleSale = new HandleSale();
        message = handleSale.createSale(name);
        message = Util.buildSuccess("Correct", message);
    }
    
    public void deleteSale( Sale sale){
        
        sales.remove(sale);
        HandleSale handleSale = new HandleSale();
        message = handleSale.deleteSale(sale);
        message = Util.buildSuccess("Correct", message);
    }
    
    public void updateSale(ValueChangeEvent event) throws IOException {
       
        //Object oldValue = event.getOldValue();
        //Object newValue = event.getNewValue(); 
        
        UIInput component = (UIInput) event.getComponent();
        
        int idSale = Integer.parseInt(component.getAttributes().get("idSale")+"");
        
        Sale oldSale = new Sale();
        oldSale.setSaleId(idSale);
        //oldSale.setName(event.getOldValue()+"");
        
        Sale newSale = new Sale();
        newSale.setSaleId(idSale);
        //newSale.setName(event.getNewValue()+"");
        
        HandleSale handleSale = new HandleSale();
        message = handleSale.updateSale(oldSale, newSale);
        message = Util.buildSuccess("Correct", message);
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("createSale.xhtml");
 
    }

}
