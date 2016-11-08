/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Service;

import java.io.Serializable;
import javax.jws.WebParam;

/**
 *
 * @author nodo
 */
public class HandleBusService implements Serializable{

    public static ResponseMessage ccuesbOperation(int idPart, int amountPart, int idUser, long idAccessory, long amountAccessory) {
        BusinessLogic.Service.CCUESBService service = new BusinessLogic.Service.CCUESBService();
        BusinessLogic.Service.CCUESBPortType port = service.getCCUESBPort();
        return port.ccuesbOperation(idPart, amountPart, idUser, idAccessory, amountAccessory);
    }

  
    
  /*  public static void ccuesbOperation(long idAccessories, long amountAccessories, int idPart, int amountPart, int idUser, javax.xml.ws.Holder<Boolean> responseAccessories, javax.xml.ws.Holder<BusinessLogic.Service.ResponseMessage> responseUnKit) {
        
        try {
            BusinessLogic.Service.CCUESBService service = new BusinessLogic.Service.CCUESBService();
            BusinessLogic.Service.CCUESBPortType port = service.getCCUESBPort();
           
            port.ccuesbOperation(idAccessories, amountAccessories, idPart, amountPart, idUser, responseAccessories, responseUnKit);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }*/

    
    
}
