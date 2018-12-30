/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehicle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Customer {
    
    
    private final SimpleIntegerProperty CustomerID;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty Address1;
    private final SimpleStringProperty phoneNumber;
    private final SimpleStringProperty Email;
    

public Customer(int customer_ID, String first_name, String last_name, String address1, String phone_number, String email){


    this.CustomerID = new SimpleIntegerProperty(customer_ID);
    this.firstName = new SimpleStringProperty(first_name);
    this.lastName = new SimpleStringProperty(last_name);
    this.Address1 = new SimpleStringProperty(address1);
    this.phoneNumber = new SimpleStringProperty(phone_number);
    this.Email = new SimpleStringProperty(email);
}     

  
public Integer getCustomerID() {
    return CustomerID.get();
}
public String getFirstName() {
    return firstName.get();    
}      
 public String getLastName() {
    return lastName.get();    
} 
 public String getAddress1() {
    return Address1.get();    
} 
 public String getPhoneNumber() {
    return phoneNumber.get();    
} 
  public String getEmail() {
    return Email.get();    
}
    
public void setCustomerID(int ID) {
        CustomerID.set(ID);
    }
public void setFirstName(String fName) {
        firstName.set(fName);
    }
public void setLastName(String lName) {
        lastName.set(lName);
    }
       public void setAddress1(String address1) {
        Address1.set(address1);
    }
       public void setEmail(String email) {
        Email.set(email);
    }


}
