/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPC;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SPC 
{
    private final SimpleIntegerProperty spcID;
    private final SimpleStringProperty spcName;
    private final SimpleStringProperty address;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty email;

    
    public SPC(int spcID,String spcName, String address, String phone, String email )
    {
    
        this.spcID = new SimpleIntegerProperty(spcID);
        this.spcName = new SimpleStringProperty(spcName);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);

        
    }
    
    public int getID() {
        return spcID.get();
    }
    
    public String getName() {
        return spcName.get();
    }
    
    public String getAddress() {
        return address.get();
    }
    
    public String getPhone() {
        return phone.get();
    }
    
    public String getEmail() {
        return email.get();
    }

    
    public void setID(int ID) {
        spcID.set(ID);
    }
    
    public void setName(String name) {
        spcName.set(name);
    }
    
    public void setAddress(String address1) {
        address.set(address1);
    }

    public void setPhone(String phone1) {
        phone.set(phone1);
    }
    
    public void setEmail(String email1) {
        email.set(email1);
    }

    
            
    
}
