/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPC;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
 
public class VehicleNotReturned {
        private final SimpleIntegerProperty spcID;
        private final SimpleStringProperty spcName;
        private final SimpleIntegerProperty vehicleID;
        private final SimpleStringProperty vehicleName;
        private final SimpleStringProperty vehicleRegNumber;
        private final SimpleIntegerProperty bookingID;
        private final SimpleStringProperty returnDate;

        

        
        public VehicleNotReturned(int spcID, String spcName, int vID,String vName,String vReg,int bID,String rDate){
            this.spcID = new SimpleIntegerProperty(spcID);
            this.spcName = new SimpleStringProperty(spcName);
            this.vehicleID = new SimpleIntegerProperty(vID);
            this.vehicleName = new SimpleStringProperty(vName);
            this.vehicleRegNumber = new SimpleStringProperty(vReg);
            this.bookingID = new SimpleIntegerProperty(bID);
            this.returnDate = new SimpleStringProperty(rDate);

    
    }
        public int getID() {
            return spcID.get();
    }
        public String getName() {
            return spcName.get();
    }
        public int getVehicleID() {
            return vehicleID.get();
    }

        public String getVehicleName() {
            return vehicleName.get();
    }
        public String getVehicleReg() {
            return vehicleRegNumber.get();
    }
                public int getBID() {
            return bookingID.get();
    }
        public String getReturnDate() {
            return returnDate.get();
    }

        public void setID(int ID) {
            spcID.set(ID);
    }
        public void setName(String name) {
            spcName.set(name);
    }
        public void setVehicleID(int vehicle) {
            vehicleID.set(vehicle);
    }

        public void setVehicleName(String vname) {
            vehicleName.set(vname);
    }
        public void setVehicleRegNumber(String regNum) {
            vehicleRegNumber.set(regNum);
    }
                public void setBID(int ID) {
            spcID.set(ID);
    }
        public void setReturnDate(String rDate) {
            returnDate.set(rDate);
    }                
}

