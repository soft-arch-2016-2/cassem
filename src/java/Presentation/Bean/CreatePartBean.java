/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandlePart;
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
public class CreatePartBean implements Serializable {

    private String name;
    private int stock;
    private int maxStock;
    private String provider;
    private String price;
    private String category;
    private String message;
    private List<Part> parts;
    private int partId;

    public CreatePartBean() {

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
        parts = HandlePart.getAllParts();
        return parts;
    }

    public boolean isNumber(String s) {
        try {
            Float.parseFloat(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void createPart() {
        
        if(name.isEmpty() || name == null){
            message = Util.buildDanger("Error", "Name cannot be empty.");
        }else if(!Util.onlyNumbers(stock+"")){
            message = Util.buildDanger("Error", "Stock must have only letters.");
        }else if(!Util.onlyNumbers(maxStock+"")){
            message = Util.buildDanger("Error", "Max Stock must have only letters.");
        }else if(!Util.onlyLetters(provider)){
            message = Util.buildDanger("Error", "Provider must have only letters.");
        }else if(!Util.onlyFloatNumbers(price)){
            message = Util.buildDanger("Error", "Price must be a number.");
        }else if(!Util.onlyLetters(category)){
            message = Util.buildDanger("Error", "Category must have only letters.");
        }else{
            HandlePart HandlePart = new HandlePart();
            message = HandlePart.createPart(name, stock, maxStock, provider, Float.parseFloat(price), category);
            message = Util.buildSuccess("Correct", message);
        }
    }

    public void deletePart(Part part) {

        parts.remove(part);
        HandlePart handlepart = new HandlePart();
        message = handlepart.deletePart(part);
        message = Util.buildSuccess("Correct", message);
    }

    public void updatePartName(ValueChangeEvent event) throws IOException {

        UIInput component = (UIInput) event.getComponent();

        Part oldPart = (Part) (component.getAttributes().get("idPart"));

        Part newPart = new Part(oldPart);
        newPart.setName(event.getNewValue() + "");

        if(newPart.getName().isEmpty() || newPart.getName() == null){
            message = Util.buildDanger("Error", "Name cannot be empty.");
        }else{
            HandlePart handlePart = new HandlePart();
            message = handlePart.updatePart(oldPart, newPart);
            message = Util.buildSuccess("Correct", message);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("createPart.xhtml");
    }
    
    public void updatePartStock(ValueChangeEvent event) throws IOException {

        UIInput component = (UIInput) event.getComponent();

        Part oldPart = (Part) (component.getAttributes().get("idPart"));

        Part newPart = new Part(oldPart);
        
        try{
            newPart.setStock(Integer.parseInt(event.getNewValue() + ""));
        }catch(Exception e){
            message = Util.buildDanger("Error", "Stock must be a number.");
            FacesContext.getCurrentInstance().getExternalContext().redirect("createPart.xhtml");
        }

        if((event.getNewValue()+"").isEmpty() || (event.getNewValue()+"") == null){
            message = Util.buildDanger("Error", "Stock cannot be empty.");
        }else if(newPart.getStock() < 0){
            message = Util.buildDanger("Error", "Stock cannot be less than zero.");
        }else{
            HandlePart handlePart = new HandlePart();
            message = handlePart.updatePart(oldPart, newPart);
            message = Util.buildSuccess("Correct", message);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("createPart.xhtml");
    }
    
}
