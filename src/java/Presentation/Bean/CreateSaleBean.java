/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleCar;
import BusinessLogic.Controller.HandleClient;
import BusinessLogic.Controller.HandleSale;
import DataAccess.Entity.Car;
import DataAccess.Entity.Client;
import DataAccess.Entity.Sale;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

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
    private List<Client> clients;
    private List<Car> cars;
    private List<Car> addedCars;
    private DualListModel <Car>dualList;
    private String xd;
    
    
    public CreateSaleBean(){
        addedCars = new ArrayList<>();
        getAllCars();
        dualList = new DualListModel<>(cars, addedCars);
    }

    public List<Car> getAddedCars() {
        return addedCars;
    }
    
    public void setAddedCars(List<Car> addedCars) {
        this.addedCars = addedCars;
    }

    public DualListModel<Car> getDualList() {
        return dualList;
    }

    public void setDualList(DualListModel<Car> dualList) {
        this.dualList = dualList;
    }

    
    
    public List<Client> getClients() {
        return clients;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public String getXd() {
        return xd;
    }

    public void setXd(String xd) {
        this.xd = xd;
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
    
    
    public List<Client> getAllClients() {
        if(clients!=null)
            return clients;
        HandleClient handleClient = new HandleClient();
        clients = handleClient.getAllClients();
        return clients;   
    }
    
    public List<String> completeText(String query) {
        query = query.toLowerCase();
        List<String> results = new ArrayList<String>();
        getAllClients();
        for(int i = 0; i < clients.size(); i++) {
            if(clients.get(i).getName().toLowerCase().startsWith(query) || query.equals(""))
               results.add(clients.get(i).getName());
        }
        return results;
    }
    
    
    public List<Car> getAllCars() {
        if(cars!=null)
            return cars;
        HandleCar handleCar = new HandleCar();
        cars = handleCar.getAllCars();
        return cars;   
    }
    
    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((Car) item).getName()).append("<br />");
        }
         
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    } 
 
    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }
     
    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }
     
    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }


}
