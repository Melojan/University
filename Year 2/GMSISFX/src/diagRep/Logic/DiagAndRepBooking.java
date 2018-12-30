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


public class DiagAndRepBooking {
    
        private final SimpleIntegerProperty bookingId; 
        private final SimpleStringProperty bookingDate;
        private final SimpleIntegerProperty time;
        private final SimpleIntegerProperty duration;
        private final SimpleIntegerProperty mechanicID;
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty vehicleRegistration;
        private final SimpleIntegerProperty currentMileage;
        private final SimpleStringProperty type;
        private final SimpleStringProperty make;
     
    
     public DiagAndRepBooking(Integer Id, String date, Integer t, Integer e_time, Integer d, Integer mec_id, String first_Name,String last_Name,String veh_Registration,int currentm, String ty, String m) {
        
        this.bookingId = new SimpleIntegerProperty(Id);
        this.bookingDate = new SimpleStringProperty(date);
        this.time = new SimpleIntegerProperty(t);
        this.duration = new SimpleIntegerProperty(d);
        this.mechanicID = new SimpleIntegerProperty(mec_id);
        this.firstName = new SimpleStringProperty(first_Name);
        this.lastName = new SimpleStringProperty(last_Name);
        this.vehicleRegistration = new SimpleStringProperty(veh_Registration);
        this.currentMileage = new SimpleIntegerProperty(currentm);
        this.type = new SimpleStringProperty(ty);
        this.make = new SimpleStringProperty(m);
        
        }

    public Integer getBookingId() {
        return bookingId.get();
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

    public Integer getMechanicID() {
        return mechanicID.get();
    }

    public String getFirstName() {
        return firstName.get();
    }
    
    public String getLastName() {
        return lastName.get();
    }
   
    public String getVehicleRegistration() {
        return vehicleRegistration.get();
    }
    
    public int getCurrentMileage() {
        return currentMileage.get();
    }

    public String getType() {
        return type.get();
    }
    
      public String getMake() {
        return make.get();
    }

    
    
    
    public void setBookingId(int id ) {
        bookingId.set(id);
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

    public void setMechanicID(int mechid) {
        mechanicID.set(mechid);
    }

    public void setFirstName(String fName) {
        firstName.set(fName);
    }
    
    public void setLastName(String lName) {
        lastName.set(lName);
    }

    public void setVehicleRegistration(String vehicle) {
        vehicleRegistration.set(vehicle);
    }
    
    public void setCurrentMileage(int currm) {
        currentMileage.set(currm);
    }

    public void setType(String tp) {
        type.set(tp);
    }
    public void setMake(String ma) {
        make.set(ma);
    }


    
    public IntegerProperty getBookingIdProperty()
   {
       return bookingId;
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
   public IntegerProperty getMechanicIDProperty()
   {
       return mechanicID;
   }
   public StringProperty getFirstNameProperty()
   {
       return firstName;

   }
   
   public StringProperty getLastNameProperty()
   {
       return lastName;

   }
   public StringProperty getVehicleRegistrationProperty()
   {
       return vehicleRegistration;
   }
   public StringProperty getTypeProperty()
   {
       return type;
   }
   public StringProperty getMakeProperty()
   {
       return make;
   }

    public void getInfo(String type, String bookingDate, Integer time, Integer duration, String vehicleRegistration, String firstName, String lastName, int currentMileage, Integer mechanicID, String make) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    


     
     
}
