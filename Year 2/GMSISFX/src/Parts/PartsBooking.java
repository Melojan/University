package Parts;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PartsBooking {
    
    private IntegerProperty bookingid;
    private StringProperty firstname;
    private StringProperty lastname;
    private StringProperty bookingtype;
    private StringProperty date;

    public PartsBooking(int bookingid,String firstname,String lastname,String bookingtype,String date)
    {
        this.bookingid = new SimpleIntegerProperty(bookingid);
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.bookingtype = new SimpleStringProperty(bookingtype);
        this.date = new SimpleStringProperty(date);
    }
    
    public int getBookingID() {
        return bookingid.get();
    }
      
    public String getFirstName() {
        return firstname.get();
    }
    
    public String getLastName() {
        return lastname.get();
    }
    
    public String getBookingType() {
        return bookingtype.get();
    }
    
    public String getDate() {
        return date.get();
    }
    
    public void setBookingID(int cID) {
        bookingid.set(cID);
    }
    
    public void setFirstName(String fname) {
        firstname.set(fname);
    }
    
    public void setLastName(String lname) {
        lastname.set(lname);
    }
    
    public void setBookingType(String bType) {
        bookingtype.set(bType);
    }
    
    public void setDate(String d) {
        date.set(d);
    }
    
}
