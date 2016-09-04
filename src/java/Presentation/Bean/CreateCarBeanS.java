/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;


import BusinessLogic.Controller.HandleCar;
import BusinessLogic.Controller.HandlePart;
import DataAccess.Entity.Part;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.sql.rowset.serial.SerialBlob;
import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Fabian
 */
@ManagedBean
@ViewScoped
public class CreateCarBeanS implements Serializable{
    
    private String name;
    private String message;
    private int price;
    private List<Part> partsAdded;
    private List<Part> parts;
    private Part valuePart;
    private StreamedContent image;
    
    public CreateCarBeanS() throws SQLException{
        partsAdded = new ArrayList<Part>();
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    
    

    public void createCar(){
        HandleCar HandleCar = new HandleCar();
        message = HandleCar.createCar(name,price,partsAdded);
        message = Util.buildSuccess("Correct", message);
    }
    
    public List<Part> listAllCars(){
        HandlePart HandlePart = new HandlePart();
        parts =  HandlePart.getAllParts();
        return parts;
    }

    public List<Part> getPartsAdded() {
        return partsAdded;
    }

    public void setPartsAdded(List<Part> partsAdded) {
        this.partsAdded = partsAdded;
    }

    public void setPartsAdded(ArrayList<Part> partsAdded) {
        this.partsAdded = partsAdded;
    }

    public List<Part> getParts() {
        if(parts == null ){
            HandlePart HandlePart = new HandlePart();
            parts =  HandlePart.getAllParts();
        }
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public Part getValuePart() {
        return valuePart;
    }

    public void setValuePart(Part valuePart) {
        this.valuePart = valuePart;
    }
    
    
    public void onCarDrop(DragDropEvent ddEvent) {
        Part part = ((Part) ddEvent.getData());
        System.out.println(partsAdded.size());
        partsAdded.add(part);
    }

    public StreamedContent getImage() {
        return image;
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }
    
    public void DynamicImageController(){
        
    }
    
    
    public StreamedContent getStreamedImageById() throws SQLException{
        FacesContext context = FacesContext.getCurrentInstance();
        
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }else{
            Blob bild;
             bild = new SerialBlob(parts.get(0).getImage());
            InputStream stream = bild.getBinaryStream();
             return new DefaultStreamedContent(stream);
        }
                //}
            //}
            
    }
    

}
