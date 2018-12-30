/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehicle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PartsBooking1 {
    
    private final SimpleIntegerProperty PartsID1;
    private final SimpleStringProperty PartsUsed1;
    private final SimpleStringProperty FutureBookingDate1;
    private final SimpleIntegerProperty BookingCost1;

public PartsBooking1(int part_id1, String part_used1, String future_booking_date1, int booking_cost1){
    
    this.PartsID1 = new SimpleIntegerProperty(part_id1);
    this.PartsUsed1 = new SimpleStringProperty(part_used1);
    this.FutureBookingDate1 = new SimpleStringProperty(future_booking_date1);
    this.BookingCost1 = new SimpleIntegerProperty(booking_cost1);

}
public Integer getPartsID1() {
    return PartsID1.get();
}
public String getPartUsed1() {
    return PartsUsed1.get();
}
public String getFutureBookingDate1() {
      return FutureBookingDate1.get();
   }
public Integer getBookingCost1() {
    return BookingCost1.get();
}
}