/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.logic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;


public class Bookings {
    dbmethods dbm = new dbmethods();
    int id;
    String balance, name,dateTime,returnStatus, vehName,partName,warrantyName,regNumber;
    private BooleanProperty paid = new SimpleBooleanProperty();
    


    public Bookings(int id, String balance, String name, int paid, String dateTime, int returnStatus, String vehName, String partName, String warrantyName, String regNumber){
        this.id = id;
        this.balance = balance;
        this.name = name;
        //this.paid = new BooleanProperty();
         setPaid(paid);
         
        setDateTime(dateTime);
        
        
        setReturnStatus(returnStatus);
        this.vehName = vehName;
        this.partName = partName;
        this.warrantyName = warrantyName;
        this.regNumber = regNumber;
    }

    public String getVehName() {
        return vehName;
    }

    public void setVehName(String vehName) {
        this.vehName = vehName;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getWarrantyName() {
        return warrantyName;
    }

    public void setWarrantyName(String warrantyName) {
        
        this.warrantyName = warrantyName;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }



    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime (String dateTime){
        try {
             DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD"); 
        Date date = (Date)formatter.parse(dateTime);
        SimpleDateFormat newFormat = new SimpleDateFormat("EEEE");
        SimpleDateFormat newFormat2 = new SimpleDateFormat("MMMM YYYY");
        String dayAsNumberWithSuffix = addSuffixToDay(date);
        this.dateTime = newFormat.format(date) +" "+dayAsNumberWithSuffix+" "+newFormat2.format(date);
        } catch(ParseException pe) {
            System.out.println("Exception");
        }
     
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(int returnStatus) {
        if(returnStatus ==0){
            this.returnStatus = "Not Returned";
        } else{
            this.returnStatus = "Returned";
        }
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setBillType(String name) {
        this.name = name;
    }

    public BooleanProperty paidProperty() {
        return paid;
    }
    
    public void setPaidDb(int paid){
        dbm.update_bookingPaid(this.getId(), paid);
    }

    public void setPaid(int paid) {
        
        if (paid == 0){
            // not paid 
            this.paid.setValue(false);
            System.out.println(paid);
            
        }else{
            this.paid.setValue(true);
            System.out.println(paid);
        }
        
        
    }
     private String addSuffixToDay(Date dateW){
        
        // http://stackoverflow.com/questions/4011075/how-do-you-format-the-day-of-the-month-to-say-11th-21st-or-23rd-in-java
         String[] suffixes =
  //    0     1     2     3     4     5     6     7     8     9
     { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
  //    10    11    12    13    14    15    16    17    18    19
       "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
  //    20    21    22    23    24    25    26    27    28    29
       "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
  //    30    31
       "th", "st" };
         
          SimpleDateFormat formatDayOfMonth  = new SimpleDateFormat("d");
        int day = Integer.parseInt(formatDayOfMonth.format(dateW));
           String dayAndsuffix = day + suffixes[day];
          
          return dayAndsuffix;
    }
    
    private String returnCurrentDate(Date dateW) {
        String date = "";
         String dayAsWord = new SimpleDateFormat("EEEE").format(dateW);
         
         String dayAsNumberWithSuffix = addSuffixToDay(dateW);
         
         String monthAndYear = new SimpleDateFormat("MMMM yyyy  h:mm:ss a").format(dateW);
          
         date = dayAsWord + " "+ dayAsNumberWithSuffix + " " + monthAndYear;
        
        
        return date;
    }

}
