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
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

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
    private UploadedFile file;
    private byte byteArray[];
    private StreamedContent graphicText;

    public CreatePartBean() {
        
    }

    public byte[] getByteArray() {
        return byteArray;
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public StreamedContent getGraphicText() {
        return graphicText;
    }

    public void setGraphicText(StreamedContent graphicText) {
        this.graphicText = graphicText;
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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    

    /*public void upload() {
        System.out.println("sssss");
        if (file != null) {
            try {
                System.out.println(file.getFileName());
                InputStream fin2 = file.getInputstream();
                /*Connection con = Database.getConnection();
                PreparedStatement pre = con.prepareStatement("insert into upload_image (image_name,image) values(?,?)");
                pre.setString(1, file.getFileName().toString());
                pre.setBinaryStream(2, fin2, file.getSize());
                pre.executeUpdate();
                System.out.println("Inserting Successfully!");
                pre.close();
                FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
 
            } catch (Exception e) {
                System.out.println("Exception-File Upload." + e.getMessage());
            }
        }
        else{
        FacesMessage msg = new FacesMessage("Please select image!!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }*/
    
    public void upload() throws IOException {
        if(file != null) {
            InputStream fin2;
            fin2 = file.getInputstream();
            byteArray = IOUtils.toByteArray(fin2);
            graphicText = new DefaultStreamedContent(new ByteArrayInputStream(byteArray));
        }
    }

    public void createPart() {
        HandlePart HandlePart = new HandlePart();
        message = HandlePart.createPart(name, stock, maxStock, provider, Float.parseFloat(price), category,byteArray);
        message = Util.buildSuccess("Correct", message);
        
    }

    public void deletePart(Part part) {

        parts.remove(part);
        HandlePart handlepart = new HandlePart();
        message = handlepart.deletePart(part);
        message = Util.buildSuccess("Correct", message);
    }

    public void updatePart(ValueChangeEvent event) throws IOException {

        UIInput component = (UIInput) event.getComponent();

        Part oldPart = (Part) (component.getAttributes().get("idPart"));

        oldPart.setName(event.getOldValue() + "");

        Part newPart = new Part(oldPart);
        newPart.setName(event.getNewValue() + "");

        HandlePart handlePart = new HandlePart();
        message = handlePart.updatePart(oldPart, newPart);
        message = Util.buildSuccess("Correct", message);
        FacesContext.getCurrentInstance().getExternalContext().redirect("createPart.xhtml");
    }

}
