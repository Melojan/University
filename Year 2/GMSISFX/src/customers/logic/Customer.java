/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Customer {
    
    dbmethods dbm = new dbmethods();
    
   private int id;
   private String fname, lname,custType, email, phoneNumber, address;
   private Double balance;
    
    
    
    public Customer(int id, String fname,String lname, int Type, String email, String phoneNumber, String address){
        
        this.fname = fname;
        this.lname = lname;
         this.id = id;
        this.setBalance();
        this.setcustType(Type);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        
    }
    
    public Customer(){
        
    }
  private final StringProperty option = new SimpleStringProperty();

    public String getOption() {
        return option.get();
    }

    public void setOption(String value) {
        option.set(value);
    }

    public StringProperty optionProperty() {
        return option;
    }

    public String getEmail() {
        return email;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance() {
        balance = dbm.getTotalBillCost_customer(this.getId());
    }
    public void setBalance(Double balance){ // hack for updating balance locally
        this.balance = balance;
    }
    

    public void setEmail(String email) {
        this.email = email;
        dbm.update_customerEmail(this.getId(), email);// update email in db
    }

    public String getPhoneNumber() {
        return phoneNumber;
       
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
         dbm.update_customerPhone(this.getId(), phoneNumber);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        dbm.update_customerAddress(this.getId(), address);
    }

    public String getcustType() {
        return custType;
    }

    public void setcustType(int type) {
        if (type == 1){
            this.custType = "Individual";
            
        } else{
            this.custType = "Business";
        }
        
        
    }
 public void setcustType(String type) {
        if (type == "Individual"){
            dbm.update_customerType(this.getId(), 1);
            
        } else{
            dbm.update_customerType(this.getId(), 2);
        }
        
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        dbm.update_customerFname(this.getId(), fname);
        this.fname = fname;
        
    }

    public String getLname() {
        
        return lname;
    }

    public void setLname(String lname) {
        dbm.update_customerLname(this.getId(), lname);
        this.lname = lname;
    }
    
}
