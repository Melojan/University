package Parts;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Parts {
    
        private final IntegerProperty partsid;
        private final StringProperty partsname;
        private final StringProperty description;
        private final IntegerProperty partcost;
        private final IntegerProperty stocklevel;
      
        public Parts(int partsid,String partname,String description,int partcost,int stocklevel){
            
            this.partsid = new SimpleIntegerProperty(partsid);
            this.partsname = new SimpleStringProperty(partname);
            this.description = new SimpleStringProperty(description);
            this.partcost = new SimpleIntegerProperty(partcost);
            this.stocklevel = new SimpleIntegerProperty(stocklevel);
 
    }
        public int getPartsID() {
            return partsid.get();
    }

        public String getPartsName() {
            return partsname.get();
    }
        public int getPartCost() {
            return partcost.get();
    }
        public int getStockLevel() {
            return stocklevel.get();
    }
        public String getDescription() {
            return description.get();
    }
    
        public void setPartsID(int pID) {
            partsid.set(pID);
    } 
        public void setPartsName(String pname) {
            partsname.set(pname);
    }
        public void setPartCost(int pcost) {
            partcost.set(pcost);
    }
        public void setStockLevel(int stock) {
            stocklevel.set(stock);
    }
        public void setDescription(String des) {
            description.set(des);
    }
    
}
