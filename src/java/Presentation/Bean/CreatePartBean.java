/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandlePart;
import BusinessLogic.Controller.HandlePart;
import DataAccess.Entity.Part;
import DataAccess.Entity.Part;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Fabian
 */
@ManagedBean
@ViewScoped
public class CreatePartBean implements Serializable{
    
    private String name;
    private int stock;
    private int maxStock;
    private String provider;
    private float price;
    private String category;
    private String message;
    private List<Part> parts;
    private int partId;
    
    public CreatePartBean(){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getMessage() {
        return message;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }
    
    

    public void setMessage(String message) {
        this.message = message;
    }
    public List<Part> getAllParts() {
        HandlePart HandlePart = new HandlePart();
        parts =  HandlePart.getAllParts();
        return parts;
    }
    public void createPart(){
        HandlePart HandlePart = new HandlePart();
        message = HandlePart.createPart(name, stock, maxStock, provider, price, category);
        message = Util.buildSuccess("Correct", message);
    }
    
    public void deletePart( Part part ){
        
        parts.remove(part);
        HandlePart handlepart = new HandlePart();
        message = handlepart.deletePart(part);
        message = Util.buildSuccess("Correct", message);
    }
    
    public void updatePart(ValueChangeEvent event) throws IOException {
       
        //Object oldValue = event.getOldValue();
        //Object newValue = event.getNewValue(); 
        
        UIInput component = (UIInput) event.getComponent();
        
        Part oldPart = (Part)(component.getAttributes().get("idPart"));
        oldPart.setName(event.getOldValue()+"");
        
        Part newPart = new Part(oldPart);
        newPart.setName(event.getNewValue()+"");
        
        HandlePart handlePart = new HandlePart();
        message = handlePart.updatePart(oldPart, newPart);
        message = Util.buildSuccess("Correct", message);
        FacesContext.getCurrentInstance().getExternalContext().redirect("createPart.xhtml");
    }
    
}
