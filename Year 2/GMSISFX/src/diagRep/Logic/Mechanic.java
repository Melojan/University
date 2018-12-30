/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagRep.Logic;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Mechanic {
    
     private final SimpleIntegerProperty mID; 
     private final SimpleIntegerProperty duration;
     private final SimpleIntegerProperty cost;
     
     
     public Mechanic(Integer mID, Integer duration, Integer cost){
     
        this.mID = new SimpleIntegerProperty(mID);
        this.duration = new SimpleIntegerProperty(duration);
        this.cost = new SimpleIntegerProperty(cost);
     
     }
     
     public Integer getMID() {
        return mID.get();
    }
     
     public Integer getDuration() {
        return duration.get();
    }
     
     public Integer getCost() {
        return cost.get();
    }
     
     public void setMID(int m ) {
        mID.set(m);
    }
    
     public void setDuration(int du ) {
        duration.set(du);
    }
    
     public void setCost(int c ) {
        cost.set(c);
    }

    
}
