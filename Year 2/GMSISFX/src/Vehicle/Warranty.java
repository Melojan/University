/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehicle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Warranty {
    
    private final SimpleIntegerProperty ID;
    private final SimpleStringProperty Name;
    private final SimpleStringProperty Address;
    private final SimpleStringProperty dateOfExpiry;

    
public Warranty(int warranty_id, String warranty_name, String warranty_address, String warranty_expiry_date){
    
    this.ID = new SimpleIntegerProperty(warranty_id);
    this.Name = new SimpleStringProperty(warranty_name);
    this.Address = new SimpleStringProperty(warranty_address);
    this.dateOfExpiry = new SimpleStringProperty(warranty_expiry_date);

}
public Integer getID() {
    return ID.get();
}
public String getName() {
      return Name.get();
   }    
public String getAddress() {
      return Address.get();
   }   
public String getDateOfExpiry() {
    return dateOfExpiry.get();
}


public void setID(Integer id) {
        ID.set(id);
    }
public void setName(String name) {
        Name.set(name);
    }
public void setAddress(String address) {
        Address.set(address);
    }
public void setDateOfExpiry(String expiryDate) {
        dateOfExpiry.set(expiryDate);
    }

}
