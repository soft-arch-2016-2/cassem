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
import DataAccess.Entity.Orders;
import DataAccess.Entity.Sale;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
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
    private String clientValue;
    private String selectValue;
    private ArrayList<Orders> orders;
    private Client client;
    private ArrayList<CarAmount> carAmountList;
    

    
    public CreateSaleBean(){
        addedCars = new ArrayList<>();
        getAllCars();
        getAllClients();
        dualList = new DualListModel<>(cars, addedCars);
        carAmountList = new ArrayList<>();
        orders = new ArrayList<>();
        clientValue = "";
    }

    

    
    public List<Car> getAddCarsTable(){
        return (List<Car>) dualList.getTarget();
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

    public String getClientValue() {
        return clientValue;
    }

    public void setClientValue(String clientValue) {
        this.clientValue = clientValue;
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
    public ArrayList<CarAmount> getCarAmountList() {
        return carAmountList;
    }

    public void setCarAmountList(ArrayList<CarAmount> carAmountList) {
        this.carAmountList = carAmountList;
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
        client = findClient();
        FacesContext context = FacesContext.getCurrentInstance();
        String username = (String) context.getExternalContext().getSessionMap().get("user");
        message = handleSale.createSale( orders, client,username );
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));
    }
    
    public Client findClient(){
        
        for( int i = 0; i < clients.size(); i++ ){
            if( clients.get(i).getName().equals(clientValue) ){
                return clients.get(i);
            }
        }
        return null;
    }
    
    public void deleteSale( Sale sale ){
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
            if(clients.get(i).getName().toLowerCase().startsWith(query) )
               results.add(clients.get(i).getName());
        }
        return results;
    }
    
    public void info() {
        
    }
    
    public List<Car> getAllCars() {
        if(cars!=null)
            return cars;
        HandleCar handleCar = new HandleCar();
        cars = handleCar.getAllCars();
        return cars;   
    }
    
    private void deleteRows( ArrayList<String> ids, ArrayList<Orders> orders, ArrayList<CarAmount> amountList ){
        for( int i = 0; i < orders.size(); i++ ){
            Orders current = orders.get(i);
            boolean contains = false;
            for( int j = 0; j < ids.size(); j++ ){
               if( current.getCarId().getCarId() == Integer.parseInt(ids.get(j)) ){
                   contains = true;
                   break;
                }
            }
            if( contains == false ){
                orders.remove(i);
                amountList.remove(i);
                i--;
            } 
        }
    }
    private Car findCar( int id ){
        for( int i = 0; i < cars.size(); i++ ){
            if( cars.get(i).getCarId() == id ){
                return cars.get(i);
            }
        }
        return null;
    }
    private void addRows( ArrayList<String> ids, ArrayList<Orders> orders, ArrayList<CarAmount> amountList ){
        for( int i = 0; i < ids.size(); i++ ){
            int id = Integer.parseInt(ids.get(i));
            boolean exist = false;
            for( int j = 0; j < orders.size(); j++ ){
                if( id == orders.get(j).getCarId().getCarId() ){
                    exist = true;
                }
            }
            if( !exist ){
                Car car = findCar(id);
                orders.add(new Orders(1, car));
                amountList.add(new CarAmount(car, 1));
            }
        }
    }
            
    
    public void onTransfer(TransferEvent event) {

        ArrayList<String> tmp = (ArrayList<String>)(Object)dualList.getTarget();
        deleteRows(tmp, orders, carAmountList );
        addRows(tmp, orders, carAmountList );

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
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Car) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void updateAmount(ValueChangeEvent event) throws IOException {

        UIInput component = (UIInput) event.getComponent();
        CarAmount carAmount = (CarAmount) (component.getAttributes().get("idCar"));
        //Car car = (Car) (component.getAttributes().get("idCar"));
        int val = Integer.parseInt(event.getNewValue() + "");
        
        
        for( int i =0 ; i < orders.size(); i++ ){
            if( orders.get(i).getCarId().getCarId() == carAmount.getCar().getCarId() ){
                orders.get(i).setAmount(val);
                carAmountList.get(i).setAmount(val);
            }
        }
        
        
        /*Part newPart = new Part(oldPart);
        newPart.setName(event.getNewValue() + "");

        HandlePart handlePart = new HandlePart();
        message = handlePart.updatePart(oldPart, newPart);
        message = Util.buildSuccess("Correct", message);
        FacesContext.getCurrentInstance().getExternalContext().redirect("createPart.xhtml");*/
    }
    
    
        
    public class CarAmount{
        private Car car;
        private int amount;
        
        public CarAmount( Car c, int amount){
            this.car = c;
            this.amount = amount;
        }

        public Car getCar() {
            return car;
        }

        public void setCar(Car car) {
            this.car = car;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
        
        
    }
    
    public void handleSelect(SelectEvent event) {
        //System.out.println(event.getObject());
        selectValue = (String) event.getObject();
    
    }

    


}
