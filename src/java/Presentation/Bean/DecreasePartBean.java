/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleClient;
import BusinessLogic.Controller.HandleOrder;
import BusinessLogic.Controller.HandlePart;
import DataAccess.Entity.Client;
import DataAccess.Entity.Orders;
import DataAccess.Entity.Part;
import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
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
public class DecreasePartBean {
    
    private List<Orders> orders;
    private String message;
    
    public DecreasePartBean(){
        getAllOrders();
        
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    public List<Orders> getOrders() {
        if( orders == null ){
            HandleOrder handleOrder = new HandleOrder();
            orders = handleOrder.getAllOrders();
        }
        return orders;
    }
    
    public List<Orders> getAllOrders() {
        HandleOrder handleOrder = new HandleOrder();
        orders = handleOrder.getAllOrders();
        for( int i = 0; i < orders.size(); i++ ){
            if(orders.get(i).getAssembled() == orders.get(i).getAmount()){
                orders.remove(i);
                i--;
            }
        }
        return orders;
    }
    
    public void assembledOrder( Orders o ) throws IOException {
        
        HandleOrder handleOrder = new HandleOrder();
        message = handleOrder.decreaseOrder(o);
        if(message.startsWith("there are")){
            message = Util.buildDanger("Error", message);
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));
        }else{
            message = Util.buildSuccess("Correct", message);
            FacesContext.getCurrentInstance().getExternalContext().redirect("decreasePart.xhtml");
        }
        
        
    }
    
    
    public void updateAssembled(ValueChangeEvent event) throws IOException {

        UIInput component = (UIInput) event.getComponent();
        Orders oldOrder = (Orders) (component.getAttributes().get("idOrder"));

    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
    
}
