/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPC;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

 
public class PartNotReturned {
           private final SimpleIntegerProperty spcID;
        private final SimpleStringProperty spcName;
        private final SimpleIntegerProperty partID;
        private final SimpleStringProperty partName;
        private final SimpleStringProperty description;
        private final SimpleIntegerProperty bookingID;
        private final SimpleStringProperty returnDate;

        
        public PartNotReturned(int spcID, String spcName, int pID,String pName,String desc,int bID,String rDate){
            this.spcID = new SimpleIntegerProperty(spcID);
            this.spcName = new SimpleStringProperty(spcName);
            this.partID = new SimpleIntegerProperty(pID);
            this.partName = new SimpleStringProperty(pName);
            this.description = new SimpleStringProperty(desc);
            this.bookingID = new SimpleIntegerProperty(bID);
            this.returnDate = new SimpleStringProperty(rDate);

    
    }
        public int getID() {
            return spcID.get();
    }
        public String getName() {
            return spcName.get();
    }
        public int getPartID() {
            return partID.get();
    }

        public String getPartName() {
            return partName.get();
    }
        public String getDescription() {
            return description.get();
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
                public void setBID(int ID) {
            spcID.set(ID);
    }
        public void setName(String name) {
            spcName.set(name);
    }
        public void setPartID(int part) {
            partID.set(part);
    }

        public void setPartName(String pname) {
            partName.set(pname);
    }
        public void setDescription(String desc) {
            description.set(desc);
    }
        public void setReturnDate(String rDate) {
            returnDate.set(rDate);
    }
    
}
