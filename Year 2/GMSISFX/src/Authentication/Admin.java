/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Admin {
       AuthDbMethods adb = new AuthDbMethods();
    int id;
    String firstName,lastName, type,lastLoginDate;

    public Admin(int id, String fName, String lName, String type, String lastLoginDate) {
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
        this.type = type;
      
            this.lastLoginDate = lastLoginDate;
        
        
    }
    
    public Admin() {
        
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fName) {
        
        adb.update_adminFirstName(fName, this.id);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lName) {
         adb.update_adminLastName(lName, this.id);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        
        int dbType = 0;
        if (type.equals("Admin")){
            dbType=1;
        } else{
             dbType=2;
        }
     
        adb.update_adminType(dbType, this.id);
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
    
    
}
