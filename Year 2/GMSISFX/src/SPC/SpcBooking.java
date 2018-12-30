/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPC;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SpcBooking {
            private final SimpleIntegerProperty spcID;
        private final SimpleStringProperty spcName;
        private final SimpleIntegerProperty bookingID;
        private final SimpleIntegerProperty vehicleID;
        private final SimpleStringProperty vehicleName;
        private final SimpleStringProperty vehicleRegNumber;
        private final SimpleIntegerProperty partID;
        private final SimpleStringProperty partName;
        private final SimpleIntegerProperty customerID;
        private final SimpleStringProperty fullName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty phoneNumber;
        private final SimpleStringProperty email;
        private final SimpleStringProperty returnStatus;
        
        public SpcBooking(int spcID, String spcName,int bID, int vID,String vName,String vReg, int pID, String pName,int cID,String cName,String cLName,String cPhone,String cEmail,String rStatus){
            this.spcID = new SimpleIntegerProperty(spcID);
            this.spcName = new SimpleStringProperty(spcName);
            this.bookingID = new SimpleIntegerProperty(bID);
            this.vehicleID = new SimpleIntegerProperty(vID);
            this.vehicleName = new SimpleStringProperty(vName);
            this.vehicleRegNumber = new SimpleStringProperty(vReg);
            this.partID = new SimpleIntegerProperty(pID);
            this.partName = new SimpleStringProperty(pName);
            this.customerID = new SimpleIntegerProperty(cID);
            this.fullName =  new SimpleStringProperty(cName);
            this.lastName = new SimpleStringProperty(cLName);
            this.phoneNumber = new SimpleStringProperty(cPhone);
            this.email = new SimpleStringProperty(cEmail);
            this.returnStatus = new SimpleStringProperty(rStatus);
    
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
        public int getPartID() {
            return partID.get();
    }
        public String getVehicleName() {
            return vehicleName.get();
    }
        public String getVehicleReg() {
            return vehicleRegNumber.get();
    }
        public String getPartName() {
            return partName.get();
    }
                public int getBookingID() {
            return bookingID.get();
    }
                        public int getCustomerID() {
            return customerID.get();
    }
                  public String getCustomerName() {
            return fullName.get();
    }              
                      public String getLastName() {
            return lastName.get();
    }
                          public String getPhone() {
            return phoneNumber.get();
    }
                                  public String getEmail() {
            return email.get();
    }
                                          public String getReturnStatus() {
            return returnStatus.get();
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
        public void setPartID(int part) {
            partID.set(part);
    }
                public void setVehicleName(String vname) {
            vehicleName.set(vname);
    }
                        public void setVehicleRegNumber(String regNum) {
            vehicleRegNumber.set(regNum);
    }
                                public void setPartName(String pname) {
            partName.set(pname);
    }
                                        public void setBookingID(int bID) {
            partID.set(bID);
    }
                                        public void setCustomerID(int cID) {
            partID.set(cID);
    }
            public void setCustomerName(String cName) {
            spcName.set(cName);
    }
               public void setLastName(String lName) {
            spcName.set(lName);
    }
                    public void setCustomerPhone(String cPhone) {
            spcName.set(cPhone);
    }
            public void setCustomerEmail(String cEmail) {
            spcName.set(cEmail);
    }                
                    public void setReturnStatus(String rStatus) {
         returnStatus.set(rStatus);
    }
}
