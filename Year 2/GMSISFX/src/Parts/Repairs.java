package Parts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Repairs {
    
        private final IntegerProperty repairid;
        private final StringProperty partsname;
        private final StringProperty installdate;
        private final StringProperty warrantydate;
        private final StringProperty registrationnumber;
        private final StringProperty firstname;
        private final StringProperty lastname;
        
        public Repairs(int repairid,String partsname,String installdate,String warrantydate,String registrationnumber,String firstname,String lastname){
            
            this.repairid = new SimpleIntegerProperty(repairid);
            this.partsname = new SimpleStringProperty(partsname);
            this.installdate = new SimpleStringProperty(installdate);
            this.warrantydate = new SimpleStringProperty(warrantydate);
            this.firstname = new SimpleStringProperty(firstname);
            this.lastname = new SimpleStringProperty(lastname);
            this.registrationnumber = new SimpleStringProperty(registrationnumber);
            
    }

        public int getRepairID() {
            return repairid.get();
    }
        public String getPartsName() {
            return partsname.get();
    }
        public String getInstallDate() {
            return installdate.get();
    }
        public String getWarrantyDate() {
            return warrantydate.get();
    }
        public String getFirstName() {
            return firstname.get();
    }
        public String getLastName() {
           return lastname.get();
    }
        public String getRegistrationNumber() {
            return registrationnumber.get();
    }

        public void setRepairID(int rID) {
         repairid.set(rID);
    }
        public void setPartsName(String pname){
            partsname.set(pname);
    }
        public void setInstallDate (String instdate) {
            installdate.set(instdate);
    }
        public void setWarrantyDate(String warranty) {
            warrantydate.set(warranty);
    }
        public void setFirstName(String fname) {
            firstname.set(fname);
    }
        public void setLastName(String lname) {
          lastname.set(lname);
    }
        
        public void setRegistrationNumber(String rnumber) {
            registrationnumber.set(rnumber);
    }        
     
}
