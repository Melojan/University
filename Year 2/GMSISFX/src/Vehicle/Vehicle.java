/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehicle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Vehicle {
    
    private final SimpleIntegerProperty ID;
    private final SimpleIntegerProperty warrantyID;
    private final SimpleStringProperty Reg;
    private final SimpleStringProperty Model;
    private final SimpleStringProperty Make;
    private final SimpleDoubleProperty EngSize;
    private final SimpleStringProperty Fuel;
    private final SimpleStringProperty ColourCol;
    private final SimpleStringProperty MOTDate;
    private final SimpleStringProperty LastServiceDate;
    private final SimpleIntegerProperty CurrentMileage;
    private final SimpleStringProperty vehicleType;


    
    
public Vehicle(int vehicle_id, int vehicle_warrantyid, String registration_num, String vehicle_model, String car_make, double engine_size, String fuel_type, String vehicle_colour, String MOT_Renewal_date, String last_service, int current_mileage, String vehicle_type){

    this.ID = new SimpleIntegerProperty(vehicle_id);
    this.warrantyID = new SimpleIntegerProperty(vehicle_warrantyid);
    this.Reg = new SimpleStringProperty(registration_num);
    this.Model = new SimpleStringProperty(vehicle_model);
    this.Make = new SimpleStringProperty(car_make);
    this.EngSize = new SimpleDoubleProperty(engine_size);
    this.Fuel = new SimpleStringProperty(fuel_type);
    this.ColourCol = new SimpleStringProperty(vehicle_colour);
    this.MOTDate = new SimpleStringProperty(MOT_Renewal_date);
    this.LastServiceDate = new SimpleStringProperty(last_service);
    this.CurrentMileage = new SimpleIntegerProperty(current_mileage);
    this.vehicleType = new SimpleStringProperty(vehicle_type);

 
}    
public Integer getID() {
    return ID.get();
}
public Integer getWarrantyID() {
    return warrantyID.get();
}
public String getReg() {
      return Reg.get();
   }    
public String getModel() {
      return Model.get();
   }
public String getMake() {
      return Make.get();
   }
public double getEngSize() {
      return EngSize.get();
   }
public String getFuel() {
      return Fuel.get();
   }
public String getColourCol() {
      return ColourCol.get();
   }
public String getMOTDate() {
      return MOTDate.get();
   }
public String getLastServiceDate() {
    return LastServiceDate.get();
}
public Integer getCurrentMileage() {
    return CurrentMileage.get();
}
public String getVehicleType() {
    return vehicleType.get();       
}


public void setID(Integer id) {
        ID.set(id);
    }
public void setWarrantyID(Integer warrantyid) {
        warrantyID.set(warrantyid);
    }
public void setReg(String regNumber) {
        Reg.set(regNumber);
    }
public void setModel(String model) {
        Model.set(model);
    }
       public void setMake(String make) {
        Make.set(make);
    }
       public void setEngSize(int engineSize) {
        EngSize.set(engineSize);
    }
       public void setFuel(String fuelType) {
        Fuel.set(fuelType);
    }
       public void setColour(String colour) {
        ColourCol.set(colour);
    }
       public void setMOTDate(String MotRenewalDate) {
        MOTDate.set(MotRenewalDate);
    }
       public void setlastServiceDate(String lastService) {
        LastServiceDate.set(lastService);
    }
       public void setCurrentMileage(int currentMileage) {
        CurrentMileage.set(currentMileage);
    }
}
