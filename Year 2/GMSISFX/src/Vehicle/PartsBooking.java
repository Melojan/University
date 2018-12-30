/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehicle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class PartsBooking {
    private final SimpleIntegerProperty PartsID;
    private final SimpleStringProperty PartsUsed;
    private final SimpleStringProperty PastBookingDate;
    private final SimpleIntegerProperty BookingCost;
    
 
    
public PartsBooking(int part_id, String part_used, String past_booking_date, int booking_cost){
    
    this.PartsID = new SimpleIntegerProperty(part_id);
    this.PartsUsed = new SimpleStringProperty(part_used);
    this.PastBookingDate = new SimpleStringProperty(past_booking_date);
    this.BookingCost = new SimpleIntegerProperty(booking_cost);
    
}
public Integer getPartsID() {
    return PartsID.get();
}
public String getPartUsed() {
    return PartsUsed.get();
}
public String getPastBookingDate() {
      return PastBookingDate.get();
   }    
public Integer getBookingCost() {
    return BookingCost.get();
}

}
