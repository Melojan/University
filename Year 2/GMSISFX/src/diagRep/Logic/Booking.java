/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagRep.Logic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Booking {
    
       
        private final SimpleStringProperty bookingDate;
        private final SimpleIntegerProperty time;
        private final SimpleIntegerProperty duration;
        private final SimpleIntegerProperty mechanicID;
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty vehicleRegistration;
       
     
    
     public Booking(String veh_Registration,String first_Name,String last_Name, Integer mec_id, String date, Integer t,  Integer d) {
        
        this.vehicleRegistration = new SimpleStringProperty(veh_Registration);
        this.firstName = new SimpleStringProperty(first_Name);
        this.lastName = new SimpleStringProperty(last_Name);
        this.mechanicID = new SimpleIntegerProperty(mec_id);
        this.bookingDate = new SimpleStringProperty(date);
        this.time = new SimpleIntegerProperty(t);
        this.duration = new SimpleIntegerProperty(d);
        
    }
    
     public String getVehicleRegistration() {
        return vehicleRegistration.get();
    }
     
      public String getFirstName() {
        return firstName.get();
    }
    
    public String getLastName() {
        return lastName.get();
    }
   
   public Integer getMechanicID() {
        return mechanicID.get();
    }

    public String getBookingDate() {
        return bookingDate.get();
    }
    


    public Integer getTime() {
        return time.get();
    }


    public Integer getDuration() {
        return duration.get();
    }

 

    

    
      public void setVehicleRegistration(String vehicle) {
        vehicleRegistration.set(vehicle);
    }


    public void setFirstName(String fName) {
        firstName.set(fName);
    }
    
    public void setLastName(String lName) {
        lastName.set(lName);
    }

  
    public void setMechanicID(int mechid) {
        mechanicID.set(mechid);
    }

    public void setBookingDate(String date) {
        bookingDate.set(date);
    }
    


    public void setTime(int ti){
        time.set(ti);
    }


    public void setDuration(int d) {
        duration.set(d);
    }

    

    
   public StringProperty getVehicleRegistrationProperty()
   {
       return vehicleRegistration;
   }

     public StringProperty getFirstNameProperty()
   {
       return firstName;

   }
   
   public StringProperty getLastNameProperty()
   {
       return lastName;

   }

    public IntegerProperty getMechanicIDProperty()
   {
       return mechanicID;
   }
  
  
    public StringProperty getBookingDateProperty()
   {
       return bookingDate;
   }
    

    
      public IntegerProperty getTimeProperty()
   {
       return time;
   }

   public IntegerProperty getDurationProperty()
   {
       return duration;
   }
 

     
     
}
